package org.zerock.partThree.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    @Test
   @Transactional
    void testRead1(){
        Optional<Board> result = boardRepository.findById(10L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getMember());
    }
    @Test
    void testRead12(){
        Object result = boardRepository.getBoardWithMember(100L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }
    @Test
    void testRead3(){
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
    @Test
    void testReadList(){
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        List<Object[]> list = result.getContent();
        list.forEach(i-> System.out.println(Arrays.toString(i)));
    }
    @Test
    void testReadMain(){

        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }
}
