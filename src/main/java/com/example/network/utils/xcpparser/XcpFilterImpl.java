package com.example.network.utils.xcpparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * xcp过滤接口实现类
 */
public class XcpFilterImpl implements XcpFilter {

    private String text;

    public final static String[] REXS = {"/*.*/", "//.*"};

    // 读取xcp文档返回字符串
    public XcpFilterImpl(File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuffer buff = new StringBuffer();
        String temp;
        while ((temp = in.readLine()) != null) {
            buff.append(temp);
        }
        in.close();
        text = buff.toString().trim();
    }

    @Override
    public String filter() {
        return null;
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
