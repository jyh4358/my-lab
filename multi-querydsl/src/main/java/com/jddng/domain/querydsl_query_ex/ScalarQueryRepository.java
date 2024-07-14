package com.jddng.domain.querydsl_query_ex;

import static com.jddng.domain.board.QBoard.board;
import static com.jddng.domain.category.QCategory.category;
import static com.jddng.domain.wish.QWish.wish;
import static com.querydsl.jpa.JPAExpressions.select;

import com.jddng.domain.board.dto.BoardDto;
import com.jddng.domain.wish.enum_type.WishStatus;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@RequiredArgsConstructor
public class ScalarQueryRepository {

  private final JPAQueryFactory queryFactory;

  /**
   * <pre>
   *   스칼라 쿼리 사용방법
   *   com.querydsl.core.types.dsl.Expressions 사용
   * </pre>
   */
  public List<BoardDto> 스칼라_쿼리() {
    return queryFactory.select(Projections.fields(BoardDto.class,
            board.boardId,
            board.title,
            board.content,
            category.categoryId,
            category.categoryName,
            Expressions.as(
                select(wish.count())
                    .from(wish)
                    .where(
                        wish.board.eq(board),
                        wish.wishStatus.eq(WishStatus.WISH)
                    )
                    .isNotNull(), "likeCount"
            ),
            Expressions.as(
                select()
                    .from(wish)
                    .where(
                        wish.board.eq(board),
                        wish.wishStatus.eq(WishStatus.WISH),
                        likeYnEq(1L)
                    )
                    .isNotNull(), "likeYn"
            ),
            board.createAt
        ))
        .from(board)
        .innerJoin(board.category, category)
        .fetch();
  }

  /**
   * <pre>
   *   특정 값이 없을 경우 null을 반환해야 해야 할 때
   *   Expression<T>를 이용하여 null을 반환 할 수 있다.
   * </pre>
   */
  public List<BoardDto> 스칼라_쿼리_null_반환() {
    return queryFactory.select(Projections.fields(BoardDto.class,
            new CaseBuilder()
                .when(category.categoryName.isNotEmpty())
                .then(category.categoryName)
                .otherwise((Expression<String>) null)
                .as("categoryName")
        ))
        .from(board)
        .innerJoin(board.category, category)
        .fetch();
  }

  /**
   * <pre>
   *   스칼라 쿼리의 별칭을 NumberPath<T>를 이용하여 다른 절에서 사용할 수 있다.
   *   아래 예시는 orderBy에서 사용
   * </pre>
   */
  public List<BoardDto> numberPath를_이용한_활용() {
    NumberPath<Long> likeCount = Expressions.numberPath(Long.class, "likeCount");
    return queryFactory.select(Projections.fields(BoardDto.class,
            Expressions.as(
                select(wish.count())
                    .from(wish)
                    .where(
                        wish.board.eq(board),
                        wish.wishStatus.eq(WishStatus.WISH)
                    ), likeCount
            )
        ))
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(likeCount.desc())
        .fetch();
  }


  private BooleanExpression likeYnEq(Long memberId) {
    return !ObjectUtils.isEmpty(memberId) ? wish.member.memberId.eq(memberId) : null;
  }
}
