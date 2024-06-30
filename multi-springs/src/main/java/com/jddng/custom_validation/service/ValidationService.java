package com.jddng.custom_validation.service;

import com.jddng.custom_validation.dto.CustomConstraintV0Request;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {


  public void customCustraintV0(CustomConstraintV0Request request) {

//    throw new RuntimeException("exception 테스트");
    try {

      Thread.sleep(1000);
    } catch (Exception e) {

    }
  }
}
