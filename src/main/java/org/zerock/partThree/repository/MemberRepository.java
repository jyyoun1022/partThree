package org.zerock.partThree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.partThree.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {


}
