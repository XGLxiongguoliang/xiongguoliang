package com.example.network.mapper;

import com.example.network.domain.Hobby;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HobbyMapper {

    List<Hobby> queryAll();

    void batchDelete(List<Integer> ids);

    void batchUpdate(List<Integer> ids);

    void addHobby(Hobby hobby);

    void updateHobby(Hobby hobby);
}
