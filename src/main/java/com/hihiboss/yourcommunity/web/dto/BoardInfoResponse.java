package com.hihiboss.yourcommunity.web.dto;

import com.hihiboss.yourcommunity.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardInfoResponse {
    private long boardId;
    private String boardType;
    private String boardName;

    public BoardInfoResponse(Board entity) {
        this.boardId = entity.getId();
        this.boardType = entity.getBoardType().getValue();
        this.boardName = entity.getBoardName();
    }
}
