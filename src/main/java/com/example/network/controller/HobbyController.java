package com.example.network.controller;

import com.example.network.common.PageObject;
import com.example.network.domain.Hobby;
import com.example.network.service.HobbyService;
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
@RequestMapping("/hobby")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @ResponseBody
    @RequestMapping("/getHobbyList")
    public PageObject<Hobby> queryAll(@RequestBody PageObject<Hobby> pageObject) {
        return hobbyService.queryAll(pageObject);
    }

    @ResponseBody
    @RequestMapping("/deleteHobby")
    public Integer deleteHobby(@RequestBody List<Integer> ids) {
        hobbyService.deleteHobby(ids);
        return 1;
    }

    @ResponseBody
    @RequestMapping("/addHobby")
        public int addHobby(@RequestBody Hobby hobby) {
        if (hobby.getId() != null) {
            hobby.setUpdateTime(new Date());
            hobbyService.updateHobby(hobby);
        } else {
            hobby.setCreateTime(new Date());
            hobbyService.addHobby(hobby);
        }

        return 1;
    }
}

