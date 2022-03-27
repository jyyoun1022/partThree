package org.zerock.partThree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.partThree.entity.Reply;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(@Param("bno")Long bno);

    //게시물로 댓글 목록 가져오기
   @Query("select r from Reply r where r.board.bno=:bno")
    List<Reply> readReply(@Param("bno")Long bno);
}
