package com.example.network.utils.xcpparser;

import com.example.network.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * xcp过滤接口实现类
 */
public class XcpFilterImpl implements XcpFilter {

    private String text;

    public final static String[] REXS = {"/\\*{1,2}[\\s\\S]*?\\*/", "//[\\s\\S]*?\\n"};

    // 读取xcp文档返回字符串
    public XcpFilterImpl(String fileUrl) throws IOException {
        // 获取文件的字符流
        BufferedReader bufferedReader =  FileUtils.getBufferedReader(fileUrl, "a2l");
        StringBuffer buff = new StringBuffer();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            buff.append(temp);
        }
        bufferedReader.close();
        text = buff.toString().trim();
    }

    @Override
    public String filter() {
        return filter(REXS);
    }

    @Override
    public String filter(String[] regex) {
        String result = text;
        for (String reg : regex) {
            result = result.replaceAll(reg, "");
        }
        return result;
    }
}
