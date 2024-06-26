package com.example.counter.domain.entity;

import com.example.counter.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 보안상 좋다고 해서 씀
@AllArgsConstructor // 생성자 자동 생성
@Table(name = "student")
@Builder // 생성자 오버로딩
@Getter
@Setter
@ToString
public class StudentEntity extends BaseTimeEntity {
    @Id // primary key로 지정
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
