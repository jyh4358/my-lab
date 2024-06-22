package com.jddng.https_data_binding;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/https-data-binding/path-variable/members")
public class PathVariableBindingController {

  /**
   * <p>PathVariable 과 binding 되는 Parameter 명이 다를 경우</p>
   * <p>name(=value) 생략 가능</p>
   *
   * @param memberId
   * @return
   */
  @GetMapping("/{member_id}")
  public ResponseEntity<String> getMemberDetailV0(
      @PathVariable(name = "member_id") Long memberId
//            @PathVariable("member_id") Long memberId
  ) {
    return ResponseEntity.ok("Member id = " + memberId);
  }

  /**
   * <p>PathVariable 과 binding 되는 Parameter 명이 같을 경우 자동으로 Binding</p>
   *
   * @param memberId
   * @return
   */
  @GetMapping("/{memberId}")
  public ResponseEntity<String> getMemberDetailV2(
      @PathVariable Long memberId
  ) {
    return ResponseEntity.ok("Member id = " + memberId);
  }
}
