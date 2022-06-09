package com.example.notice.domain.info;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NoticeRepository {

    private static final Map<Long, Notice> repo = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    // 공지 저장 기능
    public Notice save(Notice info){
        info.setId(++sequence);
        repo.put(info.getId(), info);
        return info;
    }

    // Id를 통한 공지 조회
    public Notice findById(Long id){
        return repo.get(id);
    }

    // 공지 전체 조회
    public List<Notice> findAll(){
        return new ArrayList<>(repo.values());
    }

    // 공지 업데이트 기능
    public void updateNotice(Long NoticeId, Notice updateParam){
        Notice findNotice = findById(NoticeId);
        findNotice.setSubject(updateParam.getSubject());
        findNotice.setGrade(updateParam.getGrade());
        findNotice.setContent(updateParam.getContent());
    }
}
