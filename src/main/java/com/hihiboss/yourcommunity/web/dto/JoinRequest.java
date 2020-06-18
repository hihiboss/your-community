package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.User;
import com.hihiboss.yourcommunity.domain.value.EnrollmentStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class JoinRequest {
    private long studentId;
    private String name;
    private String email;
    private String enrollmentStatus;
    private int grade;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .studentId(studentId)
                .name(name)
                .email(email)
                .enrollmentStatus(EnrollmentStatusType.getByValue(enrollmentStatus))
                .grade(grade)
                .nickname(nickname)
                .build();
    }
}
