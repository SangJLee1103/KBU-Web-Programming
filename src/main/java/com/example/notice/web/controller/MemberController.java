package com.example.notice.web.controller;

import com.example.notice.domain.info.Notice;
import com.example.notice.domain.member.Member;
import com.example.notice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member){
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult result){
        if(result.hasErrors()){
            return "members/addMemberForm";
        }

        memberRepository.save(member);
        return "redirect:/";
    }

    /**
     * 관리자(과대, 조교) 데이터 추가
     */
    @PostConstruct
    public void init(){
        memberRepository.save(new Member("1학년 과대", "cs1111", "cs1234"));
        memberRepository.save(new Member("2학년 과대", "cs2222", "cs1234"));
        memberRepository.save(new Member("3학년 과대", "cs3333", "cs1234"));
        memberRepository.save(new Member("학회장", "cs4444", "cs1234"));
        memberRepository.save(new Member("조교", "csassistant", "cs1234"));
    }
}
