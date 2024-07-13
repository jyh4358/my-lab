package com.jddng.domain.board;

import com.jddng.domain.category.Category;
import com.jddng.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Board {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @Column(columnDefinition = "varchar(500) comment '제목'", nullable = false)
  private String title;

  @Column(columnDefinition = "longtext comment '내용'", nullable = false)
  private String content;

  @UpdateTimestamp
  @Column(name = "update_at", nullable = false, columnDefinition = "datetime comment '수정일'")
  private LocalDateTime updateAt;

  @Column(name = "create_at", nullable = false, updatable = false, columnDefinition = "datetime comment '등록일'")
  @CreationTimestamp
  private LocalDateTime createAt;

  @Builder

  public Board(Category category, Member member, String title, String content) {
    this.category = category;
    this.member = member;
    this.title = title;
    this.content = content;
  }
}
