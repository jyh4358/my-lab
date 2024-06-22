package com.jddng.configuration_properties.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aws.region")
@Getter
@RequiredArgsConstructor
public class RegionProperty {
    private final String regionStatic;
}
