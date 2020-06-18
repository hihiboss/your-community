package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private long studentId;
    private String name;
    private String email;
    private String enrollmentStatus;
    private int grade;
    private String nickname;

    public UserInfoResponse(User entity) {
        this.studentId = entity.getStudentId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.enrollmentStatus = entity.getEnrollmentStatus().getValue();
        this.grade = entity.getGrade();
        this.nickname = entity.getNickname();
    }
}
