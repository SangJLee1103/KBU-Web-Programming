package com.example.notice.web.controller;

import com.example.notice.domain.info.Notice;
import com.example.notice.domain.info.NoticeRepository;
import com.example.notice.web.form.NoticeEditValidation;
import com.example.notice.web.form.NoticeWriteValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @GetMapping("")
    public String main(Model model){
        List<Notice> notices = noticeRepository.findAll();
        model.addAttribute("notices", notices);
        return "basic/main";
    }

    @GetMapping("/{noticeId}")
    public String notice(@PathVariable Long noticeId, Model model){
        Notice notice = noticeRepository.findById(noticeId);
        model.addAttribute("notice", notice);
        return "/basic/notice";
    }

    @GetMapping("/notice/write")
    public String write(Model model){
        model.addAttribute("notice", new Notice());
        return "/basic/write";
    }

    @PostMapping("/notice/write")
    public String writeNotice(@Validated @ModelAttribute("notice") NoticeWriteValidation form, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //검증에 실패하면 다시 입력 폼으로
        //bindingResult 는 modelAttribute 에 담지 않아도 뷰로 넘어감!
        if(bindingResult.hasErrors()){
            log.info("error={}", bindingResult);
            return "basic/write";
        }

        Notice notice = new Notice();
        notice.setSubject(form.getSubject());
        notice.setGrade(form.getGrade());
        notice.setContent(form.getContent());


        Notice saveNotice = noticeRepository.save(notice);
        redirectAttributes.addAttribute("noticeId", saveNotice.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/{noticeId}";
    }

    @GetMapping("/{noticeId}/edit")
    public String edit(@PathVariable Long noticeId, Model model){
        Notice notice = noticeRepository.findById(noticeId);
        model.addAttribute("notice", notice);
        return "/basic/editnotice";
    }

    @PostMapping("/{noticeId}/edit")
    public String editnotice(@PathVariable Long noticeId, @Validated @ModelAttribute("notice") NoticeEditValidation form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "/basic/editnotice";
        }

        Notice noticeParam = new Notice();
        noticeParam.setSubject(form.getSubject());
        noticeParam.setGrade(form.getGrade());
        noticeParam.setContent(form.getContent());


        noticeRepository.updateNotice(noticeId, noticeParam);
        return "redirect:/{noticeId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        noticeRepository.save(new Notice("🤬취업 자소서 제출빨리 하세요!", "4", "빨리 안내면 교수님 호출 들어갑니다...."));

        noticeRepository.save(new Notice("6월 8일 진로 세미나.", "전학년",
                "6월"));
    }
    
}
