package enums;

/**
 * Created by admin on 2016/6/28.
 */
public enum CourseEnum {

    COURSE_ERROR(1,"异常操作"),
    COURSE_NOTEXIST(2,"课程不存在"),
    VIDEO_NOTEXIST(3,"视频不存在"),
    COMMENT_NOTEXIST(4,"还没有评论"),
    PARAME_ERROR(5,"参数错误"),
    DEL_ERROR(6,"删除失败"),
    DEL_SUCCESS(7,"删除成功")
    ;

    private int state;
    private String stateInfo;

    CourseEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static CourseEnum stateOf(int index) {
        for (CourseEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }


}
