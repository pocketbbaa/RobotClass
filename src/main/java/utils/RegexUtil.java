package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证类
 * Created by admin on 2016/6/27.
 */
public class RegexUtil {

    static boolean flag = false;
    static String regex = "";

    /**
     * 自定义验证规则（待验证字符串 , 正则表达式）
     */
    public static boolean check(String str, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 验证手机号码
     *
     * 满足13、14、15、18、17开头的手机号
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(18[0-9])|(17[0-9]))\\d{8}$";
        return check(cellphone, regex);
    }

    /**
     * 邮箱验证(邮箱格式)
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^\\w+[-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
        return check(email, regex);
    }


    /**
     * 校验用户名（字母+中文 8到20位）
     * @param username
     * @return
     */
    public static boolean checkUsername(String username){
        String regex = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{8,20}";
        return check(username,regex);
    }


}
