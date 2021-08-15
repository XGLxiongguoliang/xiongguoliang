package com.example.network.service;

import com.example.network.common.PageObject;
import com.example.network.domain.UserRelation;
import com.example.network.domain.vo.UserRelationVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public interface UserRelationService {
    void addUserRelation(UserRelation userRelation);

    Map<String, List> getUserRelationListByUser(Integer id);

    Map<String, List> getUserRelationListAll();

    PageObject<UserRelationVO> getUserRelationPageList(PageObject<UserRelationVO> pageObject);

    void deleteUserRelation(List<Integer> ids);
}
