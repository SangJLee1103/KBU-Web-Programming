package com.example.notice.domain.info;

import lombok.Data;

@Data
public class Notice {

    private Long id;
    private String subject;
    private String grade;
    private String content;

    public Notice() { }

    public Notice(String subject, String grade, String content) {
        this.subject = subject;
        this.grade = grade;
        this.content = content;
    }
}
