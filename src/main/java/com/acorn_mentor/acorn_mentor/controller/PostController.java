package com.acorn_mentor.acorn_mentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostController {
    @GetMapping("/index")
    public String posts(ModelMap modelMap) {
        modelMap.addAttribute("posts", List.of());
        return "posts/index";
    }

    @GetMapping("posts/{postId}")
    public String post(@PathVariable Long postId, ModelMap modelMap) {
        modelMap.addAttribute("post", null);
        modelMap.addAttribute("comments", List.of());
        return "posts/detail";
    }


}
