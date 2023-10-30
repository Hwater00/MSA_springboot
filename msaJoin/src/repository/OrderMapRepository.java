package repository;

import entity.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepository implements OrderRepository{

    private Map<Integer,Order> orderList;

    public OrderMapRepository(){
        // 초기화
        this.orderList = new HashMap<Integer,Order>();

        // 더미데이터를 Map 형태로 넣는다
        Order order1= new Order(1,"감자",3);
        Order order2= new Order(1,"고구마",4);
        Order order3= new Order(1,"맥북",1);
        Order order4= new Order(2,"감자튀김",4);
        orderList.put(1,order1);
        orderList.put(2,order2);
        orderList.put(3,order3);
        orderList.put(4,order4);

    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        List<Order> userOrderList = new ArrayList<>();

        // 조회하는 유저의 주문리스트면 리스트에 저장, 아니면 그냥 넘어가는 로직
        for(int i=1; i<5; i++){
            if(orderList.get(i).getUserId() == userId){
                userOrderList.add(orderList.get(i));
            }
        }

        return userOrderList;
    }
}
