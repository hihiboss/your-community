package com.hihiboss.yourcommunity.domain;

import com.hihiboss.yourcommunity.domain.value.BoardType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    @Getter(value = AccessLevel.NONE)
    private Community community;

    @Column(name = "board_type")
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(name = "board_name")
    private String boardName;
}
