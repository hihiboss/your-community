package com.hihiboss.yourcommunity.domain;

import com.hihiboss.yourcommunity.domain.value.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "communities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Community extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private long id;

    @Column(name = "community_name")
    private String communityName;

    @Column(name = "manager_eamil", nullable = false)
    private String managerEmail;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    public void changeName(String newName) {
        this.communityName = newName;
    }

    public void changeManagerEmail(String newEmail) {
        this.managerEmail = newEmail;
    }

    public void createBoard(Board board) {
        this.boards.add(board);
    }

    public void deleteBoard(Board board) {
        this.boards.remove(board);
    }
}
