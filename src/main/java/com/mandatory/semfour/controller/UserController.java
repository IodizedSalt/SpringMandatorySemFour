package com.mandatory.semfour.controller;

import com.mandatory.semfour.entity.User;
import com.mandatory.semfour.entity.UserRole;
import com.mandatory.semfour.repository.PostRepository;
import com.mandatory.semfour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    public UserRepository ur;

    @Autowired
    PostRepository pr;

@RequestMapping("/login")
public ModelAndView login()
{
    System.out.println("GREAT SUCCESS");
    ModelAndView mv = new ModelAndView("LogIn");
    return mv;
}
@RequestMapping("/loginAgain")
public ModelAndView loginAgain()
{
    ModelAndView mv = new ModelAndView("LoginAgain");
    return mv;
}
@RequestMapping("/home")
public ModelAndView loginSuccess(ModelMap model)
{
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    ModelAndView mv = new ModelAndView("HomePageLoggedIn");
    mv.getModel().put("postList", pr.findAll());

    mv.addObject("username", currentPrincipalName);
    return mv;
}

    @PostMapping(value = "/register")
    private ModelAndView registerNew(@RequestParam (name = "Id", defaultValue = "-1") int Id,
                                     @RequestParam(name = "emailReg") String email,
                                     @RequestParam(name="usernameReg", defaultValue = "") String username,
                                     @RequestParam(name = "passwordReg") String password){
        try{

            User u = new User(Id, ur.findByEmail(email), password, ur.findByUsername(username));
            UserRole urr = new UserRole("ROLE_USER");
            u.setId(Id);
            u.setEmail(email);
            u.setPassword(password);
            u.setUsername(username);
            u.setEnabled(true);
            u.setRole(urr);
            ur.save(u);

        }catch (NullPointerException e) {
            System.out.println("NULL ERROR");
        }catch (ClassCastException e){
            System.out.println("DUPLICATE DETECTED");
            ModelAndView mv = new ModelAndView("RegisterAgain");
            return mv;

        }
        ModelAndView mv = new ModelAndView("HomePageLoggedIn");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
        mv.getModel().put("postList", pr.findAll());

//        mv.addObject("username", currentPrincipalName);
        return mv;
    }

}
