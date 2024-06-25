package com.example.counter.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;
    /*
    학생 아이디 (2411, pk)
     */

    @Column(name = "student_name", nullable = false)
    private String name;
    /*
    학생 이름
     */

    @Setter
    @Column(columnDefinition = "TEXT")
    private String pointReasons;
    /*
    포인트 이유들
     */

    @Setter
    @Column(nullable = false)
    private int bonusPoint;
    /*
    상점
     */

    @Setter
    @Column(nullable = false)
    private int minusPoint;
    /*
    벌점
     */

    public void resetMinusPoint(){
        this.minusPoint = 0;
    }

}
