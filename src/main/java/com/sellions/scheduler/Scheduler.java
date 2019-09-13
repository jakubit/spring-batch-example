package com.sellions.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final JobLauncher launcher;
    private final Job job;

    @Autowired
    public Scheduler(JobLauncher launcher, Job job) {
        this.launcher = launcher;
        this.job = job;
    }

    @Scheduled(fixedDelay = 15_000)
    public void triggerJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        System.out.println("LAUNCHING JOB!");
        launcher.run(job, new JobParameters());
    }
}
