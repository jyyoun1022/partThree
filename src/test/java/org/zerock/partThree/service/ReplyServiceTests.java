package org.zerock.partThree.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.partThree.dto.ReplyDTO;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService service;

    @Test
    void 등록(){
        ReplyDTO replyDTO = ReplyDTO.builder().text("test").replier("testsUser").bno(97L).build();
        Long register = service.register(replyDTO);
        System.out.println(register);
    }
    @Test
    void testGetList(){
        List<ReplyDTO> result = service.getList(97L);

        result.forEach(replyDTO -> System.out.println(replyDTO));
    }

}
