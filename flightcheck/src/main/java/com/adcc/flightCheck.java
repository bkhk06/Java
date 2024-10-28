package com.adcc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */


public class flightCheck 
{
    public static void main( String[] args )
    {
        // 创建一个ScheduledExecutorService实例
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        // 创建一个Runnable任务
        Runnable task = new checkTask();

        // 安排任务每5分钟执行一次
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.MINUTES);
    }
       
}



   

