package com.mandatory.semfour.controller;

import com.mandatory.semfour.entity.User;
import com.mandatory.semfour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            System.out.print(user.toString() + " ");

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
                                                    //TODO CHECK USERNAME FOR DUPLICATES AND DONT Allow
                                                    //LINE 52/53 BREAKS, 55-60 ADDS DEFAULT USER

    @PostMapping(value = "/register")
    private ModelAndView registerNew(@RequestParam (name = "Id", defaultValue = "-1") int Id,
                                     @RequestParam(name = "emailReg") String email,
                                     @RequestParam(name="usernameReg") String username,
                                     @RequestParam(name = "passwordReg") String password){
        try{
//            String user = ur.findOne(username).toString();
//            System.out.println(user);
            User u = User.getUserById(Id);
            if (Id != -1){
                u.setEmail(email);
                u.setUsername(username);
                u.setPassword(password);
            }
            ur.save(u);
        }catch (NullPointerException e) {
            System.out.println("NULL ERROR");
        }
        ModelAndView mv = new ModelAndView("HomePageLoggedIn");
        return mv;
    }

}
