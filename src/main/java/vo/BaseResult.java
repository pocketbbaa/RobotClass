package vo;

import java.io.Serializable;

/**
 * Created by admin on 2016/6/27.
 */
public class BaseResult implements Serializable{

    public static final int STATUS_OK = 0;
    public static final int STATUS_ERROR = 1;

    private int status;
    private String message;
    private Object date;

    public BaseResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResult(int status, String message, Object date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
