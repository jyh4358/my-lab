package com.jddng.domain.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Category {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @Column(columnDefinition = "varchar(100) comment '카테고리명'", nullable = false, unique = true)
  private String categoryName;

  @UpdateTimestamp
  @Column(name = "update_at", nullable = false, columnDefinition = "datetime comment '수정일'")
  private LocalDateTime updateAt;

  @Column(name = "create_at", nullable = false, updatable = false, columnDefinition = "datetime comment '등록일'")
  @CreationTimestamp
  private LocalDateTime createAt;

  @Builder
  public Category(String categoryName) {
    this.categoryName = categoryName;
  }
}
