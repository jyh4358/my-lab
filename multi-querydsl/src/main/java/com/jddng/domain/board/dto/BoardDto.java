package com.jddng.domain.board.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

  private Long boardId;

  private String title;

  private String content;

  private Long categoryId;

  private String categoryName;

  private long likeCount;

  private boolean likeYn;

  private String tagStr;

  private LocalDateTime createAt;
}
