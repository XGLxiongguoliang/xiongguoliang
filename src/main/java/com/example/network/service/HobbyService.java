package com.example.network.service;

import com.example.network.common.PageObject;
import com.example.network.domain.Hobby;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public interface HobbyService {
    PageObject<Hobby> queryAll(PageObject<Hobby> pageObject);

    void deleteHobby(List<Integer> ids);

    void addHobby(Hobby hobby);

    void updateHobby(Hobby hobby);
}
