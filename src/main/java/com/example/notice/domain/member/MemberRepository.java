package com.example.notice.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepository {
    private static HashMap<Long, Member>store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    // 회원 번호로 조회
    public Member findById(Long id){
        return store.get(id);
    }

    // 회원 전체 조회
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    // 회원 로그인 아이디로 조회
    public Optional<Member>findByLoginId(String loginId){
        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clearStore(){
        store.clear();
    }

}
