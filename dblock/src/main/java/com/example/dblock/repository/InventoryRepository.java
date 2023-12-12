package com.example.dblock.repository;

import com.example.dblock.entity.Inventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    // DB쪽에서 락을 건다.
    // Entity를 제어하는 레포지토리 인터페이스에 추가 메서드를 작성해 특정 메서드에 락을 걸 수 있습니다.
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from Inventory i where i.id = :id")
    Inventory findByIdPessmistic(Long id);
}
