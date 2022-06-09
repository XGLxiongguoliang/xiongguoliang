package com.example.network.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program com.example.demo.service
 * @description common service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Service
public interface CommonService {

    void uploadFile();

    void importCSV(MultipartFile file);
}
