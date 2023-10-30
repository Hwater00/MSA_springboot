package dto;

import entity.Order;

import java.util.List;

// Getter , Setter 필수
// 필드를 합친 DTO를 만들어두고
public class UserOrderDto {

    private int userId;
    private String name;
    private int age;

    // 연관관계를 가져갈 수 있는 Order목록
    private List<Order> orderList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "UserOrderDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", orderList=" + orderList +
                '}';
    }
}
