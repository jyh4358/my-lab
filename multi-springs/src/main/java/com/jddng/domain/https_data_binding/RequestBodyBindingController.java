package com.jddng.domain.https_data_binding;

import com.jddng.domain.https_data_binding.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestBodyBindingController {

  /**
   * <p>JSON이나 XML 형식의 데이터를 memberDto 객체에 바인딩</p>
   * <p>내부에서 ObjectMapper를 통해 객체에 데이터를 바인딩이 이루어진다.
   * ObjectMapper는 바인딩할 객체(dto)의 기본 생성자가 필요하며 reflection을 통해 필드에 값을 넣어주기 때문에
   * Getter or Setter 중 1개만 있어도 데이터가 바인딩이 된다</p>
   *
   * @param memberDto
   */
  @PostMapping("/request-body/members")
  public ResponseEntity<String> saveMemberV0(
      @RequestBody MemberDto memberDto
  ) {
    return ResponseEntity.ok("Member info : " + memberDto);
  }
}
