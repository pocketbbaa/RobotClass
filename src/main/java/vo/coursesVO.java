package vo;

import model.Course;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/7/1.
 */
public class coursesVO implements Serializable{

    private int totalCount;
    private int totalPage;
    private List<Course> courseList;
    private int pageNo;


    public coursesVO(int totalCount, int totalPage, List<Course> courseList,int pageNo) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.courseList = courseList;
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "coursesVO{" +
                "pageNo=" + pageNo +
                ", courseList=" + courseList +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                '}';
    }
}
