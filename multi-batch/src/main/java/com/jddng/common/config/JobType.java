package com.jddng.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {

  JOB1("job1"),

  ;

  private String job;
}
