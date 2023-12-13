package com.example.dblock.facade;

import com.example.dblock.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedissonLockFacade {

    // Redisson은 아래 인스턴스가 레디스 연동을 제어함
    private final RedissonClient redissonClient;

    private final InventoryService inventoryService;

    public void decrease(Long key, Long count){
        RLock lock = redissonClient.getLock(key.toString());

        try{
            boolean available = lock.tryLock(10,1, TimeUnit.SECONDS);

            // 해당 스레드가 락을 얻었는지 아닌지 확인하고 못 얻으면 return
            if(!available){
                System.out.println("Lock 획득 실패");
                return;
            }

            inventoryService.decrease(key,count);

        }catch (InterruptedException e){
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
