package com.example.network.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.network.common
 * @description 分页对象信息
 * @auther Mr.Xiong
 * @create 2020-08-10 09:00
 */
public class PageObject<T> implements Serializable {
    //当前页的页数
    private Integer pageCurrent = 1;
    //每页的默认大小
    private Integer pageSize = 20;
    //总条数
    private Long rowCount;
    //总页数
    private Long pageCount;
    //当前页数据
    private List<T> records;

    private T param;

    public PageObject(Integer pageCurrent, Integer pageSize, Long rowCount, List<T> records, T param) {
        super();
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.records = new ArrayList<>(records);
        this.pageCount = (rowCount-1)/pageSize + 1;
        this.param = param;
    }

    public PageObject() {

    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
