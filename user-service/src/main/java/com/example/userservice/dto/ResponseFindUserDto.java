package com.example.userservice.dto;

import com.example.userservice.domain.Order;
import com.example.userservice.domain.User;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ResponseFindUserDto {

    private Long id;

    private String uuid;

    private String email;

    private String name;

    private String userId;

    // 필요하다면 구매 내역을 같이 가져올 수 있도록 처리합니다.
    private List<Order> orderList;


    public ResponseFindUserDto(User user){
        this.id = user.getId();
        this.email =user.getEmail();
        this.name =user.getName();
        this.uuid =user.getUuid();
        this.userId=user.getUserId();
    }

}
