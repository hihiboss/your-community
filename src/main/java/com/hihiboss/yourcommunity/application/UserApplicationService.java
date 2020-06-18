package com.hihiboss.yourcommunity.application;

import com.hihiboss.yourcommunity.domain.User;
import com.hihiboss.yourcommunity.domain.UserRepository;
import com.hihiboss.yourcommunity.domain.value.EnrollmentStatusType;
import com.hihiboss.yourcommunity.web.dto.JoinRequest;
import com.hihiboss.yourcommunity.web.dto.UpdateUserInfoRequest;
import com.hihiboss.yourcommunity.web.dto.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserApplicationService {
    private UserRepository userRepository;

    @Transactional
    public Long join(JoinRequest requestDto) {
        User user = requestDto.toEntity();
        return userRepository.save(user).getId();
    }

    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("There is no user having that id! - id: " + userId));

        return new UserInfoResponse(user);
    }

    @Transactional
    public Long updateUserInfo(Long userId, UpdateUserInfoRequest requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("There is no user having that id! - id: " + userId));

        user.updateInfo(
                requestDto.getName(),
                requestDto.getEmail(),
                EnrollmentStatusType.getByValue(requestDto.getEnrollmentStatus()),
                requestDto.getGrade(),
                requestDto.getNickname()
        );
        return userId;
    }

    @Transactional
    public Long leave(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("There is no user having that id! - id: " + userId));

        userRepository.delete(user);
        return userId;
    }
}
