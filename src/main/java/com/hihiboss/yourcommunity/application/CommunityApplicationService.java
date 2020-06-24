package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.Community;
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
        Community community = createCommunityRequest.toEntity();
        return communityRepository.save(community).getId();
    }

    public CommunityInfoResponse getCommunityInfo(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        return new CommunityInfoResponse(community);
    }

    @Transactional
    public Long changeManagerEmail(Long communityId, String newEmail) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        community.changeManagerEmail(newEmail);

        return communityId;
    }

    @Transactional
    public Long changeCommunityName(Long communityId, String newName) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        community.changeName(newName);

        return communityId;
    }

    @Transactional
    public Long deleteCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        communityRepository.delete(community);

        return communityId;
    }
}
