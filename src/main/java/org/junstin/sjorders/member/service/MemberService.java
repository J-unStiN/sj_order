package org.junstin.sjorders.member.service;

import org.junstin.sjorders.exception.GlobalException;
import org.junstin.sjorders.member.domain.Member;
import org.junstin.sjorders.member.dto.MemberCreateReqDto;
import org.junstin.sjorders.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void memberCreate(MemberCreateReqDto member) {
        Member createMember = Member.builder()
                .name(member.name())
                .email(member.email())
                .password(member.password())
                .address(member.address())
                .role(member.role())
                .orderings(new ArrayList<>())
                .build();

        memberRepository.save(createMember);
    }

    public List<Member> memberAllList() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("없는 아이디입니다."));
        return findMember;
    }



}
