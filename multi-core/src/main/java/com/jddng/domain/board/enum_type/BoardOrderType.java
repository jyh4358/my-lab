package com.jddng.domain.board.enum_type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardOrderType {

  LATEST,
  UPDATE,
  TITLE_ASC,
  TITLE_DESC,
  ;
}
