package ddbs.bit.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: ddbs
 * @description: Orders entity
 * @author: lihuichao
 * @create: 2019-12-16
 **/

@TableName("orders")
public class Orders {
    private long id;
    private long goodId;
    private long userId;
    private int totalPrice;
    private int goodsNumber;
    private String address;
    private String phoneNumber;
    private int orderTime;
    private String orderState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", goodsNumber=" + goodsNumber +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderTime=" + orderTime +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}
