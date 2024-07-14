package com.jddng.domain.querydsl_query_ex;

import static com.jddng.domain.board.QBoard.board;
import static com.jddng.domain.category.QCategory.category;

import com.jddng.domain.board.Board;
import com.jddng.domain.board.enum_type.BoardOrderType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderingRepository {

  private final JPAQueryFactory queryFactory;

  /**
   * <pre>
   *   특정 조건으로 순서 정렬을 하고 싶을 때
   *   CaseBuilder를 이용하여 정렬을 할 수 있다.
   *   아래 예시는 이렇게 사용할 수 있다라는 것을 보여주는 코드로
   *   별 의미없는 코드이다.
   * </pre>
   * @return
   */
  public List<Board> caseBuilder를_이용한_순서_정렬() {
    return queryFactory
        .select(board)
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(
            new CaseBuilder()
                .when(board.title.contains("첫번쨰"))
                .then(1)
                .when(board.title.contains("두번쨰"))
                .then(2)
                .otherwise(3)
                .asc()
        )
        .fetch();
  }

  /**
   * <pre>
   *   특정 조건에 따라 ordering 해야 하는 경우 OrderSpecifier를 이용할 수 있다.
   * </pre>
   */
  public List<Board> 특정조건에_따른_순서_정렬_OrderSpecifier_이용(BoardOrderType boardOrderType) {
    return queryFactory
        .select(board)
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(
            boardOrderBy(boardOrderType)
        )
        .fetch();
  }

  /**
   * <pre>
   *   특정 조건에 따라 ordering 해야 하는 경우 OrderSpecifier<?>[]를 이용할 수 있다.
   * </pre>
   */
  public List<Board> 특정조건에_따른_순서_정렬_OrderSpecifier_배열_이용(BoardOrderType boardOrderType) {
    return queryFactory
        .select(board)
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(
            boardOrdersBy(boardOrderType)
        )
        .fetch();
  }











  private OrderSpecifier<?>[] boardOrdersBy(BoardOrderType boardOrderType) {
    return switch (boardOrderType) {
      case UPDATE -> new OrderSpecifier[]{
          board.updateAt.desc(),
          board.boardId.desc()
      };
      case TITLE_ASC -> new OrderSpecifier[]{
          board.title.asc(),
          board.boardId.desc()
      };
      case TITLE_DESC -> new OrderSpecifier[]{
          board.title.desc(),
          board.boardId.desc()
      };
      default -> new OrderSpecifier[]{
          board.createAt.desc()
      };
    };
  }


  private OrderSpecifier boardOrderBy(BoardOrderType boardOrderType) {
    return switch (boardOrderType) {
      case UPDATE -> board.updateAt.desc();
      case TITLE_ASC -> board.title.asc();
      case TITLE_DESC -> board.title.desc();
      default -> board.createAt.desc();
    };
  }
}
