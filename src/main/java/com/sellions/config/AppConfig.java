package com.sellions.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AppConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private JobExecutionListener interceptingJobExecution;

    @Autowired
    public AppConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, JobExecutionListener interceptingJobExecution) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.interceptingJobExecution = interceptingJobExecution;
    }

    @Bean
    public Job job(@Qualifier("step1") Step step1,
                   @Qualifier("step2") Step step2) {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .listener(interceptingJobExecution)
                .build();
    }

    @Bean
    protected Step step1(ItemReader<String> reader,
                         ItemProcessor<String, String> processor,
                         ItemWriter<String> writer) {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    protected Step step2(Tasklet tasklet) {
        return stepBuilderFactory.get("step2")
                .tasklet(tasklet)
                .build();
    }

}
