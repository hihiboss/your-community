package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.Community;
import com.hihiboss.yourcommunity.domain.CommunityRepository;
import com.hihiboss.yourcommunity.domain.User;
import com.hihiboss.yourcommunity.domain.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
}
