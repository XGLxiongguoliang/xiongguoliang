package com.example.network.mapper;

import com.example.network.domain.UserRelation;
import com.example.network.domain.vo.UserRelationVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRelationMapper {

    void addUserRelation(UserRelation userrelation);

    void batchDelete(List<Integer> ids);

    void batchUpdate(List<Integer> ids);

    List<UserRelation> getUserRelationList();

    List<UserRelation> selectUserRelationByMasterId(Integer masterId);

    List<UserRelationVO> getUserRelationPageList();

}
