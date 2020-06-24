package com.hihiboss.yourcommunity.domain;

import com.hihiboss.yourcommunity.domain.value.EnrollmentStatusType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Test
    public void updateInfo_shouldSuccess() {
        // given
        User testUser = User.builder()
                .name("test name")
                .email("test@email.com")
                .enrollmentStatus(EnrollmentStatusType.STUDENT)
                .grade(1)
                .nickname("test nickname")
                .build();

        String newName = "new test name";
        String newEmail = "new@email.com";
        EnrollmentStatusType newStatus = EnrollmentStatusType.ALUMNI;
        int newGrade = 2;
        String newNickname = "new test nickname";

        // when
        testUser.updateInfo(
                newName,
                newEmail,
                newStatus,
                newGrade,
                newNickname
        );

        // then
        assertThat(testUser.getName()).isEqualTo(newName);
        assertThat(testUser.getEmail()).isEqualTo(newEmail);
        assertThat(testUser.getEnrollmentStatus()).isEqualTo(newStatus);
        assertThat(testUser.getGrade()).isEqualTo(newGrade);
        assertThat(testUser.getNickname()).isEqualTo(newNickname);
    }
}
