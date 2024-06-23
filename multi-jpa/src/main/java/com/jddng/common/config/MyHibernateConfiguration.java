package com.jddng.common.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyHibernateConfiguration {

  @Bean
  PhysicalNamingStrategyStandardImpl caseSensitivePhysicalNamingStrategy() {
    return new PhysicalNamingStrategyStandardImpl();
  }
}
