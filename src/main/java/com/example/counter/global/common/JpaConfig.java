package com.example.counter.global.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 언제 어떤 일을 했는지 알 수 있게 하기 위해서 사용
public class JpaConfig {
}
