package org.zerock.partThree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.partThree.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
