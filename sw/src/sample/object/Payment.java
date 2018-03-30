package sample.object;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Payment {
    private IntegerProperty pid;
    private StringProperty goodsName;
    private IntegerProperty price;
    private IntegerProperty count;


    public Payment(int pid, String goodsName, int price, int count) {
        this.pid = new SimpleIntegerProperty(pid);
        this.goodsName = new SimpleStringProperty(goodsName);
        this.price = new SimpleIntegerProperty(price);
        this.count = new SimpleIntegerProperty(count);
    }

    public IntegerProperty getPid() {
        return pid;
    }

    public void setPid(IntegerProperty pid) {
        this.pid = pid;
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

    public IntegerProperty getCount() {
        return count;
    }

    public void setCount(IntegerProperty count) {
        this.count = count;
    }
}
