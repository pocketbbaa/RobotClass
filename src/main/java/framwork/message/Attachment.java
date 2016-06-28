package framwork.message;

import java.io.File;

/**
 * 邮件附件
 * Created by admin on 2016/6/28.
 */
public class Attachment {

    private File file;
    private String filename;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilename() {
        if (filename == null || filename.trim().length() == 0) {
            return file.getName();
        }
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Attachment(File file, String filename) {
        super();
        this.file = file;
        this.filename = filename;
    }

    public Attachment(File file) {
        super();
        this.file = file;
    }

}
