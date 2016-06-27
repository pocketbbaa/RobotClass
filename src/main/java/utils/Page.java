package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Page类包含分页基本信息
 * Created by admin on 2016/6/27.
 */
public class Page<E> implements Serializable{


    private int pageSize = 20; // 每页显示记录数
    private int totalPage; // 总页数
    private int totalCount; // 总记录数
    private int pageIndex; // 当前页
    private int currentResult; // 当前记录起始索引
    private List<E> result = new ArrayList<>(); //分页数据的List集合


    public Page() {
    }

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        totalPage = (totalCount + pageSize - 1) / pageSize;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        if (pageIndex > getTotalPage())
            pageIndex = getTotalPage();
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentResult() {
        currentResult = (getPageIndex() - 1) * getPageSize();
        if (currentResult < 0) {
            currentResult = 0;
        }
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "Page [pageSize=" + pageSize + ", totalPage=" + getTotalPage()
                + ", totalCount=" + getTotalCount() + ", pageIndex=" + pageIndex
                + ", currentResult=" + currentResult + "]";
    }



}
