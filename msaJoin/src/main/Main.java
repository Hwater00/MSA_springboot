package main;

import dto.UserOrderDto;
import entity.Order;
import entity.User;
import repository.OrderMapRepository;
import repository.OrderRepository;
import repository.UserMapRepository;
import repository.UserRepository;

import java.util.List;

// 내부에서 조인 형식으로 User가 가지고 있는 구매내역을 가져와보겠습니다.
public class Main {

    public static void main(String[] args) {

        // 맵 형태 자료를 저장소에서 꺼낼 수 있도록 레포지토리 객체 생성
        OrderRepository orderRepository = new OrderMapRepository();
        UserRepository  userRepository = new UserMapRepository();

        // 구매 내역 있음
        User user1 = userRepository.getUserByUserId(1);
        // 구매 내역 없음
        User user3 = userRepository.getUserByUserId(3);
        System.out.println(user1);
        System.out.println(user3);

        // 조인처럼 동작시키기 위해서 특정 유저의 구매 내역을 다 가져오기
        List<Order> orderList1 = orderRepository.getOrderByUserId(user1.getUserId());
        List<Order> orderList3 = orderRepository.getOrderByUserId(user3.getUserId());
        System.out.println(orderList1);
        System.out.println(orderList3);

        // user1과 order1 조인 형식으로 객체에 저장하기.
        UserOrderDto userOrderDto1 = new UserOrderDto();
        userOrderDto1.setUserId(user1.getUserId());
        userOrderDto1.setAge(user1.getAge());
        userOrderDto1.setName(user1.getName());
        userOrderDto1.setOrderList(orderList1);

        // 조인 여부 체크
        System.out.println(userOrderDto1);

        // user3과 order3 조인 형식으로 객체에 저장하기
        UserOrderDto userOrderDto3 = new UserOrderDto();
        userOrderDto1.setUserId(user3.getUserId());
        userOrderDto1.setAge(user3.getAge());
        userOrderDto1.setName(user3.getName());
        userOrderDto1.setOrderList(orderList3);

        System.out.println(userOrderDto3);
    }
}
