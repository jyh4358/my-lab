package com.jddng.custom_validation.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * - @Pattern을 이용한 Validation
 */
@Getter
public class CustomConstraintV1Request {

    @Pattern(regexp = "^\\S+$", message = "이름에 공백 문자가 없어야합니다")
    private String name;
}
