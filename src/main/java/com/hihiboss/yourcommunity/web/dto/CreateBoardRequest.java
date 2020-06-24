package com.hihiboss.yourcommunity.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateBoardRequest {
    private long communityId;
    private String boardType;
    private String boardName;
}
