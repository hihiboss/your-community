package com.hihiboss.yourcommunity.web;

import com.hihiboss.yourcommunity.application.UserApplicationService;
import com.hihiboss.yourcommunity.web.dto.JoinRequest;
import com.hihiboss.yourcommunity.web.dto.UpdateUserInfoRequest;
import com.hihiboss.yourcommunity.web.dto.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserApplicationService userApplicationService;

    @PostMapping()
    public Long join(@RequestBody JoinRequest requestDto) {
        return userApplicationService.join(requestDto);
    }

    @GetMapping("{id}")
    public UserInfoResponse getUserInfo(@PathVariable long id) {
        return userApplicationService.getUserInfo(id);
    }

    @PutMapping("{id}")
    public Long updateUserInfo(
            @PathVariable long id,
            @RequestBody UpdateUserInfoRequest requestDto
    ) {
        return userApplicationService.updateUserInfo(id, requestDto);
    }

    @DeleteMapping("{id}")
    public Long leave(@PathVariable long id) {
        return userApplicationService.leave(id);
    }
}
