package com.stock.togetherStock.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model,
        @RequestParam(value="error", required = false) String error,
        @RequestParam(value = "exception", required = false) String exception)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);


        return "/post/postList";
    }
}
