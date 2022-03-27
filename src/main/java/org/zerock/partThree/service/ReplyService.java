package org.zerock.partThree.service;

import org.zerock.partThree.dto.ReplyDTO;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO dto);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO dto);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO dto){

        Board board = Board.builder().bno(dto.getBno()).build();

        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replier(dto.getReplier())
                .board(board)
                .build();

        return reply;
    }

    default ReplyDTO entityToDto(Reply reply){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replier(reply.getReplier())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return replyDTO;
    }
}
