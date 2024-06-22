package com.jddng.domain.configuration_properties;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jddng.SpringsApplication;
import com.jddng.domain.configuration_properties.config.properties.AWSProperty;
import com.jddng.domain.configuration_properties.config.properties.S3InnerProperty;
import com.jddng.domain.configuration_properties.config.properties.S3Property;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringsApplication.class)
@SpringBootTest
class PropertiesConfigurationTest {

  @Value("${aws.s3.access-key}")
  private String accessKey;

  @Value("${aws.s3.secret-key}")
  private String secretKey;

  @Value("${aws.s3.bucket}")
  private String bucket;

  /**
   * Property들을 @Value 어노테이션을 이용하여 가져올 수 있다.
   * 해당 방법은 여러 곳에서 사용할 경우 Property의 경로가 변경이 될 경우
   * 사용하는 모든 곳에 수정을 해야하기 때문에 유지보수가 어려워진다는 단점이 있다.
   */
  @DisplayName("@Value 어노테이션 사용")
  @Test
  void property_test1() {
    Assertions.assertThat(accessKey).isEqualTo("access-key value");
    Assertions.assertThat(secretKey).isEqualTo("secret-key value");
    Assertions.assertThat(bucket).isEqualTo("bucket value");
  }

  @Value("${test-property.value1}")
  private boolean booleanValue;

  @Value("${test-property.value1}")
  private String stringValue1;

  @Value("${test-property.value2}")
  private int intValue;

  @Value("${test-property.value2}")
  private String stringValue2;

  /**
   * 컴파일 시 타입 검증이 이루어지지 않기 때문에
   * 잘못된 타입의 값이 주입될 경우 런타임 오류가 발생할 수 있다.
   */
  @DisplayName("@Value 어노테이션 타입 안정성 문제")
  @Test
  void property_test2() {
    // boolean, String type으로 값을 가져올 수 있다.
    Assertions.assertThat(booleanValue).isTrue();
    Assertions.assertThat(stringValue1).isEqualTo("true");

    // int, String type으로 값을 가져올 수 있다.
    Assertions.assertThat(intValue).isEqualTo(1234);
    Assertions.assertThat(stringValue2).isEqualTo("1234");
  }

  @Autowired
  private S3Property s3Property;

  @Autowired
  private AWSProperty awsProperty;

  @Autowired
  private S3InnerProperty s3InnerProperty;

  /**
   * Property 값을 자바 클래스의 필드에 매핑하면서 타입 변환과 검증을 자동으로 처리하므로,
   * 타입 안전성을 보장하며 클래스로 관리하기 때문에 유지보수가 용이하다.
   * 또한 중첩 구조와 복잡한 타입들을 모두 지원한다.
   *
   * 그 외에도 유효성 검사 및 기본값을 설정할 수 있다.
   */
  @DisplayName("@ConfigurationProperties 어노테이션 사용")
  @Test
  void property_test3() {
    Assertions.assertThat(s3Property.getAccessKey()).isEqualTo("access-key value");
    Assertions.assertThat(s3Property.getSecretKey()).isEqualTo("secret-key value");
    Assertions.assertThat(s3Property.getBucket()).isEqualTo("bucket value");
  }

  @DisplayName("@ConfigurationProperties 어노테이션 사용 - 중첩구조")
  @Test
  void property_test4() {
    Assertions.assertThat(awsProperty.getS3().getAccessKey()).isEqualTo("access-key value");
    Assertions.assertThat(awsProperty.getS3().getSecretKey()).isEqualTo("secret-key value");
    Assertions.assertThat(awsProperty.getS3().getBucket()).isEqualTo("bucket value");
    Assertions.assertThat(awsProperty.getRegion().getRegionStatic()).isEqualTo("region-static value");
  }

  @DisplayName("@ConfigurationProperties 어노테이션 사용 - 복잡한 데이터 구조(List)")
  @Test
  void property_test5() {
    Assertions.assertThat(s3InnerProperty.getAccessKey()).isEqualTo("access-key value");
    Assertions.assertThat(s3InnerProperty.getSecretKey()).isEqualTo("secret-key value");
    Assertions.assertThat(s3InnerProperty.getBucket().getStage()).contains("stage-bucket value 1", "stage-bucket value 2");
    Assertions.assertThat(s3InnerProperty.getBucket().getProduction()).isEqualTo("production-bucket value");
  }

}