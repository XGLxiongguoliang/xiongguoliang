package com.example.network.utils.xcpparser;

import java.util.List;

/*
 * 解析器可以对一段完整的父节点字符串提供解析服务。
 * 将一条父节点的字符串解析成为多条子节点字符串
 */
public interface XcpParser {
    // 解析一段父节点，返回子节点字符串
    List<String> parser(String str);
}
