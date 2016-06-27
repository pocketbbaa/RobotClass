package interceptor;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import utils.Page;
import utils.ReflectHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 * Created by admin on 2016/6/27.
 */
public class PagePlugin implements Interceptor {


    private static String dialect = ""; // 数据库方言
    private static String pageSqlId = ""; // mapper.xml中需要拦截的ID(正则匹配)
    private static String groupSqld = "";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

            if (mappedStatement.getId().matches(pageSqlId) || mappedStatement.getId().matches(groupSqld)) { // 拦截需要分页的SQL
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject尚未实例化！");
                }
                Connection connection = ((Connection) invocation.getArgs()[0]);
                String sql = boundSql.getSql().toLowerCase();
                String countSql = "";
                if (countOccurrencesOf(sql, "from") == 1) { // 比较简单的查询，统计时简单的替换select 和 from间为count(1)
                    countSql = sql.replaceAll("select([\\s\\S]*)from", "select count(1) from ");
                    int orderByIndex = countSql.indexOf("order by");
                    if (orderByIndex >= 0) {
                        countSql = countSql.substring(0, orderByIndex);
                    }
                } else {
                    countSql = "select count(0) from (" + sql + ") as tmp_count"; // 记录统计
                }
                if (mappedStatement.getId().matches(groupSqld)) {
                    countSql = "select count(0) from ( " + countSql + " ) temp ";
                }
                PreparedStatement countStmt = connection.prepareStatement(countSql);

                DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
                parameterHandler.setParameters(countStmt);
                ResultSet rs = countStmt.executeQuery();
                int count = 0;
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                rs.close();
                countStmt.close();
                Page<?> page = null;
                if (parameterObject instanceof Page) { // 参数就是Page实体
                    page = (Page<?>) parameterObject;
                    page.setTotalCount(count);
                } else if (parameterObject instanceof Map) {
                    page = (Page<?>) ((Map<?, ?>) parameterObject).get("page");
                    page.setTotalCount(count);
                } else { // 参数为某个实体，该实体拥有Page属性
                    Field pageField = ReflectHelper.getFieldByFieldName(parameterObject, "page");
                    if (pageField != null) {
                        page = (Page<?>) ReflectHelper.getValueByFieldName(parameterObject, "page");
                        if (page == null)
                            page = new Page<Object>();
                        // 注释
                        page.setTotalCount(count);
                        ReflectHelper.setValueByFieldName(parameterObject, "page", page); // 通过反射，对实体对象设置分页对象
                    } else {
                        throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
                    }
                }
                String pageSql = generatePageSql(sql, page);
                ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        dialect = properties.getProperty("dialect");
        pageSqlId = properties.getProperty("pageSqlId");
        groupSqld = properties.getProperty("groupSqld");

    }

    /**
     * 根据数据库方言，生成特定的分页sql
     *
     * @param sql
     * @param page
     * @return
     */
    private String generatePageSql(String sql, Page<?> page) {
        if (page != null && dialect != null && !"".equals(dialect)) {
            StringBuffer pageSql = new StringBuffer();
            if ("mysql".equals(dialect)) {
                pageSql.append(sql);
                pageSql.append(" limit " + page.getCurrentResult() + "," + page.getPageSize());
            } else if ("oracle".equals(dialect)) {
                pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
                pageSql.append(sql);
                pageSql.append(") as tmp_tb where ROWNUM<=");
                pageSql.append(page.getCurrentResult() + page.getPageSize());
                pageSql.append(") where row_id>");
                pageSql.append(page.getCurrentResult());
            }
            return pageSql.toString();
        }
        return sql;
    }

    protected int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0;
        int pos = 0;
        int idx;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }


}
