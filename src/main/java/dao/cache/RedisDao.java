package dao.cache;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import java.util.List;



/**
 * redis缓存
 * Created by admin on 2016/7/6.
 */
public class RedisDao<T> {

    public static Jedis jedis = new Jedis("localhost");

    /**
     * 存字符串
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        jedis.set(key, value);
    }

    /**
     * 取字符串
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return jedis.get(key);
    }

    /**
     * 存对象
     * @param key
     * @param object
     */
    public void setObject(String key,Object object){
        String json = JSON.toJSONString(object);
        jedis.set(key,json);
    }

    /**
     * 去对象
     * @param key
     * @return
     */
    public Object getObject(String key){
        String json =  jedis.get(key);
        return JSON.parse(json);
    }

    /**
     * 存list集合
     *  @param key
     * @param list
     */
    public void setList(String key, List<T> list) {
        for (T t : list) {
            String objstr = JSON.toJSONString(t);
            jedis.lpush(key, objstr);
        }
    }

    /**
     * 获取list集合
     * @param key
     * @param size
     * @return
     */
    public List<String> getList(String key, Long size) {

        return jedis.lrange(key, 0, size);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println(jedis.ping());

        //存字符串
       /* jedis.set("mykey","wangyang");
        System.out.println("存储成功");
        System.out.println("获取成功："+jedis.get("mykey"));*/

        //存集合
       /* jedis.lpush("listkey","aaaa");
        jedis.lpush("listkey","bbbb");
        jedis.lpush("listkey","cccc");
        jedis.lpush("listkey","dddd");
        jedis.lpush("listkey","eeee");
        jedis.lpush("listkey","ffff");

        System.out.println("存集合成功");*/
        List<String> list = jedis.lrange("listkey", 0, 2);
        for (String s : list) {
            System.out.println(s);
        }

       /* Set<String> list = jedis.keys("*");
        for(String s : list){
            System.out.println(s);
        }
*/

    }
}
