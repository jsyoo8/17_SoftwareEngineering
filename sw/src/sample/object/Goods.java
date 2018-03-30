package sample.object;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Goods {
    private IntegerProperty gid;
    private StringProperty goodsName;
    private IntegerProperty price;
    private IntegerProperty stock;

    public Goods(int gid, String goodsName, int price, int stock) {
        this.gid = new SimpleIntegerProperty(gid);
        this.goodsName = new SimpleStringProperty(goodsName);
        this.price = new SimpleIntegerProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public IntegerProperty getGid() {
        return gid;
    }

    public void setGid(IntegerProperty gid) {
        this.gid = gid;
    }

    public StringProperty getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(StringProperty goodsName) {
        this.goodsName = goodsName;
    }

    public IntegerProperty getPrice() {
        return price;
    }

    public void setPrice(IntegerProperty price) {
        this.price = price;
    }

    public IntegerProperty getStock() {
        return stock;
    }

    public void setStock(IntegerProperty stock) {
        this.stock = stock;
    }
}
