package com.stock.togetherStock.member.controller;

import com.stock.togetherStock.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final PostService postService;

    @GetMapping("/login")
    public String login(Model model,
        @RequestParam(value="error", required = false) String error,
        @RequestParam(value = "exception", required = false) String exception)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("postList", postService.postList());

        return "/post/postList";
    }
}
