package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.Relation;
import com.example.network.mapper.RelationMapper;
import com.example.network.service.RelationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public PageObject<Relation> queryAll(PageObject<Relation> pageObject) {
        PageHelper.startPage(pageObject.getPageCurrent(), pageObject.getPageSize());

        List<Relation> relationList = relationMapper.queryAll();

        PageInfo<Relation> pageInfo = new PageInfo(relationList);

        pageObject.setRowCount(pageInfo.getTotal());
        pageObject.setPageCurrent(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setRecords(relationList);

        return pageObject;
    }

    @Override
    public List<Relation> queryAllRelationList() {
        return relationMapper.queryAll();
    }

    @Override
    public void deleteRelation(List<Integer> ids) {
        relationMapper.batchUpdate(ids);
    }

    @Override
    public void addRelation(Relation relation) {
        relationMapper.addRelation(relation);
    }

    @Override
    public void updateRelation(Relation relation) {
        relationMapper.updateRelation(relation);
    }
}
