package com.jddng.domain.swagger.dto;

import com.jddng.domain.swagger.enum_type.MemberType;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SearchMember {

  @Parameter(description = "이름", example = "11")
  private String name;

  @Parameter(description = "나이", required = true)
  private int age;

  @Parameter(description = "회원 타입", required = true)
  private MemberType memberType;
}
