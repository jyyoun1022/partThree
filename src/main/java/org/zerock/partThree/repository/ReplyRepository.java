package org.zerock.partThree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.partThree.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
