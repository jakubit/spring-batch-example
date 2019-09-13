package com.sellions.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class InterceptingJobExecution implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptingJobExecution.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("JOB STARTED");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("JOB FINISHED");
    }
}
