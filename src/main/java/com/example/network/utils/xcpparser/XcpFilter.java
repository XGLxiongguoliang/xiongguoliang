package com.example.network.utils.xcpparser;

/**
 * xcp文件过滤器，过滤一些注释文字
 */
public interface XcpFilter {
    /**
     * 无匹配规则过滤方法
     * @return
     */
    String filter();

    /**
     * 提供自定义表达式，识别符合过滤条件的字符串
     * @param regex
     * @return
     */
    String filter(String[] regex);
}
