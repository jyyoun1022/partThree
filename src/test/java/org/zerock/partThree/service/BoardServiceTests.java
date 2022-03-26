package org.zerock.partThree.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.partThree.dto.BoardDTO;
import org.zerock.partThree.dto.PageRequestDTO;
import org.zerock.partThree.dto.PageResultDTO;

import java.util.List;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    void testRegister(){

        BoardDTO dto = BoardDTO.builder().title("Test").content("TitleTests").writerEmail("jyyoun100@naver.com").build();
        service.register(dto);
    }
    @Test
    void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<Object[], BoardDTO> result = service.getList(pageRequestDTO);

        List<BoardDTO> dtoList = result.getDtoList();

        for (BoardDTO boardDTO : dtoList) {
            System.out.println(boardDTO);
        }
    }
    @Test
    void testGet(){

        BoardDTO boardDTO = service.get(100L);
        System.out.println(boardDTO);
    }

    @Test
    void testRemoveWithReply(){

        service.removeWithReply(100L);
    }
}
