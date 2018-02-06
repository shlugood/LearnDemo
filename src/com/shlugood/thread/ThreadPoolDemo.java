package com.shlugood.thread;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public void demoRun(){

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is a simple task");
            }
        });

        Future future =  executorService.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    long timeNow = System.currentTimeMillis();
                    System.out.println("now is : " + timeNow);
                    Thread.sleep(2000);
                    System.out.println("runnable finished!");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            future.get();
            long timeNow = System.currentTimeMillis();
            System.out.println("after get, now is : " + timeNow);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("submit runnable;");

        Future<String> future2 = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                try {
                    long timeNow = System.currentTimeMillis();

                    System.out.println("start callable is : " + timeNow);
                    Thread.sleep(2000);
                    timeNow = System.currentTimeMillis();
                    return "callable returned" + timeNow;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return "";
                }
            }

        });

        try {
            String ret = future2.get();
            System.out.println("get callable return : " + ret);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
