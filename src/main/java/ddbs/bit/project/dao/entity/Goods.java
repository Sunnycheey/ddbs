package ddbs.bit.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @program: ddbs
 * @description: goods entity
 * @author: lihuichao
 * @create: 2019-12-16
 **/
@TableName("Goods")
public class Goods {
    private long id;
    private long userId;
    private String name;
    private String introduction;
    private String pictureURL;
    private String kind;
    private int originalPrice;
    private int currentPrice;
    private int amount;
    private int uploadTime;

    public Goods(long id, long userId, String name, String introduction, String pictureURL, String kind, int originalPrice, int currentPrice, int amount, int uploadTime) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.pictureURL = pictureURL;
        this.kind = kind;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.amount = amount;
        this.uploadTime = uploadTime;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(int uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", kind='" + kind + '\'' +
                ", originalPrice=" + originalPrice +
                ", currentPrice=" + currentPrice +
                ", amount=" + amount +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
