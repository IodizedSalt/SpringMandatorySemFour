package com.mandatory.semfour.controller;

import com.mandatory.semfour.entity.User;
import com.mandatory.semfour.repository.UserRepository;
import javafx.scene.control.Alert;
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
    @PostMapping(value = "/register")
    private ModelAndView registerNew(@RequestParam (name = "Id", defaultValue = "-1") int Id,
                                     @RequestParam(name = "emailReg") String email,
                                     @RequestParam(name="usernameReg") String username,
                                     @RequestParam(name = "passwordReg") String password){
        try{

            User u = new User(Id, ur.findByEmail(email), password, ur.findByUsername(username));
            u.setEmail(email);
            u.setPassword(password);
            u.setUsername(username);
            ur.save(u);


        }catch (NullPointerException e) {
            System.out.println("NULL ERROR");
        }catch (ClassCastException e){
            System.out.println("DUPLICATE DETECTED");   //TODO display specific username/email duplicate error
//            TODO add error message on HTML for user
            ModelAndView mv = new ModelAndView("RegisterAgain");
            return mv;

        }
        ModelAndView mv = new ModelAndView("HomePageLoggedIn");
        return mv;
    }

}
