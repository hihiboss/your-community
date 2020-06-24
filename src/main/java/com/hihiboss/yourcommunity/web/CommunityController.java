package com.hihiboss.yourcommunity.web;

import com.hihiboss.yourcommunity.application.CommunityApplicationService;
import com.hihiboss.yourcommunity.web.dto.CommunityInfoResponse;
import com.hihiboss.yourcommunity.web.dto.CreateCommunityRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/communities")
@AllArgsConstructor
public class CommunityController {
    private CommunityApplicationService communityApplicationService;

    @PostMapping()
    public Long createCommunity(CreateCommunityRequest requestDto) {
        return communityApplicationService.createCommunity(requestDto);
    }

    @GetMapping("{id}")
    public CommunityInfoResponse getCommunityInfo(@PathVariable Long id) {
        return communityApplicationService.getCommunityInfo(id);
    }

    @PutMapping("{id}/email")
    public Long changeManagerEmail(@PathVariable Long id, @RequestBody String email) {
        return communityApplicationService.changeManagerEmail(id, email);
    }

    @PutMapping("{id}/name")
    public Long changeCommunityName(@PathVariable Long id, @RequestBody String name) {
        return communityApplicationService.changeCommunityName(id, name);
    }

    @DeleteMapping("{id}")
    public Long deleteCommunity(@PathVariable Long id) {
        return communityApplicationService.deleteCommunity(id);
    }
}
