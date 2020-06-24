package com.hihiboss.yourcommunity.domain.value;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum EnrollmentStatusType implements EnumModel {
    STUDENT("재학생"),
    ALUMNI("졸업생"),
    PROFESSOR("교수");

    private String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public static EnrollmentStatusType getByValue(String value) {
        return Arrays.stream(EnrollmentStatusType.values())
                .filter(type -> type.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no type matching that value! - value: " + value));
    }
}
