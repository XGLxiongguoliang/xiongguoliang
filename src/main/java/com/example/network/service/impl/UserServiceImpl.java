package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.UserInfo;
import com.example.network.mapper.UserMapper;
import com.example.network.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageObject<UserInfo> queryPageList(PageObject<UserInfo> pageObject) {
        PageHelper.startPage(pageObject.getPageCurrent(), pageObject.getPageSize());

        List<UserInfo> userList = userMapper.queryAll();

        PageInfo<UserInfo> pageInfo = new PageInfo(userList);

        pageObject.setRowCount(pageInfo.getTotal());
        pageObject.setPageCurrent(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setRecords(userList);

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
    public UserInfo queryUserByAccount(String account) {
        return userMapper.queryUserByAccount(account);
    }
}
