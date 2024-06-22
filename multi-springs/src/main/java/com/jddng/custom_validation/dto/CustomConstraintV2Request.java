package com.jddng.custom_validation.dto;

import com.jddng.custom_validation.annotation.NoWhitespace;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Validation 어노테이션 직접 만들어서 사용
 */
@Getter
public class CustomConstraintV2Request {

    @NoWhitespace
    private String name;

    @NoWhitespace
    private List<String> nameList = new ArrayList<>();

}
