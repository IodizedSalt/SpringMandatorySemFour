package com.mandatory.semfour;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    public UserRepository ur;



    @PostMapping(value = "/user/login")
    private ModelAndView checkUserLogIn(@RequestParam(name = "username") String username,
                                    @RequestParam(name = "password") String password) {

        try {
            String user = ur.findOne(username).getPassword();
            System.out.println(user.toString());

            if(user.equals(password)){
                System.out.println("LOG IN SUCCESS");
                ModelAndView mv = new ModelAndView("HomePageLoggedIn");
                return mv;
            }else{
                System.out.println("ACCESS DENIED _ INVALID PASSWORD");
                ModelAndView mvStudent = new ModelAndView("LogIn");
                return mvStudent;
            }
        } catch (NullPointerException e) {
            System.out.println("ACCESS DENIED_INVALID USERNAME_?ALSO PASSWORD?");

            ModelAndView mvUserLoggedIn = new ModelAndView("LogIn");
            return mvUserLoggedIn;
        }
    }

}
