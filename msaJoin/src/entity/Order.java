package entity;

public class Order {
    private int userId;
    private String itemName;
    private  int count;

    public Order(int userId, String itemName, int count) {
        this.userId = userId;
        this.itemName = itemName;
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", itemName='" + itemName + '\'' +
                ", count=" + count +
                '}';
    }
}
