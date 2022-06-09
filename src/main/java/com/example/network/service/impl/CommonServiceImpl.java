package com.example.network.service.impl;

import com.example.network.service.CommonService;
import com.example.network.utils.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @program com.example.demo.service
 * @description common service
 * @auther Mr.Xiong
 * @create 2020-03-07 13:10
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    private static final String DIR = "C:\\Users\\50299\\Desktop\\csv";

    @Override
    public void uploadFile() {

    }

    public void importCSV(MultipartFile file) {
        log.info("importCSV --- START");

        log.info("获取CSV文件中的所有表头加数据");
        Map<String, Object> resultMap = CsvUtils.getCsvDataMethod2Strong(file);

        log.info("获取CSV文件中的所有表头");
        List<Object> header = (List<Object>) resultMap.get("header");

        log.info("获取CSV文件中的所有数据");
        List<List<Object>> data = (List<List<Object>>) resultMap.get("data");

        log.info("导出新的CSV文件");
        CsvUtils.createCSVFile(header, data, DIR, file.getOriginalFilename().split("\\.")[0]);

        log.info("importCSV --- END");
    }
}
