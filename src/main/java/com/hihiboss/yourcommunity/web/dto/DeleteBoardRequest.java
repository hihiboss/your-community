package com.hihiboss.yourcommunity.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeleteBoardRequest {
    private long communityId;
    private long boardId;
}
