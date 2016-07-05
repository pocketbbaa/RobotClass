package vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2016/7/5.
 */
public class CommentVO implements Serializable{

    private String useremail;
    private String createTime;
    private String text;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
