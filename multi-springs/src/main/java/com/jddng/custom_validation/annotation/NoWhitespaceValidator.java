package com.jddng.custom_validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class NoWhitespaceValidator implements ConstraintValidator<NoWhitespace, Object> {

    @Override
    public void initialize(NoWhitespace constraintAnnotation) {
        // 초기화 로직이 필요하지 않은 경우 생략 가능
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // null 값은 검증하지 않음
        }

        // String type 검증 처리
        if (value instanceof String) {
            return !((String) value).contains(" "); // 공백 문자가 포함되지 않은 경우 true 반환
        }

        // List type 검증 처리, null 값은 검증하지 않음
        if (value instanceof List<?>) {
            List<?> list = (List<?>) value;

            for (Object element : list) {
                if (element != null && element.toString().contains(" ")) {
                    return false; // 공백 문자가 포함된 요소가 있으면 검증 실패
                }
            }

            return true;
        }
        return true;
    }
}
