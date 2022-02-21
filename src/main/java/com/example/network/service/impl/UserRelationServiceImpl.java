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
        Map<String, List> resultMap = new HashMap<>();

        List<UserInfo> users = new ArrayList<UserInfo>();
        List<UserRelation> userRelations = new ArrayList<UserRelation>();

        List<UserInfo> userList = userMapper.queryAll();
        List<UserRelation> userRelationList = userRelationMapper.getUserRelationList();
        List<Relation> relationList = relationMapper.queryAll();

        Map<String, UserRelation> userRelationMap = new HashMap<String, UserRelation>();
        Map<String, UserRelation> tempUserRelationMap = new HashMap<String, UserRelation>();
        Map<Integer, UserInfo> userMap = new HashMap<Integer, UserInfo>();
        Map<Integer, String> relationMap = new HashMap<Integer, String>();

        userRelationList.forEach(item -> {
            userRelationMap.put(item.getId() + "-" + item.getMasterId(), item);
        });
        userList.forEach(item -> {
            userMap.put(item.getId(), item);
        });
        relationList.forEach(item -> {
            relationMap.put(item.getId(), item.getName());
        });

        findUserRelations(users, userRelations, userRelationMap, userMap, id, tempUserRelationMap);
        resultMap.put("users", users);

        List<UserRelationVO> userRelationVOList = new ArrayList<UserRelationVO>();
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

    private void findUserRelations(List<UserInfo> users, List<UserRelation> userRelations, Map<String, UserRelation> userRelationMap, Map<Integer, UserInfo> userMap, Integer id, Map<String, UserRelation> tempUserRelationMap) {
        List<UserRelation> tempList = userRelationMapper.selectUserRelationByMasterId(id);
        if (tempList.isEmpty()) {
            return;
        } else {
            tempList.forEach(temp -> {
                if (userRelationMap.containsKey(temp.getId() + "-" + id)) {
                    addUserAndUserRelation(temp, users, userRelations, userRelationMap, userMap, id, tempUserRelationMap);
                    Iterator entries = userRelationMap.entrySet().iterator();
                    while (entries.hasNext()) {
                        Map.Entry entry = (Map.Entry) entries.next();
                        UserRelation userRelation = (UserRelation) entry.getValue();
                        if (temp.getServantId() == userRelation.getMasterId()) {
                            addUserAndUserRelation(userRelation, users, userRelations, userRelationMap, userMap, userRelation.getMasterId(), tempUserRelationMap);
                            this.findUserRelations(users, userRelations, userRelationMap, userMap, temp.getServantId(), tempUserRelationMap);
                        } else if (temp.getServantId() == userRelation.getServantId()) {
                            if (tempUserRelationMap.containsKey(temp.getId() + "-" + userRelation.getServantId())) {
                                continue;
                            }
                            addUserAndUserRelation(userRelation, users, userRelations, userRelationMap, userMap, userRelation.getServantId(), tempUserRelationMap);
                            this.findUserRelations(users, userRelations, userRelationMap, userMap, temp.getServantId(), tempUserRelationMap);
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }

            });
        }
    }

    private void addUserAndUserRelation(UserRelation temp, List<UserInfo> users, List<UserRelation> userRelations, Map<String, UserRelation> userRelationMap, Map<Integer, UserInfo> userMap, Integer id, Map<String, UserRelation> tempUserRelationMap) {
        if (userRelationMap.get(temp.getId() + "-" + id) != null) {
            userRelations.add(userRelationMap.get(temp.getId() + "-" + id));
            tempUserRelationMap.put(temp.getId() + "-" + id, userRelationMap.get(temp.getId() + "-" + id));
        }
        userRelationMap.remove(userRelationMap.get(temp.getId() + "-" + id));
        if (userMap.containsKey(temp.getMasterId())) {
            users.add(userMap.get(temp.getMasterId()));
            userMap.remove(temp.getMasterId());
        }
        if (userMap.containsKey(temp.getServantId())) {
            users.add(userMap.get(temp.getServantId()));
            userMap.remove(temp.getServantId());
        }
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
