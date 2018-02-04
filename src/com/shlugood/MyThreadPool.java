package com.shlugood;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//如何使用阻塞队列实现
public class MyThreadPool <T> {
    private BlockingQueue<MyTask> mBlockingQueue = null;
    private List<Thread> mThreadList = null;
    private boolean isRunning = true;
    public MyThreadPool(int threadCount, int maxTaskCount){
        mBlockingQueue = new ArrayBlockingQueue<MyTask>(5);
        for(int i = 0; i < threadCount; i++){
            mThreadList.add(new MyThread(mBlockingQueue));
        }
    }

    public synchronized void execute(MyTask myTask){
        this.mBlockingQueue.offer(myTask);
    }
}

class MyThread  extends Thread{
    private BlockingQueue<MyTask> taskQueue = null;
    public MyThread(BlockingQueue<MyTask> taskQueue){
        this.taskQueue = taskQueue;
    }
    @Override
    public void run() {
        while (true){
            MyTask myTask = taskQueue.poll();
            myTask.excute();
        }
    }
}

interface MyTask{
    public void excute();
}
