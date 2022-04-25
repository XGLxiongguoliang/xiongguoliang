package com.example.network.utils.xcpparser;

/*
 * 过滤器的作用是删除文件中不重要的部分。
 * 通常都是一些注释性文字，不需要被机器解析。
 */
public interface XcpFilter {
    String filter();

    // 提供自定义正则表达式，识别符合过滤条件的字符串
    String filter(String[] regex);
}
