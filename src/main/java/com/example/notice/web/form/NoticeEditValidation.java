package com.example.notice.web.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NoticeEditValidation {
    @NotNull
    private Long id;

    @NotBlank(message = "제목은 필수입니다.")
    private String subject;

    @NotEmpty(message = "학년 입력은 필수입니다.")
    private String grade;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
