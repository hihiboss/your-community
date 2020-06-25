package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.Community;
import com.hihiboss.yourcommunity.domain.CommunityRepository;
import com.hihiboss.yourcommunity.domain.User;
import com.hihiboss.yourcommunity.domain.UserRepository;
import com.hihiboss.yourcommunity.domain.value.EnrollmentStatusType;
import com.hihiboss.yourcommunity.web.dto.JoinRequest;
import com.hihiboss.yourcommunity.web.dto.UpdateUserInfoRequest;
import com.hihiboss.yourcommunity.web.dto.UserInfoResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationServiceTest {
    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommunityRepository communityRepository;

    private User testUser;

    @Before
    public void setup() {
        long testCommunityId = communityRepository.save(
                Community.builder()
                        .communityName("test community")
                        .managerEmail("manager@email.com")
                        .build()
        ).getId();

        testUser = User.builder()
                .studentId(12345678L)
                .communityId(testCommunityId)
                .name("test name")
                .email("test@email.com")
                .enrollmentStatus(EnrollmentStatusType.STUDENT)
                .grade(1)
                .nickname("test nickname")
                .build();
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
        communityRepository.deleteAll();
    }

    @Test
    public void join_shouldSuccess() {
        // given
        JoinRequest joinRequest = new JoinRequest(
                testUser.getStudentId(),
                testUser.getCommunityId(),
                testUser.getName(),
                testUser.getEmail(),
                testUser.getEnrollmentStatus().getValue(),
                testUser.getGrade(),
                testUser.getNickname()
        );

        // when
        Long userId = userApplicationService.join(joinRequest);

        // then
        User user = userRepository.findAll().get(0);

        assertThat(userId).isNotNull();
        assertThat(user.getStudentId()).isEqualTo(testUser.getStudentId());
        assertThat(user.getCommunityId()).isEqualTo(testUser.getCommunityId());
        assertThat(user.getName()).isEqualTo(testUser.getName());
        assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(user.getEnrollmentStatus()).isEqualTo(testUser.getEnrollmentStatus());
        assertThat(user.getGrade()).isEqualTo(testUser.getGrade());
        assertThat(user.getNickname()).isEqualTo(testUser.getNickname());
    }

    @Test(expected = IllegalArgumentException.class)
    public void join_shouldFail_forNotCreatedCommunity() {
        // given
        communityRepository.deleteAll();

        JoinRequest joinRequest = new JoinRequest(
                testUser.getStudentId(),
                testUser.getCommunityId(),
                testUser.getName(),
                testUser.getEmail(),
                testUser.getEnrollmentStatus().getValue(),
                testUser.getGrade(),
                testUser.getNickname()
        );

        // when
        Long userId = userApplicationService.join(joinRequest);
    }

    @Test(expected = IllegalArgumentException.class)
    public void join_shouldFail_forAlreadyJoinedUser() {
        // given
        userRepository.save(
                User.builder()
                .studentId(testUser.getStudentId())
                .communityId(testUser.getCommunityId())
                .build()
        );

        JoinRequest joinRequest = new JoinRequest(
                testUser.getStudentId(),
                testUser.getCommunityId(),
                testUser.getName(),
                testUser.getEmail(),
                testUser.getEnrollmentStatus().getValue(),
                testUser.getGrade(),
                testUser.getNickname()
        );

        // when
        Long userId = userApplicationService.join(joinRequest);
    }

    @Test
    public void join_shouldSuccess_forSameStudentIdInOtherCommunity() {
        // given
        long otherCommunityId = communityRepository.save(
                Community.builder()
                        .communityName("other community")
                        .managerEmail("other@email.com")
                        .build()
        ).getId();

        userRepository.save(
                User.builder()
                .studentId(testUser.getStudentId())
                .communityId(otherCommunityId)
                .build()
        );

        JoinRequest joinRequest = new JoinRequest(
                testUser.getStudentId(),
                testUser.getCommunityId(),
                testUser.getName(),
                testUser.getEmail(),
                testUser.getEnrollmentStatus().getValue(),
                testUser.getGrade(),
                testUser.getNickname()
        );

        // when
        Long userId = userApplicationService.join(joinRequest);

        // then
        User user = userRepository.findAll().get(1);

        assertThat(userId).isNotNull();
        assertThat(user.getStudentId()).isEqualTo(testUser.getStudentId());
        assertThat(user.getCommunityId()).isEqualTo(testUser.getCommunityId());
        assertThat(user.getName()).isEqualTo(testUser.getName());
        assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(user.getEnrollmentStatus()).isEqualTo(testUser.getEnrollmentStatus());
        assertThat(user.getGrade()).isEqualTo(testUser.getGrade());
        assertThat(user.getNickname()).isEqualTo(testUser.getNickname());
    }

    @Test
    public void getUserInfo_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();

        // when
        UserInfoResponse userInfoResponse = userApplicationService.getUserInfo(userId);

        // then
        assertThat(userInfoResponse).isNotNull();
        assertThat(userInfoResponse.getStudentId()).isEqualTo(testUser.getStudentId());
        assertThat(userInfoResponse.getCommunityId()).isEqualTo(testUser.getCommunityId());
        assertThat(userInfoResponse.getName()).isEqualTo(testUser.getName());
        assertThat(userInfoResponse.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(userInfoResponse.getEnrollmentStatus()).isEqualTo(testUser.getEnrollmentStatus().getValue());
        assertThat(userInfoResponse.getGrade()).isEqualTo(testUser.getGrade());
        assertThat(userInfoResponse.getNickname()).isEqualTo(testUser.getNickname());
    }

    @Test
    public void updateUserInfo_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();

        UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest(
                "new test name",
                "new@email.com",
                EnrollmentStatusType.ALUMNI.getValue(),
                2,
                "new test nickname"
        );

        // when
        userApplicationService.updateUserInfo(userId, updateUserInfoRequest);

        // then
        User user = userRepository.findAll().get(0);

        assertThat(user.getName()).isEqualTo(updateUserInfoRequest.getName());
        assertThat(user.getEmail()).isEqualTo(updateUserInfoRequest.getEmail());
        assertThat(user.getEnrollmentStatus().getValue()).isEqualTo(updateUserInfoRequest.getEnrollmentStatus());
        assertThat(user.getGrade()).isEqualTo(updateUserInfoRequest.getGrade());
        assertThat(user.getNickname()).isEqualTo(updateUserInfoRequest.getNickname());
    }

    @Test
    public void leave_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();

        // when
        userApplicationService.leave(userId);

        // then
        assertThat(userRepository.findAll().size()).isEqualTo(0);
    }
}
