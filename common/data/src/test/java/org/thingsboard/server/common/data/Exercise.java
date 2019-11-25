package org.thingsboard.server.common.data;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Exercise {

    private SimpleApplicationService simpleApplicationService = new SimpleApplicationService();

    @Test
    public void futureTest() throws InterruptedException, ExecutionException {
        long st = System.currentTimeMillis();
        //这里本是一callable接口实现，我用lambda方便一点
        FutureTask result = new FutureTask<String>(() -> simpleApplicationService.query());
        //记住我们的任务都与需要开启一个新的线程
        Thread t = new Thread(result);
        t.start();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - st) + " 结果：" + result.get());
    }
}

