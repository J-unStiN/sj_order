package org.junstin.sjorders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junstin.sjorders.member.domain.Member;
import org.junstin.sjorders.member.domain.Role;
import org.junstin.sjorders.member.repository.MemberRepository;
import org.junstin.sjorders.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    @DisplayName("맴버회원가입")
    public void 맴버회원가입() {
        Member member = Member.builder()
                .name("이강인")
                .email("Lee@naver.com")
                .address("손흥민집")
                .password("1234")
                .role(Role.USER)
                .orderings(new ArrayList<>())
                .build();
        memberRepository.save(member);

        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(2).isEqualTo(all.size());
    }

    @Test
    @DisplayName("맴버찾기")
    public void 맴버찾기() {

        Member findMember = memberService.findMember(1L);

        Assertions.assertThat("김철수").isEqualTo(findMember.getName());
    }

}