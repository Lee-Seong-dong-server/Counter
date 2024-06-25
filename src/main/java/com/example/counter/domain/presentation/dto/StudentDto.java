package com.example.counter.domain.presentation.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDto {
    private String studentId;

    private String name;

    private String pointReasons = "";

    private int bonusPoint = 0;

    private int minusPoint = 0;
}
