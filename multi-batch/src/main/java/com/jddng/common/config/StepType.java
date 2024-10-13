package com.jddng.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StepType {

  STEP1("step1"),
  STEP2("step2"),

  ;

  private String step;
}
