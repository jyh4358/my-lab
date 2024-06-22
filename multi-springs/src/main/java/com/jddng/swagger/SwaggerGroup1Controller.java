package com.jddng.swagger;


import com.jddng.swagger.dto.CreateMemberRequest;
import com.jddng.swagger.dto.SearchMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group1")
@Tag(name = "그룹1 멤버", description = "SwaggerGroup1Controller")
public class SwaggerGroup1Controller {

  @Operation(summary = "회원 목록 조회")
  @GetMapping("/members")
  public ResponseEntity<SearchMember> getMemberList(
      @ParameterObject @ModelAttribute SearchMember searchMember
  ) {
    return ResponseEntity.ok(searchMember);
  }

  @Operation(summary = "회원 상세 조회")
  @GetMapping("/members/{memberId}")
  public ResponseEntity<String> getMember(
      @Parameter(description = "회원 고유 id", example = "1")
      @PathVariable Long memberId
  ) {
    return ResponseEntity.ok("memberId : " + memberId);
  }

  @Operation(summary = "회원 생성")
  @PostMapping("/members")
  public ResponseEntity<CreateMemberRequest> saveMember(
      @Valid @RequestBody CreateMemberRequest request
  ) {
    return ResponseEntity.ok(request);
  }

}
