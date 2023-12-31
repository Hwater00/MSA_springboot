package com.example.dblock.facade;

import com.example.dblock.service.OptimisticInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticInventoryFacade {
    // 퍼사드 클래스의 역할은 낙관적 락 서비스의 decrease를 반영될 때까지 지속적으로 재시고하는 로직을
    // Service 객체의 wraping 하는 것
    private final OptimisticInventoryService optimisticInventoryService;

    public void decrease(Long id, Long count) throws  InterruptedException{
        // 성공할때까지 무한반복문
        while (true){
            try {
                // decrease단계에서 충돌임으로 경쟁에서 이기면! decrease내부는 무조건 실행되었음을 보장!
                optimisticInventoryService.decrease(id,count); // 서비스의 decrease 호출
                break;// 위 서비스 구문의 감소가 버전정보가 맞아서 잘 처리되면 반복문 탈출

            }catch (Exception e){
                // 낙관적 락에 의해서 버저닝 정합성이 맞지 않아 예외가 발생했다면
                Thread.sleep(100); // 0.1초 대기 후 재시도
            }
        }
    }
}
