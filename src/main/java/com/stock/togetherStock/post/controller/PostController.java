package com.stock.togetherStock.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/post")
public class PostController {


    @GetMapping
    public String view() {

        return "/post/postList";
    }

    @GetMapping("/{postId}")
    public String post(Model model) {
        model.addAttribute("");

        return "";

    }
}
