package com.buptcnc.projectmanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class Schedule {

//    @Scheduled(cron = "*/5 * * * * ?")
//    private void testTask() {
//        log.info("每隔五秒自动执行的定时任务");
//    }

    @Scheduled(cron = "0 0 1 * * ?")
    private void daily() {
        log.info("现在是凌晨1点，系统自动维护中");
    }
}
