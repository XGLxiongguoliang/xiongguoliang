package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.Hobby;
import com.example.network.mapper.HobbyMapper;
import com.example.network.service.HobbyService;
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
public class HobbyServiceImpl implements HobbyService {

    @Autowired
    private HobbyMapper hobbyMapper;

    @Override
    public PageObject<Hobby> queryAll(PageObject<Hobby> pageObject) {
        PageHelper.startPage(pageObject.getPageCurrent(), pageObject.getPageSize());

        List<Hobby> hobbyList = hobbyMapper.queryAll();

        PageInfo<Hobby> pageInfo = new PageInfo(hobbyList);

        pageObject.setRowCount(pageInfo.getTotal());
        pageObject.setPageCurrent(pageInfo.getPageNum());
        pageObject.setPageSize(pageInfo.getPageSize());
        pageObject.setRecords(hobbyList);

        return pageObject;
    }

    @Override
    public void deleteHobby(List<Integer> ids) {
        hobbyMapper.batchUpdate(ids);
    }

    @Override
    public void addHobby(Hobby hobby) {
        hobbyMapper.addHobby(hobby);
    }

    @Override
    public void updateHobby(Hobby hobby) {
        hobbyMapper.updateHobby(hobby);
    }
}
