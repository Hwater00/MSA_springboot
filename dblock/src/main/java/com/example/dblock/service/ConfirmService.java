package com.example.dblock.service;

import com.example.dblock.entity.Coupon;
import com.example.dblock.repository.CouponCountRepository;
import com.example.dblock.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public void confirm(Long userId){
        // select count(*) from count가 아래 로직이다.
        // DB에 직접 질의해서 요청 횟수 카운트하기
        //long count = couponRepository.count(); // JPA를 이용해 해당 테이블(Coupon) row 개수

        // 레디스에서 아코믹연산화 시켜서 하는 카운트
        Long count = couponCountRepository.increment(); // 1증가 시킨 후, 증가된 해당 값을 리턴함

        if(count > 100){
            return; // 발급된 쿠폰이 1000개가 넘으면 발급 방지
        }

        couponRepository.save(new Coupon(userId));
    }

    // 테스트 초기화를 위한 로직용.
    public void reset(){
        couponCountRepository.reset();
    }

}
