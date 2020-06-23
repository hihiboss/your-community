package com.hihiboss.yourcommunity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users/join")
    public String usersJoin() {
        return "users-join";
    }

    @GetMapping("/communities/create")
    public String createCommunity() { return "communities-create"; }
}
