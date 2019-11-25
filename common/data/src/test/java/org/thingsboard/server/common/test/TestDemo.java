package org.thingsboard.server.common.test;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TestDemo {
//    @Test
//    public void testFuturesTransform() throws ExecutionException, InterruptedException {
//        ListenableFuture listenableFuture = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "张三";
//            }
//        });
//        AsyncFunction<String, Person> asyncFunction = new AsyncFunction<String, Person>() {
//            @Override
//            public ListenableFuture<Person> apply(String input) throws Exception {
//                person.setName(input);
//                return executorService.submit(new Callable<Person>() {
//                    @Override
//                    public Person call() throws Exception {
//                        return person;
//                    }
//                });
//            }
//        };
//        ListenableFuture<Person> lf = Futures.transform(listenableFuture, asyncFunction);
//        assertThat(lf.get().getName(), is("张三"));
//    }
}
