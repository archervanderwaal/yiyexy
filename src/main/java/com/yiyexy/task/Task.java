package com.yiyexy.task;

import com.yiyexy.util.log.MyLogger;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by stormma on 2017/5/8.
 */
@Component
@EnableScheduling
public class Task {


    private static final Logger LOGGER = MyLogger.getLogger(Task.class);

    @Scheduled(cron = "0 49 20 ? * MON")
    public void task() {
        LOGGER.info("听说今天是周日");
    }
}
