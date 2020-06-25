package com.hihiboss.yourcommunity.web;

import com.hihiboss.yourcommunity.application.CommunityApplicationService;
import com.hihiboss.yourcommunity.web.dto.BoardInfoResponse;
import com.hihiboss.yourcommunity.web.dto.CreateBoardRequest;
import com.hihiboss.yourcommunity.web.dto.DeleteBoardRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@AllArgsConstructor
public class BoardController {
    private CommunityApplicationService communityApplicationService;

    @PostMapping()
    public void createBoard(@RequestBody CreateBoardRequest request) {
        communityApplicationService.createBoard(request);
    }

    @DeleteMapping()
    public void deleteBoard(@RequestBody DeleteBoardRequest request) {
        communityApplicationService.deleteBoard(request);
    }

    @GetMapping("{id}")
    public BoardInfoResponse getBoard(@PathVariable long id, @RequestParam long communityId) {
        return communityApplicationService.getBoardInfo(id, communityId);
    }
}
