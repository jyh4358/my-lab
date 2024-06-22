package com.jddng.domain.https_data_binding;

import com.jddng.domain.https_data_binding.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/https-data-binding/query-parameter")
public class QueryParameterBindingController {

  /**
   * <p>QueryParameter 와 Binding 되는 Parameter 명이 다를 경우</p>
   * <p>name(=value) 생략 가능</p>
   *
   * @param name
   */
  @GetMapping("/v0/members")
  public ResponseEntity<String> getMemberDetailV0RequestParam(
      @RequestParam(name = "memberName") String name
//            @RequestParam("memberName") String name
  ) {
    return ResponseEntity.ok("Member info : " + name);
  }

  /**
   * <p>QueryParameter 와 Binding 되는 Parameter 명이 같을 경우 자동으로 Binding</p>
   * <p>@RequestParam 생략 시 required, defaultValue 와 같은 속성을 사용할 수 없으니 참고 </p>
   *
   * @param name
   * @param age
   * @see RequestParam @RequestParam 생략 가능
   */
  @GetMapping("/v1/members")
  public ResponseEntity<String> getMemberDetailV2RequestParam(
      @RequestParam String name,
      @RequestParam int age
  ) {
    return ResponseEntity.ok("Member info : " + name + ", " + age);
  }

  /**
   * <p>QueryParameter 와 객체의 필드명이 같을 경우 자동으로 Binding</p>
   * <p>QueryParameter 의 수가 많을 때 유용</p>
   * <p>QueryParameter Binding 시 주의점
   *     <ul>
   *         <li>생성자가 2개 이상인 경우 Setter를 이용하여 Binding한다.</ol>
   *         <li>생성자가 1개인 경우
   *             <ol>
   *                 <li>기본 생성자 : 기본 생성자로 객체 생성 후 Setter를 이용하여 바인딩</li>
   *                 <li>매개변수를 가진 생성자 : : 매개변수를 가진 생성자로 요청 파라미터 값을 바인딩한 객체를 생성 후 Setter를 이용하여 재 바인딩</li>
   *             </ol>
   *         </li>
   *     </ul>
   * </p>
   *
   * @param memberDto name, age
   * @see ModelAttribute @ModelAttribute 생략 가능
   */
  @GetMapping("/v2/members")
  public ResponseEntity<String> getMemberDetailV0ModelAttribute(
      @ModelAttribute MemberDto memberDto
  ) {
    return ResponseEntity.ok("Member info : " + memberDto);
  }
}
