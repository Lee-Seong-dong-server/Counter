package com.example.counter.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "student")
@Builder
@Getter
@Setter
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

    @Setter
    @Column(columnDefinition = "TEXT")
    private String pointReasons = "";
    /*
    포인트 이유들
     */

    @Setter
    @Column(nullable = false)
    private int bonusPoint = 0;
    /*
    상점
     */

    @Setter
    @Column(nullable = false)
    private int minusPoint = 0;
    /*
    벌점
     */

    public void resetMinusPoint(){
        this.minusPoint = 0;
    }

}
