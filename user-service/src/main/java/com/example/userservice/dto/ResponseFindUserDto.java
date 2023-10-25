package com.example.userservice.dto;

import com.example.userservice.domain.User;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ResponseFindUserDto {

    private Long id;

    private String uuid;

    private String email;

    private String name;

    private String userId;

    public ResponseFindUserDto(User user){
        this.id = user.getId();
        this.email =user.getEmail();
        this.name =user.getName();
        this.uuid =user.getUuid();
        this.userId=user.getUserId();
    }

}
