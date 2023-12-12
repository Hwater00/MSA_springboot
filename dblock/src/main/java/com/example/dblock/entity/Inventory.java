package com.example.dblock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;//물품명
    private Long count; // 재고량 -> 특정한 물품이 몇개가 되는지 관리해주는 테이블임,,

    // 재고가 0개 미만으로 떨어지지 않도록 검증해주는 메서드
    public void decrease(Long count){
        // 일종의 setter역할을 함
        if(this.count - count <0){
            throw  new RuntimeException("재고량이 부족해 판매할 수 없습니다."); //예외처리를 엔티티 내부에서
        }
        this.count -=count;
    }
}
