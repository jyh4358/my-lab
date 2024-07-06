package com.jddng.domain.wish;

import com.jddng.domain.board.Board;
import com.jddng.domain.wish.enum_type.WishStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Wish {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long wishId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @Enumerated(EnumType.STRING)
//  @Column(columnDefinition = "char(50) default 'WISH' comment '좋아요 상태", nullable = false)
  private WishStatus wishStatus;

  @UpdateTimestamp
  @Column(nullable = false, columnDefinition = "datetime comment '수정일'")
  private LocalDateTime updateAt;

  @Column(nullable = false, updatable = false, columnDefinition = "datetime comment '등록일'")
  @CreationTimestamp
  private LocalDateTime createAt;

  @Builder
  public Wish(Board board) {
    this.board = board;
  }
}
