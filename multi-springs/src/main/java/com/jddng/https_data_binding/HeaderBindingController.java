package com.jddng.https_data_binding;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/https-data-binding")
public class HeaderBindingController {

  /**
   * <p>Header Key 값과 Binding 되는 Parameter 명이 다를 경우</p>
   * <p>참고 - 대소문자 구분하지 않는다</p>
   * <p>name(=value) 생략 가능</p>
   *
   * @param token
   * @return
   */
  @GetMapping("/v0/request-header")
  public ResponseEntity<String> getMemberDetailV0RequestHeader(
      @RequestHeader(name = "Authorization") String token
//            @RequestHeader("Authorization") String token
  ) {
    return ResponseEntity.ok("Token info : " + token);
  }

  /**
   * <p>Header Key 값과 Binding 되는 Parameter 명이 같을 경우</p>
   * <p>참고 - 대소문자 구분하지 않는다</p>
   *
   * @param authorization
   * @return
   */
  @GetMapping("/v2/request-header")
  public ResponseEntity<String> getMemberDetailV2RequestHeader(
      @RequestHeader String authorization
  ) {
    return ResponseEntity.ok("Token info : " + authorization);
  }
}
