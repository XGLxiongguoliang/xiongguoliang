package com.example.network.utils.xcpparser;

import java.io.IOException;

public class TestDemo {
    public static void main(String[] args) {
        XcpFilter filter = null;
        try {
            filter = new XcpFilterImpl("https://jfx.qdfaw.com/fs/group5/M00/3B/6C/CkXFtmJg-C-AWtcNAAAE46O_iOo095.a2l");
        } catch (IOException e) {
            e.printStackTrace();
        }

        XcpParser parser = new XcpParserImpl();
        XcpNodeBuilder builder = new XcpNodeBuilder(parser, filter);
        XcpNode node = builder.getRoot();
        System.out.println(node);
    }
}
