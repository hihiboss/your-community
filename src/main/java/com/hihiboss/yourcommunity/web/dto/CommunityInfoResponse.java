package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.Community;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommunityInfoResponse {
    private long communityId;
    private String name;
    private String managerEmail;

    public CommunityInfoResponse(Community entity) {
        this.communityId = entity.getId();
        this.name = entity.getCommunityName();
        this.managerEmail = entity.getManagerEmail();
    }
}
