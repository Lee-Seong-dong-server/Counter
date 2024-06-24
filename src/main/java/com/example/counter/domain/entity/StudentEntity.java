package com.example.counter.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "student")
@Builder
@Getter
@ToString
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /*
    기본 아이디 (pk)
     */

    @Column(nullable = false)
    private String studentId;
    /*
    학생 아이디 (2411)
     */

    @Column(name = "student_name", nullable = false)
    private String name;
    /*
    학생 이름
     */

    @Column(nullable = false)
    private int bonusPoint = 0;
    /*
    상점
     */

    @Column(nullable = false)
    private int minusPoint = 0;
    /*
    벌점
     */
}
