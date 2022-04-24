package com.example.network.utils.xcpparser;

import java.util.List;

/**
 * 解析器接口，解析一个父节点下的子节点集合，并返回一条统一的字符串
 */
public interface XcpParser {
    /**
     * 解析一段父节点，返回其子节点集合组成的字符串
     * @param str
     * @return
     */
    List<String> parser(String str);
}
