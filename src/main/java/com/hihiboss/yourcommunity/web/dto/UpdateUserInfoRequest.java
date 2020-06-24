package com.hihiboss.yourcommunity.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateUserInfoRequest {
    private String name;
    private String email;
    private String enrollmentStatus;
    private int grade;
    private String nickname;
}
