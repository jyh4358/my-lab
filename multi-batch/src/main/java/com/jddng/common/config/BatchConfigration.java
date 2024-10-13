package com.jddng.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfigration {

  /**
   * Deprecated JobBuilderFactory, StepBuilderFactory
   */


  @Bean
  public Job job(JobRepository jobRepository, Step step1, Step step2) {
    return new JobBuilder(JobType.JOB1.getJob(), jobRepository)
        .start(step1)
        .next(step2)
        .build();
  }

  @Bean
  public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(StepType.STEP1.getStep(), jobRepository)
        .tasklet(new Tasklet() {
          @Override
          public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
              throws Exception {
            System.out.println("hello tasklet 1");

            return RepeatStatus.FINISHED;
          }
        }, transactionManager)
        .build();
  }

  @Bean
  public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder(StepType.STEP2.getStep(), jobRepository)
        .tasklet((contribution, chunkContext) -> {
          System.out.println("hello tasklet 2");
          return RepeatStatus.FINISHED;
        } , transactionManager)
        .build();

  }
}
