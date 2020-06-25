package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.Board;
import com.hihiboss.yourcommunity.domain.Community;
import com.hihiboss.yourcommunity.domain.CommunityRepository;
import com.hihiboss.yourcommunity.domain.UserRepository;
import com.hihiboss.yourcommunity.domain.value.BoardType;
import com.hihiboss.yourcommunity.web.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommunityApplicationService {
    private CommunityRepository communityRepository;
    private UserRepository userRepository;

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
        userRepository.deleteAllByCommunityId(communityId);

        return communityId;
    }

    @Transactional(readOnly = true)
    public CommunityBoardsInfoResponse getCommunityBoardsInfo(long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        return new CommunityBoardsInfoResponse(communityId, community.getBoards());
    }

    @Transactional
    public void createBoard(CreateBoardRequest createBoardRequest) {
        Community community = communityRepository.findById(createBoardRequest.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        Board board = Board.builder()
                .community(community)
                .boardName(createBoardRequest.getBoardName())
                .boardType(BoardType.getByValue(createBoardRequest.getBoardType()))
                .build();

        community.createBoard(board);
    }

    @Transactional
    public void deleteBoard(DeleteBoardRequest deleteBoardRequest) {
        Community community = communityRepository.findById(deleteBoardRequest.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        Board board = community.getBoards().stream()
                .filter(b -> b.getId() == deleteBoardRequest.getBoardId())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no board with that ID!"));

        community.deleteBoard(board);
    }

    @Transactional(readOnly = true)
    public BoardInfoResponse getBoardInfo(long boardId, long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("There is no community with that ID!"));

        Board board = community.getBoards().stream()
                .filter(b -> b.getId() == boardId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no board with that ID!"));

        return new BoardInfoResponse(board);
    }
}
