package com.example.network.utils.xcpparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class SimpleXcpFilter implements XcpFilter {
    private String text;
    // 常用的过滤正则表达式
    public final static String[] REG = {"/\\*{1,2}[\\s\\S]*?\\*/", "//[\\s\\S]*?\\n"};

    // 读取文档返回字符串
    public SimpleXcpFilter(File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuffer buff = new StringBuffer();
        String temp;

        Pattern pattern = Pattern.compile("(/begin\\s+\\S+)|(/end\\s+\\S+)");

        while ((temp = in.readLine()) != null) {

            if (pattern.matcher(temp).find()) {
                temp = "<" + temp.trim() + ">";
            }

            buff.append(temp);
        }
        in.close();
        text = buff.toString().trim();
    }

    @Override
    public String filter() {
        return filter(REG);
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
