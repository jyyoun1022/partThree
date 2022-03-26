package org.zerock.partThree.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.partThree.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    //게시물에 대한 멤버
    @Query("select b,m from Board b left join b.member m where b.bno = :bno")
    Object getBoardWithMember(@Param("bno")Long bno);
    //게시물에 대한 댓글
    @Query("select b,r from Board b left join Reply r on r.board=b where b.bno =:bno")
    List<Object[]> getBoardWithReply(@Param("bno")Long bno);
    //목록처리
    @Query(value = "select b,m,count(r) from Board b " +
            "left join b.member m " +
            "left join Reply r on r.board=b " +
            "group by b",
    countQuery = "select count(b) from Board b ")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b,m,count(r) from Board b " +
            "left join b.member m " +
            "left join Reply r on r.board=b " +
            "where b.bno =:bno")
    Object getBoardByBno(@Param("bno")Long bno);




}
