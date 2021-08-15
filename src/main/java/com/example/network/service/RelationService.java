package com.example.network.service;

import com.example.network.common.PageObject;
import com.example.network.domain.Relation;
import com.example.network.mapper.RelationMapper;
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
public interface RelationService {
    PageObject<Relation> queryAll(PageObject<Relation> pageObject);

    List<Relation> queryAllRelationList();

    void deleteRelation(List<Integer> ids);

    void addRelation(Relation relation);

    void updateRelation(Relation relation);
}
