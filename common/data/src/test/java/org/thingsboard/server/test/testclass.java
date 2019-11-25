package org.thingsboard.server.test;

import org.junit.Test;

public class testclass {
    @Test
    public void findPrices(){
        BestFinder bestFinder = new BestFinder();
        long st = System.currentTimeMillis();
        System.out.println(bestFinder.findPrices("iPhonX"));
        System.out.println("findPrices done : " + (System.currentTimeMillis() - st) + "msecs");
    }
    @Test
    public void findPricesParallel(){
        BestFinder bestFinder = new BestFinder();
        long st = System.currentTimeMillis();
        System.out.println(bestFinder.findPricesParallel("iPhonX"));
        System.out.println("findPricesParallel done : " + (System.currentTimeMillis() - st) + "msecs");
    }
    @Test
    public void findPricesAsync(){
        BestFinder bestFinder = new BestFinder();
        long st = System.currentTimeMillis();
        System.out.println(bestFinder.findPricesAsync("iPhonX"));
        System.out.println("findPricesAsync done : " + (System.currentTimeMillis() - st) + "msecs");
    }
}
