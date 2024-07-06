package com.jddng.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware {
  @Override
  public Optional getCurrentAuditor() {
    return Optional.ofNullable(0L);
  }
}
