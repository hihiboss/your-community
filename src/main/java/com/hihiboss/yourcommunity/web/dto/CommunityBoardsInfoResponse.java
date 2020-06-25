package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommunityBoardsInfoResponse {
    private long communityId;
    private List<Board> boards;
}
