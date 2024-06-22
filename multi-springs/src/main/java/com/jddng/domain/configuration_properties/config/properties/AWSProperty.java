package com.jddng.domain.configuration_properties.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
@Getter
@RequiredArgsConstructor
public class AWSProperty {
    private final S3Property s3;
    private final RegionProperty region;
}
