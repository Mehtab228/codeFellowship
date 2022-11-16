package com.RiarMehtabCodeFellowship.CodeFellowship.controller;

import com.RiarMehtabCodeFellowship.CodeFellowship.models.Post;
import com.RiarMehtabCodeFellowship.CodeFellowship.models.SiteUser;
import com.RiarMehtabCodeFellowship.CodeFellowship.repository.PostRepository;
import com.RiarMehtabCodeFellowship.CodeFellowship.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    SiteUserRepository siteUserRepository;

    @PostMapping("/add-post")
    public RedirectView addNewPost(Principal p, Model m, String body, String title){
        String username = p.getName();
        SiteUser siteUser = siteUserRepository.findByUsername(username);
        m.addAttribute("siteUser", siteUser);
        Post post = new Post(body, title);
        post.setCreatedAt(new Date());
        post.setSiteUser(siteUser);
        postRepository.save(post);
        return new RedirectView("/profile");
    }

}
