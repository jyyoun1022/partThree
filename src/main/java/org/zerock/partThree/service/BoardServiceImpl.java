package org.zerock.partThree.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.partThree.dto.BoardDTO;
import org.zerock.partThree.dto.PageRequestDTO;
import org.zerock.partThree.dto.PageResultDTO;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Member;
import org.zerock.partThree.repository.BoardRepository;
import org.zerock.partThree.repository.ReplyRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        boardRepository.save(dtoToEntity(dto));
        return dtoToEntity(dto).getBno();
    }

    @Override
    public PageResultDTO<Object[], BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        Function<Object[],BoardDTO>fn =(entityArr-> entityToDto((Board) entityArr[0],(Member) entityArr[1],(Long) entityArr[2]));

//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        Page<Object[]> result = boardRepository.searchPage(pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;

        BoardDTO boardDTO = entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);

        return boardDTO;
    }
    @Transactional
    @Override
    public void removeWithReply(Long bno) {

        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno);

    }

    @Override
    public void modify(BoardDTO dto) {

        Optional<Board> result = boardRepository.findById(dto.getBno());
        if(result.isPresent()){
            Board board = result.get();
            board.changeTitle(dto.getTitle());
            board.changeContent(dto.getContent());

            boardRepository.save(board);
        }

    }
}
