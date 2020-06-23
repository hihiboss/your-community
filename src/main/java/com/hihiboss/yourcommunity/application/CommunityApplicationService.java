package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.CommunityRepository;
import com.hihiboss.yourcommunity.web.dto.CommunityInfoResponse;
import com.hihiboss.yourcommunity.web.dto.CreateCommunityRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommunityApplicationService {
    private CommunityRepository communityRepository;

    @Transactional
    public Long createCommunity(CreateCommunityRequest createCommunityRequest) {
        return null;
    }

    public CommunityInfoResponse getCommunityInfo(Long communityId) {
        return null;
    }

    @Transactional
    public Long changeManagerEmail(Long communityId, String newEmail) {
        return null;
    }

    @Transactional
    public Long changeCommunityName(Long communityId, String newName) {
        return null;
    }

    @Transactional
    public Long deleteCommunity(Long communityId) {
        return null;
    }
}
