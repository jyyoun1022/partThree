package org.zerock.partThree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.partThree.dto.ReplyDTO;
import org.zerock.partThree.entity.Board;
import org.zerock.partThree.entity.Reply;
import org.zerock.partThree.repository.ReplyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto) {

        Reply reply = dtoToEntity(dto);
        Reply result = replyRepository.save(reply);

        return result.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result = replyRepository.readReply(bno);

        return result.stream().map(reply ->entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO dto) {
        Reply reply = dtoToEntity(dto);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);
    }
}
