package org.zerock.partThree.service;

import org.springframework.data.domain.PageRequest;
import org.zerock.partThree.dto.BoardDTO;
import org.zerock.partThree.dto.PageRequestDTO;
import org.zerock.partThree.dto.PageResultDTO;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<Object[],BoardDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReply(Long bno);

    void modify(BoardDTO dto);



    default Board dtoToEntity(BoardDTO dto){

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board board,Member member,Long replyCount){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
