package com.shlugood;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo {

    static int shareValue = 0;
    public static void ReentrantLockTest(){
        Lock lock = new ReentrantLock();
        for(int i = 0; i< 4; i++){
            Thread thread = new Thread("thread" + i){
                @Override
                public void run() {
                    while (true){
                        if(!lock.tryLock()){
                            System.out.println(this.getName()+"not get locked");
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        };
                        try{
                            Thread.sleep(900);
                            shareValue++;
                            System.out.println(this.getName() + " : " + shareValue);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }

                }
            };
            thread.start();
        }


    }

    public static void semaphoreTest(){
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0;i<6;i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    while (true){
                        try {
                            semaphore.acquire();
                            System.out.println(this.getId() + ":" + "i am running");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            semaphore.release();
                        }
                    }
                }
            };
        }
    }


    private static int rwShareValue = 0;
    public static void reentrantReadWriteLockTest() {
        ReadWriteLock readLock = new ReentrantReadWriteLock();
        //read threads,
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            readLock.readLock().lock();
                            Thread.sleep(1000);
                            System.out.println(this.getId() + " : " + rwShareValue);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            readLock.readLock().unlock();
                        }
                    }
                }
            };
            thread.start();
        }
        //write thread;
        Thread wThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        readLock.writeLock().lock();
                        rwShareValue++;
                        Thread.sleep(1000);
                        System.out.println(this.getId() + " : " + rwShareValue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readLock.writeLock().unlock();
                    }
                }
            }

        };
    }

    public static void countDownLatchTest(){
        int threadCount = 4;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        new Thread("last thread"){
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("other thread finished!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        for(int i = 0;i < threadCount; i++){
            new Thread("thread index : " + i){
                @Override
                public void run() {
                    System.out.println(this.getName() + "finished!");
                    countDownLatch.countDown();
                }
            }.start();
        }
    }


    public static void cyclicBarrierTest(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for(int i = 0; i < 4; i++){
            new Thread(String.format("[thread %s]", i)){
                @Override
                public void run() {
                    try {
                        System.out.println(this.getName() + "step 1 finished!");
                        cyclicBarrier.await();
                        System.out.println(this.getName() + "step 2 finished!");
                        cyclicBarrier.await();
                        System.out.println(this.getName() + "step 3 finished!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }


}
