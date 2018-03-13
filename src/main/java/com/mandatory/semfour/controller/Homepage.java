package com.mandatory.semfour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Homepage {
    @GetMapping("/")
    public String homePage(){
        return "HomePage";
    }
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("LogIn");
        return mv;
    }
    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("Register");
        return mv;
    }

}
