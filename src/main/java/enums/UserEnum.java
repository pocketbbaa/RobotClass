package enums;

/**
 * 状态枚举
 * Created by admin on 2016/6/27.
 */
public enum UserEnum {

    USER_NOEXIST(1,"用户名不存在"),
    USER_FORMATERROR(2,"用户名格式错误"),
    USER_NAMENULL(3,"参数错误");



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
