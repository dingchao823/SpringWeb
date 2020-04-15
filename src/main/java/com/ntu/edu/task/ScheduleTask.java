package com.ntu.edu.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleTask {

    private int count = 0;

    @Scheduled(fixedDelay = 100000)
    @Async(value = "executor1")
    public void countTask(){
        log.info("ScheduleTask >>>> 定时任务执行 " + (count++) + ", 执行在" + Thread.currentThread().getName());
    }

    @Scheduled(fixedDelay = 100000)
    @Async(value = "executor2")
    public void countTask2(){
        log.info("ScheduleTask2 >>>> 定时任务执行 " + (count++) + ", 执行在" + Thread.currentThread().getName());
    }

}
