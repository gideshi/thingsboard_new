package org.thingsboard.server.common.data;

public class SimpleApplicationService {
    public String query() throws InterruptedException {
        System.out.println("开始查询");
        Thread.sleep(2000);
        return "query result";
    }
}

