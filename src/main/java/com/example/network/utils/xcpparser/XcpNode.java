package com.example.network.utils.xcpparser;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class XcpNode implements Serializable {
    // 标题(xcp文件中对应的标签起始行)
    private String title;
    // 名称(xcp文件中对应的标签起始行中/begin后紧跟着的字符串)
    private String name;
    // 标签以及标签间的正文内容
    private String allContext;
    // 标签间的所有非子标签的内容
    private String noSonNodeContext;

    // 该节点下的子节点对象集合
    private List<XcpNode> childNodes = new LinkedList<>();

    public XcpNode() {
    }

    // 输出完整的节点字符串也用到了递归
    @Override
    public String toString() {
        if (childNodes.isEmpty() && noSonNodeContext == null) {
            return title + "\n" + "/end " + name;
        } else if (childNodes.isEmpty() && noSonNodeContext != null) {
            return title + "\n" + noSonNodeContext + "\n" + "/end " + name;
        } else {
            StringBuffer buff = new StringBuffer();
            if (!noSonNodeContext.isEmpty()) {
                buff.append(noSonNodeContext + "\n");
            }
            for (XcpNode n : childNodes) {
                buff.append(n.toString());
            }
            buff.append("/end" + name);
            return buff.toString();
        }
    }
}
