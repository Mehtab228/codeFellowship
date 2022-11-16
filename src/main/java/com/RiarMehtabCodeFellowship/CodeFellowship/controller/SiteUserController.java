package com.RiarMehtabCodeFellowship.CodeFellowship.controller;

import com.RiarMehtabCodeFellowship.CodeFellowship.models.SiteUser;
import com.RiarMehtabCodeFellowship.CodeFellowship.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class SiteUserController {

    @Autowired
    SiteUserRepository siteUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    // Principal == Http session
    @GetMapping("/")
    public String getHomePage(Model m, Principal p){
        if(p != null){
            String username = p.getName();
            SiteUser foundUser = siteUserRepo.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("fName", foundUser.getFirstName());
        }
        return "profile";
    }

    @GetMapping("/profile")
    public String getMyProfile(Principal p, Model m){
        SiteUser siteUser = siteUserRepo.findByUsername(p.getName());
        m.addAttribute("siteUser", siteUser);
        return "profile.html";
    }


    // Login
    // POST -> not with Spring Security
    // GET return login.html
    @GetMapping("login")
    public String getLoginPage(){
        return "login";
    }


    // signup
    // POST
    // save a new user into DB with Hashed PW
    // Handle session?
    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName){
        // hash the PW
        String hashedPW = passwordEncoder.encode(password);
        // create new user
        SiteUser newUser = new SiteUser(username, hashedPW, firstName);
        // save the user
        siteUserRepo.save(newUser);
        // auto login -> httpServletRequest
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }


    // logout
    // POST
    // invalidate the session


    // secret -> authenticated
    // GET -> /sauce return html
    @GetMapping("/sauce")
    public String getSauce(){
        return "secret";
    }



}
