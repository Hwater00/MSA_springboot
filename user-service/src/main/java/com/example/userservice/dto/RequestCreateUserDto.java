package com.example.userservice.dto;

// DTO를 사용하여 User 테이블 은닉화
// 네이밍 이유 : 요청/응답 표시 + 어떤 요청(DB)

import com.example.userservice.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RequestCreateUserDto {
    @Email
    private String email;

    @NonNull
    @Size(min = 8,max = 20,message = "비밀번호는 최소 8글자이고, 최대 20글자입니다.")
    private String pw;

    @NonNull
    private String name;

    @NonNull
    private String userId;

    // 엔티티로 리턴함으로써 도메인 레이어를 참조
    public User toEntity(){
        return User.builder()
                .email(this.email)
                .endPw(this.pw)
                .userId(this.userId)
                .name(this.name)
                .uuid(UUID.randomUUID().toString())
                .creatAt(LocalDateTime.now())
                .build();
    }
}
