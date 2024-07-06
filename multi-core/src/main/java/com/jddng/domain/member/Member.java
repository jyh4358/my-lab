package com.jddng.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column(columnDefinition = "char(50) comment '닉네임'", nullable = false)
  private String nickName;

  @Column(columnDefinition = "int default 0 comment '나이'", nullable = false)
  private int age;

  @UpdateTimestamp
  @Column(nullable = false, columnDefinition = "datetime comment '수정일'")
  private LocalDateTime updateAt;

  @Column(nullable = false, updatable = false, columnDefinition = "datetime comment '등록일'")
  @CreationTimestamp
  private LocalDateTime createAt;

}
