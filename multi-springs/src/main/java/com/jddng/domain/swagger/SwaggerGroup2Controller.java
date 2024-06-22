package com.jddng.domain.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group2")
public class SwaggerGroup2Controller {

  @GetMapping("/members")
  public ResponseEntity<String> getMemberList() {
    return ResponseEntity.ok("member list");
  }

  @GetMapping("/members/{memberId}")
  public ResponseEntity<String> getMember(
      @PathVariable Long memberId
  ) {
    return ResponseEntity.ok("memberId : " + memberId);
  }
}
