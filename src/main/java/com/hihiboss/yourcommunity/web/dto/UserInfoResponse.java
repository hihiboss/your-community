package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private long studentId;
    private long communityId;
    private String name;
    private String email;
    private String enrollmentStatus;
    private int grade;
    private String nickname;

    public UserInfoResponse(User entity) {
        this.studentId = entity.getStudentId();
        this.communityId = entity.getCommunityId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.enrollmentStatus = entity.getEnrollmentStatus().getValue();
        this.grade = entity.getGrade();
        this.nickname = entity.getNickname();
    }
}
