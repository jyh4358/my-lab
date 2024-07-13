package com.jddng.domain.tag;

import com.jddng.domain.board.Board;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Tag {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  @Column(columnDefinition = "varchar(100) comment '태그명'", nullable = false)
  private String tagName;

  @Column(nullable = false, updatable = false, columnDefinition = "datetime comment '등록일'")
  @CreationTimestamp
  private LocalDateTime createAt;

  @Builder
  public Tag(Board board, String tagName) {
    this.board = board;
    this.tagName = tagName;
  }
}
