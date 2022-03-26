package org.zerock.partThree.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Reply;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository repository;

    @Test
    @DisplayName("댓글더미데이터 생성")
    void insertReplyDummies(){

        IntStream.rangeClosed(1,300).forEach(i->{

            Board board = Board.builder().bno((long)(Math.random()*100)+1).build();

            Reply reply = Reply.builder()
                    .replier("Replier"+i)
                    .text("Guest")
                    .board(board)
                    .build();

            repository.save(reply);
        });
    }
}