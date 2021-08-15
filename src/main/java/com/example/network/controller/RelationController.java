package com.example.network.controller;

import com.example.network.common.PageObject;
import com.example.network.domain.Relation;
import com.example.network.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @program com.example.demo.controller
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:36
 */
@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @ResponseBody
    @RequestMapping("/getRelationList")
    public PageObject<Relation> queryPageList(@RequestBody PageObject<Relation> pageObject) {
        return relationService.queryAll(pageObject);
    }

    @ResponseBody
    @RequestMapping("/getAllRelationList")
    public List<Relation> queryAll() {
        return relationService.queryAllRelationList();
    }

    @ResponseBody
    @RequestMapping("/deleteRelation")
    public Integer deleteRelation(@RequestBody List<Integer> ids) {
        relationService.deleteRelation(ids);
        return 1;
    }

    @ResponseBody
    @RequestMapping("/addRelation")
    public int addRelation(@RequestBody Relation relation) {
        if (relation.getId() != null) {
            relation.setUpdateTime(new Date());
            relationService.updateRelation(relation);
        } else {
            relation.setCreateTime(new Date());
            relationService.addRelation(relation);
        }

        return 1;
    }

}

