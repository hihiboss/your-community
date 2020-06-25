package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.*;
import com.hihiboss.yourcommunity.domain.value.BoardType;
import com.hihiboss.yourcommunity.web.dto.CommunityBoardsInfoResponse;
import com.hihiboss.yourcommunity.web.dto.CreateBoardRequest;
import com.hihiboss.yourcommunity.web.dto.DeleteBoardRequest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationServiceTest {
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityApplicationService communityApplicationService;

    @After
    public void teardown() {
        communityRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void deleteCommunity_shouldSuccess_forJoinedUsers() {
        // given
        Community community = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@test.com")
                        .build()
        );

        userRepository.save(
                User.builder()
                        .studentId(1L)
                        .communityId(community.getId())
                        .build()
        );
        userRepository.save(
                User.builder()
                        .studentId(2L)
                        .communityId(community.getId())
                        .build()
        );

        // when
        communityApplicationService.deleteCommunity(community.getId());

        // then
        assertThat(communityRepository.findAll().size()).isEqualTo(0);
        assertThat(userRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    public void deleteCommunity_shouldSuccess_forJoinedUsersInOtherCommunity() {
        // given
        Community community = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@test.com")
                        .build()
        );
        Community otherCommunity = communityRepository.save(
                Community.builder()
                        .communityName("other community")
                        .managerEmail("other@test.com")
                        .build()
        );

        userRepository.save(
                User.builder()
                        .studentId(1L)
                        .communityId(community.getId())
                        .build()
        );
        userRepository.save(
                User.builder()
                        .studentId(2L)
                        .communityId(otherCommunity.getId())
                        .build()
        );

        // when
        communityApplicationService.deleteCommunity(community.getId());

        // then
        assertThat(communityRepository.findAll().size()).isEqualTo(1);
        assertThat(userRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void createBoard_shouldSuccess() {
        // given
        Community community = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@test.com")
                        .build()
        );
        Board testBoard = Board.builder()
                .community(community)
                .boardName("test board")
                .boardType(BoardType.FREE)
                .build();

        CreateBoardRequest createBoardRequest = CreateBoardRequest.builder()
                .communityId(community.getId())
                .boardName(testBoard.getBoardName())
                .boardType(testBoard.getBoardType().getValue())
                .build();

        // when
        communityApplicationService.createBoard(createBoardRequest);

        // then
        community = communityRepository.findById(community.getId()).get();
        assertThat(community.getBoards().size()).isEqualTo(1);

        Board result = community.getBoards().get(0);
        assertThat(result.getBoardName()).isEqualTo(testBoard.getBoardName());
        assertThat(result.getBoardType()).isEqualTo(testBoard.getBoardType());
    }

    @Test
    @Transactional
    public void deleteBoard_shouldSuccess() {
        // given
        Community community = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@test.com")
                        .build()
        );
        Board testBoard = Board.builder()
                .boardName("test board")
                .boardType(BoardType.FREE)
                .build();
        community.createBoard(testBoard);

        DeleteBoardRequest deleteBoardRequest = DeleteBoardRequest.builder()
                .communityId(community.getId())
                .boardId(testBoard.getId())
                .build();

        // when
        communityApplicationService.deleteBoard(deleteBoardRequest);

        // then
        community = communityRepository.findById(community.getId()).get();
        assertThat(community.getBoards().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void getBoards_shouldSuccess() {
        // given
        Community community = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@test.com")
                        .build()
        );
        Board testBoard = Board.builder()
                .boardName("test board")
                .boardType(BoardType.FREE)
                .build();
        Board otherBoard = Board.builder()
                .boardName("other board")
                .boardType(BoardType.FREE)
                .build();

        community.createBoard(testBoard);
        community.createBoard(otherBoard);

        // when
        CommunityBoardsInfoResponse response = communityApplicationService.getCommunityBoardsInfo(community.getId());

        // then
        assertThat(response.getCommunityId()).isEqualTo(community.getId());
        assertThat(response.getBoards().size()).isEqualTo(2);
    }
}
