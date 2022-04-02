package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.Relation;
import com.example.network.domain.UserInfo;
import com.example.network.domain.UserRelation;
import com.example.network.domain.vo.UserRelationVO;
import com.example.network.mapper.RelationMapper;
import com.example.network.mapper.UserMapper;
import com.example.network.mapper.UserRelationMapper;
import com.example.network.service.UserRelationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program com.example.demo.service
 * @description user service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationMapper userRelationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public void addUserRelation(UserRelation userRelation) {
        userRelationMapper.addUserRelation(userRelation);
    }

    @Override
    public Map<String, List> getUserRelationListByUser(Integer id) {
        //总得返回结果
        Map<String, List> resultMap = new HashMap<>();

        //查询全量用户
        List<UserInfo> users = userMapper.queryAll();
        //查询全量关系
        List<Relation> relations = relationMapper.queryAll();

        //涉及到该查询用户的关系
        List<UserRelation> userRelations = userRelationMapper.selectUserRelationByUserId(id);

        //创建返回的用户集和用户相关的关系集
        List<UserRelationVO> userRelationVOList = new ArrayList<>();
        List<UserInfo> resultUsers = new ArrayList<>();

        Map<Integer, String> relationMap = new HashMap<>();
        relations.forEach(item -> {
            relationMap.put(item.getId(), item.getName());
        });

        List<Integer> somePeople = new ArrayList<>();
        userRelations.forEach(item -> {
            // 整合用户关系
            UserRelationVO userRelationVO = new UserRelationVO();
            BeanUtils.copyProperties(item, userRelationVO);
            userRelationVOList.add(userRelationVO);

            // 整合相关用户
            if (!somePeople.contains(item.getMasterId())) {
                somePeople.add(item.getMasterId());
            }
            if (!somePeople.contains(item.getServantId())) {
                somePeople.add(item.getServantId());
            }
        });

        users.forEach(a -> {
            if (somePeople.contains(a.getId())) {
                resultUsers.add(a);
            }
        });

        userRelationVOList.forEach(item -> {
            item.setRelationName(relationMap.get(item.getRelationId()));
        });

        resultMap.put("users", resultUsers);
        resultMap.put("userRelationVOList", userRelationVOList);

        return resultMap;
    }

    @Override
    public Map<String, List> getUserRelationListAll() {
        Map<String, List> resultMap = new HashMap<>();

        List<UserInfo> users = userMapper.queryAll();
        List<UserRelation> userRelations = userRelationMapper.getUserRelationList();
        List<Relation> relations = relationMapper.queryAll();

        List<UserRelationVO> userRelationVOList = new ArrayList<UserRelationVO>();

        Map<Integer, String> relationMap = new HashMap<Integer, String>();

        relations.forEach(item -> {
            relationMap.put(item.getId(), item.getName());
        });

        resultMap.put("users", users);
        userRelations.forEach(item -> {
            UserRelationVO userRelationVO = new UserRelationVO();
            BeanUtils.copyProperties(item, userRelationVO);
            userRelationVOList.add(userRelationVO);
        });
        userRelationVOList.forEach(item -> {
            item.setRelationName(relationMap.get(item.getRelationId()));
        });
        resultMap.put("userRelationVOList", userRelationVOList);
        return resultMap;
    }

    @Override
    public PageObject<UserRelationVO> getUserRelationPageList(PageObject<UserRelationVO> pageObject) {
        PageHelper.startPage(pageObject.getPageCurrent(), pageObject.getPageSize());

        List<UserRelationVO> userRelationVOList = userRelationMapper.getUserRelationPageList();

        PageInfo<UserInfo> pageInfo = new PageInfo(userRelationVOList);

        pageObject.setRowCount(pageInfo.getTotal());
        pageObject.setPageCurrent(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setRecords(userRelationVOList);

        return pageObject;
    }

    @Override
    public void deleteUserRelation(List<Integer> ids) {
        userRelationMapper.batchUpdate(ids);
    }
}
