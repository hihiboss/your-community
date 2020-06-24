package com.hihiboss.yourcommunity.domain;

import com.hihiboss.yourcommunity.domain.value.BaseTimeEntity;
import com.hihiboss.yourcommunity.domain.value.EnrollmentStatusType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "enrollment_status")
    private EnrollmentStatusType enrollmentStatus;

    @Column(name = "grade")
    private int grade;

    @Column(name = "nickname")
    private String nickname;

    public void updateInfo(
            String name,
            String email,
            EnrollmentStatusType enrollmentStatus,
            int grade,
            String nickname
    ) {
        this.name = name;
        this.email = email;
        this.enrollmentStatus = enrollmentStatus;
        this.grade = grade;
        this.nickname = nickname;
    }
}
