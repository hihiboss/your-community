package com.hihiboss.yourcommunity.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByCommunityId(Long communityId);
}
