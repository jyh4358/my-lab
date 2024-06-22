package com.jddng.domain.configuration_properties.config.properties;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "s3")
@Getter
@RequiredArgsConstructor
public class S3InnerProperty {
    private final String accessKey;
    private final String secretKey;
    private final BucketProperty bucket;

    @Getter
    @RequiredArgsConstructor
    public static class BucketProperty {
        private final List<String> stage;
        private final String production;
    }
}
