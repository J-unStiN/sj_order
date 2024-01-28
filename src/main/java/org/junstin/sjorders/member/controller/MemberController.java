package org.junstin.sjorders.member.controller;

import lombok.Getter;
import org.junstin.sjorders.member.domain.Member;
import org.junstin.sjorders.member.dto.MemberCreateReqDto;
import org.junstin.sjorders.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/member/new")
    public void memberCreate(@RequestBody MemberCreateReqDto member ) {

    }

    @GetMapping("/members")
    public List<Member> memberAllLst() {
        return memberService.memberAllList();
    }

    @GetMapping("/member/{id}/orders")
    public void memberOrdersList(@PathVariable("id") Long id) {

    }


}
