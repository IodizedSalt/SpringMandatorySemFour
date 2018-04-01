package com.mandatory.semfour;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Homepage {
    @GetMapping(value = "/")
    public ModelAndView homePage(){
        ModelAndView mv  = new ModelAndView("HomePage");
        return mv;
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
