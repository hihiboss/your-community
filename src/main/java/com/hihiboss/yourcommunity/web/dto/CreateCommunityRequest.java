package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.Community;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommunityRequest {
    private String communityName;
    private String managerEmail;

    public Community toEntity() {
        return Community.builder()
                .communityName(communityName)
                .managerEmail(managerEmail)
                .build();
    }
}
