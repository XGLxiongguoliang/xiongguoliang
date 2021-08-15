package com.example.network.controller;

import com.example.network.common.PageObject;
import com.example.network.domain.UserRelation;
import com.example.network.domain.vo.UserRelationVO;
import com.example.network.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program com.example.demo.controller
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:36
 */
@Controller
@RequestMapping("/userrelation")
public class userRelationController {

    @Autowired
    private UserRelationService userRelationService;

    @ResponseBody
    @RequestMapping("/saveUserRelation")
    public int saveUserRelation(@RequestBody UserRelation userRelation) {
        userRelationService.addUserRelation(userRelation);
        return 1;
    }

    @ResponseBody
    @RequestMapping("/getUserRelationListByUser/{id}")
    public Map<String, List> getUserRelationListByUser(@PathVariable Integer id) {
        Map<String, List> map = userRelationService.getUserRelationListByUser(id);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getUserRelationListAll")
    public Map<String, List> getUserRelationListAll() {
        Map<String, List> map = userRelationService.getUserRelationListAll();
        return map;
    }

    @ResponseBody
    @RequestMapping("/getUserRelationPageList")
    public PageObject<UserRelationVO> getUserRelationPageList(@RequestBody PageObject<UserRelationVO> pageObject) {
        return userRelationService.getUserRelationPageList(pageObject);
    }

    @ResponseBody
    @RequestMapping("/deleteUserRelation")
    public Integer deleteUserRelation(@RequestBody List<Integer> ids) {
        userRelationService.deleteUserRelation(ids);
        return 1;
    }
}

