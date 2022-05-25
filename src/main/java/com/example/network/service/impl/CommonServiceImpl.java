package com.example.network.service.impl;

import com.example.network.common.PageObject;
import com.example.network.domain.UserInfo;
import com.example.network.mapper.UserMapper;
import com.example.network.service.CommonService;
import com.example.network.service.KafkaService;
import com.example.network.service.UserService;
import com.example.network.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.demo.service
 * @description common service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaService kafkaService;

    @Override
    public void uploadFile() {

    }
}
