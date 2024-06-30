package com.jddng.custom_validation;

import com.jddng.custom_validation.dto.CustomConstraintV0Request;
import com.jddng.custom_validation.dto.CustomConstraintV1Request;
import com.jddng.custom_validation.dto.CustomConstraintV2Request;
import com.jddng.custom_validation.service.ValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ValidationController {

  private final ValidationService validationService;

  @PostMapping("/v0/custom-constraint")
  public ResponseEntity<String> customConstraintV0(
      @Valid @RequestBody CustomConstraintV0Request request
  ) {
    validationService.customCustraintV0(request);
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/v1/custom-constraint")
  public ResponseEntity<String> customConstraintV1(
      @Valid @RequestBody CustomConstraintV1Request request
  ) {
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/v2/custom-constraint")
  public ResponseEntity<String> customConstraintV2(
      @Valid @RequestBody CustomConstraintV2Request request
  ) {
    return ResponseEntity.ok("ok");
  }
}
