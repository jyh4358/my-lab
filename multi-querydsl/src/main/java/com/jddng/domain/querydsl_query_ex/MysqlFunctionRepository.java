package com.jddng.domain.querydsl_query_ex;

import static com.jddng.domain.board.QBoard.board;
import static com.jddng.domain.category.QCategory.category;
import static com.jddng.domain.tag.QTag.tag;
import static com.jddng.domain.wish.QWish.wish;
import static com.querydsl.jpa.JPAExpressions.select;

import com.jddng.domain.board.dto.BoardDto;
import com.jddng.domain.wish.enum_type.WishStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
@RequiredArgsConstructor
public class MysqlFunctionRepository {

  private final JPAQueryFactory queryFactory;

  /**
   * <pre>
   *   Hibernate는 특정 데이터베이스에 종속되지 않고 객체지향스럽게 사용할 수 있도록
   *   추상화해주어 특정 DB에 종속된 함수(예, MySQL의 group_concat)는
   *   제공하지 않는다.
   *   때문에 Hibernate에서 제공하지 않고 특정 DB에서 제공하는 함수를 사용하려면
   *   Expressions.stringTemplate을 이용하여 사용해야 한다.
   *
   *   참고로 이전 Qeurydsl 5, hibernate 6 이하 버전에서는 직접 함수들을
   *   등록해주고 사용해야 하는 번거로움이 있었지만 현재는 내장되어 있다.
   * </pre>
   *
   */
  public List<BoardDto> mySql_내장함수_사용방법_group_concat() {
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
                    ), "likeCount"
            ),
            Expressions.as(
                select(wish)
                    .from(wish)
                    .where(
                        wish.board.eq(board),
                        wish.wishStatus.eq(WishStatus.WISH),
                        likeYnEq(1L)
                    )
                    .isNotNull(), "likeYn"
            ),
            Expressions.as(
                select(Expressions.stringTemplate("group_concat({0})", tag.tagName))
                    .from(tag)
                    .where(
                        tag.board.eq(board)
                    ), "tagStr"
            ),
            board.createAt
        ))
        .from(board)
        .innerJoin(board.category, category)
        .fetch();
  }

  public List<BoardDto> mySql_내장함수_사용방법_rand() {
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
                    ), "likeCount"
            ),
            Expressions.as(
                select(wish)
                    .from(wish)
                    .where(
                        wish.board.eq(board),
                        wish.wishStatus.eq(WishStatus.WISH),
                        likeYnEq(1L)
                    )
                    .isNotNull(), "likeYn"
            ),
            Expressions.as(
                select(Expressions.stringTemplate("group_concat({0})", tag.tagName))
                    .from(tag)
                    .where(
                        tag.board.eq(board)
                    ), "tagStr"
            ),
            board.createAt
        ))
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
        .fetch();
  }

  public List<BoardDto> mySql_내장함수_사용방법_substring() {
    return queryFactory.select(Projections.fields(BoardDto.class,
            Expressions.stringTemplate("function('substring', {0}, {1}, {2})", board.title, 1, 3).as("title")

        ))
        .from(board)
        .innerJoin(board.category, category)
        .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
        .fetch();
  }

  private BooleanExpression likeYnEq(Long memberId) {
    return !ObjectUtils.isEmpty(memberId) ? wish.member.memberId.eq(memberId) : null;
  }
}
