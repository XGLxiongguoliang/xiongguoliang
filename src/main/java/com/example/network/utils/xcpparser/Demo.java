package com.example.network.utils.xcpparser;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\50299\\Desktop\\testxcp001.a2l");
        XcpFilter filter = null;
        try {
            filter = new SimpleXcpFilter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XcpParser parser = new SimpleXcpParser();
        NodeBuilder builder = new NodeBuilder(parser, filter);
        Node node = builder.getRoot();
        System.out.println(node);
    }
}
