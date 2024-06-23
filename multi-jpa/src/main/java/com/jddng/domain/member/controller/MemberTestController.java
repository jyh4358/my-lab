package com.jddng.domain.member.controller;

import com.jddng.domain.member.MemberTest;
import com.jddng.domain.member.repository.MemberTestRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberTestController {

  private final MemberTestRepository memberTestRepository;


  @GetMapping
  @Transactional
  public ResponseEntity<String> saveMembers() {

    List<MemberTest> memberTestList = new ArrayList<>();
    memberTestList.add(
        MemberTest.builder()
            .firstName("홍")
            .lastName("길동")
            .build()
    );

    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );
    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );
    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );
    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );
    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );
    memberTestList.add(
        MemberTest.builder()
            .firstName("김")
            .lastName("춘향")
            .build()
    );

    memberTestRepository.saveAll(memberTestList);

    return ResponseEntity.ok("ok");
  }

}
