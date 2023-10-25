package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.RequestCreateUserDto;
import com.example.userservice.dto.ResponseFindUserDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repoaitory.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public void creatUser(RequestCreateUserDto userDto){
        // dto를 entity로 변경해주는 작업이 필요
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public ResponseFindUserDto findUserByUuid(String uuid){
        User user = userRepository.findUserByUuid(uuid);

        if(user == null)
            throw  new UserNotFoundException("해당 유저는 존재하지 않습니다");

        return new ResponseFindUserDto(user);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }
}
