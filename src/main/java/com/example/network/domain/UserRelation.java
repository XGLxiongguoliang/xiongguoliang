package com.example.network.domain;


/**
 * @program com.example.demo.domain
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:53
 */
public class UserRelation {

    private Integer id;
    private Integer masterId;
    private Integer servantId;
    private Integer relationId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getServantId() {
        return servantId;
    }

    public void setServantId(Integer servantId) {
        this.servantId = servantId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}
