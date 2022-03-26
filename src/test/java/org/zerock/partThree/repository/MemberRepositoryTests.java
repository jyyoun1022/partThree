package org.zerock.partThree.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.partThree.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버더미데이터 생성")
    void insertMemberDummies(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .emil("jyyoun"+i+"@naver.com")
                    .password("1111")
                    .name("USER"+i)
                    .build();

            memberRepository.save(member);
        });
    }
}
