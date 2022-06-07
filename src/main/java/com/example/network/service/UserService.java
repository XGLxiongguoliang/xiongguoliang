package com.example.network.service;

import com.example.network.common.PageObject;
import com.example.network.domain.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public interface UserService {
    PageObject<UserInfo> queryPageList(PageObject<UserInfo> pageObject);

    List<UserInfo> queryAllUserList();

    void deleteUser(List<Integer> ids);

    void addUser(UserInfo user);

    void updateUser(UserInfo user);

    UserInfo authUser(UserInfo user);

    UserInfo queryUserByAccount(String username);

    void exportUserList(HttpServletResponse response) throws IOException;
}
