package com.jddng.domain.configuration_properties.config.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
@Getter
@Setter
@RequiredArgsConstructor
public class S3Property {
    // 기본값 설정 가능
    // 단점은 Setter가 필요하기 때문에 불변 객체로 사용할 수 없다.
    private String accessKey = "default value"; // 기본값 설정
    // 유효성 검사 가능
    @NotNull
    private String secretKey;
    private String bucket;
}
