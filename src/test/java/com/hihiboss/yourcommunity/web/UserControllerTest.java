package com.hihiboss.yourcommunity.web;

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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private String endpoint = "/api/users";

    @Before
    public void setup() {
        testUser = User.builder()
                .studentId(12345678L)
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
    }

    @Test
    public void joinTest_shouldSuccess() {
        // given
        String url = endpoint;
        JoinRequest requestDto = new JoinRequest(
                testUser.getStudentId(),
                testUser.getName(),
                testUser.getEmail(),
                testUser.getEnrollmentStatus().getValue(),
                testUser.getGrade(),
                testUser.getNickname()
        );

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        User user = userRepository.findAll().get(0);
        assertThat(user.getStudentId()).isEqualTo(testUser.getStudentId());
        assertThat(user.getName()).isEqualTo(testUser.getName());
        assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(user.getEnrollmentStatus()).isEqualTo(testUser.getEnrollmentStatus());
        assertThat(user.getGrade()).isEqualTo(testUser.getGrade());
        assertThat(user.getNickname()).isEqualTo(testUser.getNickname());
    }

    @Test
    public void getUserInfoTest_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();
        String url = endpoint + "/" + userId;

        // when
        ResponseEntity<UserInfoResponse> responseEntity = restTemplate.getForEntity(url, UserInfoResponse.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserInfoResponse userInfoResponse = responseEntity.getBody();
        assertThat(userInfoResponse.getStudentId()).isEqualTo(testUser.getStudentId());
        assertThat(userInfoResponse.getName()).isEqualTo(testUser.getName());
        assertThat(userInfoResponse.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(userInfoResponse.getEnrollmentStatus()).isEqualTo(testUser.getEnrollmentStatus().getValue());
        assertThat(userInfoResponse.getGrade()).isEqualTo(testUser.getGrade());
        assertThat(userInfoResponse.getNickname()).isEqualTo(testUser.getNickname());
    }

    @Test
    public void updateUserInfoTest_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();
        String url = endpoint + "/" + userId;
        UpdateUserInfoRequest requestDto = new UpdateUserInfoRequest(
                "new test name",
                "new@email.com",
                EnrollmentStatusType.ALUMNI.getValue(),
                2,
                "new test nickname"
        );
        HttpEntity<UpdateUserInfoRequest> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        User user = userRepository.findAll().get(0);
        assertThat(user.getName()).isEqualTo(requestDto.getName());
        assertThat(user.getEmail()).isEqualTo(requestDto.getEmail());
        assertThat(user.getEnrollmentStatus().getValue()).isEqualTo(requestDto.getEnrollmentStatus());
        assertThat(user.getGrade()).isEqualTo(requestDto.getGrade());
        assertThat(user.getNickname()).isEqualTo(requestDto.getNickname());
    }

    @Test
    public void leaveTest_shouldSuccess() {
        // given
        long userId = userRepository.save(testUser).getId();
        String url = endpoint + "/" + userId;

        // when
        restTemplate.delete(url);

        // then
        assertThat(userRepository.findAll().size()).isEqualTo(0);
    }
}
