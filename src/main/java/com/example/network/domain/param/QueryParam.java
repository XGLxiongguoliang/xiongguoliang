package com.example.network.domain.param;

import com.example.network.common.PageObject;

import java.io.Serializable;

/**
 * @program com.example.network.domain.param
 * @description 查询用户列表的参数
 * @auther Mr.Xiong
 * @create 2020-08-10 10:11:00
 */
public class QueryParam<T> implements Serializable {
    private T entity;
    private PageObject<T> pageObject;

    public QueryParam(T entity, PageObject<T> pageObject) {
        this.entity = entity ;
        this.pageObject = pageObject;
    }

    public T getObject() {
        return entity;
    }

    public void setObject(T object) {
        this.entity = object;
    }

    public PageObject<T> getPageObject() {
        return pageObject;
    }

    public void setPageObject(PageObject<T> pageObject) {
        this.pageObject = pageObject;
    }
}
