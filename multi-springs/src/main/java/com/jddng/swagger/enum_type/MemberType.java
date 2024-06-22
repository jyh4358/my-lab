package com.jddng.swagger.enum_type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(enumAsRef = true)
public enum MemberType {

    MEMBER("회원"),
    ADMIN("관리자"),
    ;

    private String desc;

}
