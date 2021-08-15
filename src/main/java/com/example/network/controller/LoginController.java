package com.example.network.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.network.domain.UserInfo;
import com.example.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program com.example.demo.controller
 * @description user
 * @auther Mr.Xiong
 * @create 2020-03-07 12:36
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserInfo login(@RequestBody UserInfo user) {
        return userService.authUser(user);
    }

    @RequestMapping("/test")
    public List login(@RequestBody JSONArray list) {

        System.out.println(list);
        List<List<String>> result = new ArrayList<>();

        list.forEach(n -> {
            List<String> lists = diGui((List<JSONObject>) ((LinkedHashMap) n).get("children"), (String) ((JSONObject) n).get("id"));
            result.add(lists);
        });

        System.out.println(result);
        return result;
    }

    private List<String> diGui(List<JSONObject> children, String parentId) {

        List<String> lists = new ArrayList<>();

        //添加父id
        lists.add(parentId);

        //json中的children是否有值，有的话继续调用自己，没有的话则跳出
        for (int i = 0; i < children.size() - 1; i++) {
            if (children.size() > 0) {
                lists.add(children.get(i).get("id").toString());
                diGui((List<JSONObject>) children.get(i).get("children"), (String) children.get(i).get("id"));
            } else {
                break;
            }
        }

        children.forEach(m -> {

            if (children.size() > 0) {
                lists.add(m.get("id").toString());
                diGui((List<JSONObject>) m.get("children"), (String) m.get("id"));
            } else {
                return;
            }
        });

        return lists;
    }
}

