package com.hihiboss.yourcommunity.web;

import com.hihiboss.yourcommunity.application.CommunityApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class IndexController {
    private CommunityApplicationService communityApplicationService;

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

    @GetMapping("/communities/login")
    public String joinCommunity() {
        return "communities-login";
    }

    @GetMapping("/communities/{id}")
    public String communityInfo(Model model, @PathVariable Long id) {
        model.addAttribute("communityInfo", communityApplicationService.getCommunityInfo(id));
        return "communities-info";
    }
}
