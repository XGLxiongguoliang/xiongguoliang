package com.example.network.mapper;

import com.example.network.domain.Relation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationMapper {

    List<Relation> queryAll();

    void batchDelete(List<Integer> ids);

    void batchUpdate(List<Integer> ids);

    void addRelation(Relation relation);

    void updateRelation(Relation relation);
}
