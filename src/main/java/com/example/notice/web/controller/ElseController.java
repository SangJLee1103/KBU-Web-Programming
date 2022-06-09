package com.example.notice.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/else")
public class ElseController {

    @GetMapping("/map")
    public String map(){
        return "else/map";
    }

    @GetMapping("/lecture")
    public String lecture(){
        return "else/lectureinfo";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "else/calendar";
    };

}
