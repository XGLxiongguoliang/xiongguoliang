package com.example.network.controller;

import com.example.network.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program com.example.demo.controller
 * @description 公共
 * @auther Mr.Xiong
 * @create 2020-03-07 12:36
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping("/uploadFile")
    public void uploadFile() {
        commonService.uploadFile();
    }

    @ResponseBody
    @RequestMapping("/importCSV")
    public void importCSV(MultipartFile file) {
        commonService.importCSV(file);
    }
}

