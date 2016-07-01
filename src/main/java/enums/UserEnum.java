package enums;

/**
 * 状态枚举
 * Created by admin on 2016/6/27.
 */
public enum UserEnum {

    USER_NOEXIST(1, "用户名不存在"),
    USER_FORMATERROR(2, "参数格式错误"),
    USER_PARAMEERROR(3, "参数错误"),
    USER_NOJD(4,"没有权限"),
    USER_ADDERROR(5,"添加用户失败"),
    USER_ADDSUCCESS(6,"添加用户成功"),
    EMAIL_EXIST(7,"该邮箱已被注册"),
    EMAIL_ERROR(8,"邮箱有误"),
    USERNAME_EXIST(9,"用户名已存在"),
    EMAIL_NOTRIGHT(10,"邮箱格式不正确");


    private int state;
    private String stateInfo;

    UserEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserEnum stateOf(int index) {
        for (UserEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
