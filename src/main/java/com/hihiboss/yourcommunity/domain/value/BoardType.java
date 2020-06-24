package com.hihiboss.yourcommunity.domain.value;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum BoardType implements EnumModel {
    FREE("자유게시판"),
    PHOTO("사진게시판"),
    POLL("투표게시판");

    private String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public static BoardType getByValue(String value) {
        return Arrays.stream(BoardType.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no type matching that value! - value: " + value));
    }
}
