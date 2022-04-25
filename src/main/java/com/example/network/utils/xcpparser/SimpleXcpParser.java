package com.example.network.utils.xcpparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleXcpParser implements XcpParser {
    @Override
    public List<String> parser(String text) {
        List<String> childrenDocs = new ArrayList<>();
        // 捕获根节点中间的文本
        Pattern p = Pattern.compile("<.*?>(.*)<.*?>");
        Matcher m = p.matcher(text);
        if (m.find()) {
            String inner = m.group(1);
            // 匹配节点字符串
            p = Pattern.compile("<(.*?)>");
            m = p.matcher(inner);
            while (m.find()) {
                String s1 = m.group(1);
                // 如果不是结束节点，则表示需要查找对应的闭合节点
                 if (!s1.startsWith("/end")) {
                    // 计算起始字符数
                    int start = m.end() - m.group().length();
                    // 如果捕获到未闭合节点则index++，如果捕获到闭合节点则index--
                    int index = 1;
                    while (m.find()) {
                        String s2 = m.group(1);
                        if (!s2.startsWith("/end")) {
                            index++;
                        } else if (s2.startsWith("/end")) {
                            index--;
                        }
                        // 找到符合条件的闭合节点则循环终止
                        if (index == 0) {
                            break;
                        }
                    }
                    // 计算结束字符数
                    int end = m.end();
                    // 截取对应字符串
                    childrenDocs.add(inner.substring(start, end));
                }
            }
        }
        return childrenDocs;
    }
}
