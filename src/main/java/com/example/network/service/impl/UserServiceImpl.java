package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.UserInfo;
import com.example.network.mapper.UserMapper;
import com.example.network.service.KafkaService;
import com.example.network.service.UserService;
import com.example.network.utils.ExcelSheet;
import com.example.network.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaService kafkaService;

    @Override
    public PageObject<UserInfo> queryPageList(PageObject<UserInfo> pageObject) {
        PageHelper.startPage(pageObject.getPageCurrent(), pageObject.getPageSize());

        List<UserInfo> userList = userMapper.queryAll();

        PageInfo<UserInfo> pageInfo = new PageInfo(userList);

        pageObject.setRowCount(pageInfo.getTotal());
        pageObject.setPageCurrent(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setRecords(userList);

        kafkaService.sendMessage(null, null);

        return pageObject;
    }

    public List<UserInfo> queryAllUserList() {
        return userMapper.queryAll();
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        userMapper.batchUpdate(ids);
    }

    @Override
    public void addUser(UserInfo user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(UserInfo user) {
        userMapper.updateUser(user);
    }

    @Override
    public UserInfo authUser(UserInfo user) {
        return userMapper.getUserByAccountAndPassword(user);
    }

    @Override
    public UserInfo queryUserByAccount(String username) {
        return userMapper.queryUserByAccount(username);
    }

    public void exportUserList(HttpServletResponse response) throws IOException {
        List<UserInfo> userInfoList = userMapper.queryAll();

        List<List<String>> listTemp = new ArrayList<>();
        List<String> headerList = new ArrayList<>();
        headerList.add("姓名");
        headerList.add("年龄");
        headerList.add("用户名");
        listTemp.add(headerList);

        userInfoList.forEach(n -> {
            List<String> objectList = new ArrayList<>();
            objectList.add(n.getName());
            objectList.add(n.getAge() + "");
            objectList.add(n.getUsername());
            listTemp.add(objectList);
        });

        ExcelUtil excelUtil = new ExcelUtil();
        String sheetName = "sheet1";
        String fileName = "用户列表";

        excelUtil.exportExcel(response, listTemp, sheetName, fileName, 3);
    }
}
