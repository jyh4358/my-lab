package com.jddng.common.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

//  @Bean
  public Job job(JobRepository jobRepository, Step step1, Step step2) {
    return new JobBuilder(JobType.JOB1.getJob(), jobRepository)
        .start(step1)
        .next(step2)
        .build();
  }


}
