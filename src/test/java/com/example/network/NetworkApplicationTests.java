package com.example.network;

import com.example.network.domain.UserRelation;
import com.example.network.service.UserRelationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class NetworkApplicationTests {

    @Autowired
    private UserRelationService userRelationService;

    @Test
    void contextLoads() {
    }

    @Test
    void getUserRelationListByUser() {
        Map<String, List> result = userRelationService.getUserRelationListByUser(8);
        System.out.println(result);
    }
}
