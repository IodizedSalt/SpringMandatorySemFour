package com.mandatory.semfour;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping(value = "/logout") //TODO GIVE A SUCCESS LOGOUT ALERT
    public ModelAndView logout(){
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
    @GetMapping("/upload/Image")
    public ModelAndView imageUploader(){
        ModelAndView mv = new ModelAndView("UploadImage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        mv.addObject("username", currentPrincipalName);
        return mv;
    }
    @GetMapping("/upload/Text")
    public ModelAndView textUploader(){
        ModelAndView mv = new ModelAndView("UploadText");
        return mv;
    }

}
