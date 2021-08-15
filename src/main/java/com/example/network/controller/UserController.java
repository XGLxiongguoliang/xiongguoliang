package com.example.network.controller;

import com.example.network.common.PageObject;
import com.example.network.domain.UserInfo;
import com.example.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program com.example.demo.controller
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String goIndex() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getUserList")
    public PageObject<UserInfo> queryPageList(@RequestBody PageObject<UserInfo> pageObject) {
        return userService.queryPageList(pageObject);
    }

    @ResponseBody
    @RequestMapping("/getAllUserList")
    public List<UserInfo> queryAll() {
        return userService.queryAllUserList();
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public Integer deleteUser(@RequestBody List<Integer> ids) {
        userService.deleteUser(ids);
        return 1;
    }

    @ResponseBody
    @RequestMapping("/addUser")
        public int addUser(@RequestBody UserInfo user) {
        if (user.getId() != null) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }

        return 1;
    }
}

