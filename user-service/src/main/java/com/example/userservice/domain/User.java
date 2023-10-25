package com.example.userservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter // @Setter은 엔티티 수정을 막기 위해서, 캡슐화 + 은닉화
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String userId;

    private String endPw;

    private String uuid;

    private LocalDateTime creatAt;
}

// 도메인 레이어는 다른 레이어를 참조하면 안 되지만 다른 레이어에서 도메인 레이어를 참조해도 된다.