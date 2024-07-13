package com.jddng.domain.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.jddng.domain.board.Board;
import com.jddng.domain.board.dto.BoardDto;
import com.jddng.domain.category.Category;
import com.jddng.domain.category.repository.CategoryRepository;
import com.jddng.domain.member.Member;
import com.jddng.domain.member.repository.MemberRepository;
import com.jddng.domain.tag.Tag;
import com.jddng.domain.tag.repository.TagRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class QueryBoardRepositoryTest {

  @Autowired
  BoardRepository boardRepository;

  @Autowired
  TagRepository tagRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  QueryBoardRepository queryBoardRepository;


  @Rollback(value = false)
  @Test
  void test() {
    Category category = categoryRepository.save(
        Category.builder()
            .categoryName("테스트 카테고리")
            .build()
    );

    Member member = memberRepository.save(
        Member.builder()
            .age(33)
            .nickName("JDong")
            .build()
    );

    Board board = boardRepository.save(
        Board.builder()
            .category(category)
            .member(member)
            .title("타이틀")
            .content("내용")
            .build()
    );

    tagRepository.save(
        Tag.builder()
            .board(board)
            .tagName("태그1")
            .build()
    );
    tagRepository.save(
        Tag.builder()
            .board(board)
            .tagName("태그2")
            .build()
    );

    List<BoardDto> boardDtos = queryBoardRepository.mySql_내장함수_사용방법_group_concat();

    System.out.println("boardDtos = " + boardDtos);

  }

}