package com.example.userservice.controller;

import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
// MVC 패턴에서 @RequestMapping("user-service")가 없어도 빌드 오류가 발생하지는 않지만
// 게이트웨이서  predicates: - Path=/user-service/** 로 등록되었기 때문에 @RequestMapping("user-service")가 필요함!!
@RequestMapping("user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "Server is available!";
    }

    @PostMapping("users")
    public ResponseEntity<?> createUser(@Valid @RequestBody RequestCreateUserDto userDto){
        // @RequestBody가 붙으면 RequestCreateUserDto 맞는 필트를 채울 수 있도록 json으로 보내주겠다
        // @RequestBody가 안 붙으면 폼에서 들어온 것으로 매핑됨
        // @Vaild는 사전의 지정한 양식이 맞는지 검증한 후에 역직렬화를 진행

        userService.creatUser(userDto);
        return new ResponseEntity(HttpStatus.CREATED); // 상태코드 2001
    }

    // 가입된 계정을 uuid 기반으로 찾아올 수 있도록 get 요청을 만들어주세요.
    // users/{uuid}로 조회했을 때 해당하는 User 엔티티 객체를 받아옵니다.
    @GetMapping("users/{uuid}")
    public ResponseEntity<?> findUserByUuid(@PathVariable String uuid){
        ResponseFindUserDto userDto = userService.findUserByUuid(uuid);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("users/all")
    public ResponseEntity<?> findAllUser(){
        return ResponseEntity.ok(userService.findAllUser());
    }


}
