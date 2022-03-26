package org.zerock.partThree.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 더미데이터 생성")
    void insertBoardDummies(){

        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().emil("jyyoun"+i+"@naver.com").build();

            Board board = Board.builder()
                    .title("Title"+i)
                    .content("Content"+i)
                    .member(member)
                    .build();

            boardRepository.save(board);
        });
    }
}
