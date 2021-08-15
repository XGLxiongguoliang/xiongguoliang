package com.example.network.mapper;

import com.example.network.domain.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

    List<UserInfo> queryAll();

    void batchDelete(List<Integer> ids);

    void batchUpdate(List<Integer> ids);

    void addUser(UserInfo user);

    void updateUser(UserInfo user);

    UserInfo getUserByAccountAndPassword(UserInfo user);

    UserInfo queryUserByAccount(String account);
}
