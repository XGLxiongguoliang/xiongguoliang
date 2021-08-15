package com.example.network.domain.vo;

import com.example.network.domain.UserRelation;

/**
 * @program com.example.network.domain.vo
 * @description 用户关系模型
 * @auther Mr.Xiong
 * @create 2020-08-11 21:60
 */
public class UserRelationVO extends UserRelation {
    private String masterName;
    private String servantName;
    private String relationName;

    public UserRelationVO() {
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getServantName() {
        return servantName;
    }

    public void setServantName(String servantName) {
        this.servantName = servantName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
}
