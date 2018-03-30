package sample.object;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Receipt {
    private IntegerProperty rid;
    private StringProperty payTime;
    private StringProperty payMode;
    private IntegerProperty payValue;

    public Receipt(int rid, String payTime, String payMode, int payValue) {
        this.rid = new SimpleIntegerProperty(rid);
        this.payTime = new SimpleStringProperty(payTime);
        this.payMode = new SimpleStringProperty(payMode);
        this.payValue = new SimpleIntegerProperty(payValue);
    }

    public IntegerProperty getRid() {
        return rid;
    }

    public void setRid(IntegerProperty rid) {
        this.rid = rid;
    }

    public StringProperty getPayTime() {
        return payTime;
    }

    public void setPayTime(StringProperty payTime) {
        this.payTime = payTime;
    }

    public StringProperty getPayMode() {
        return payMode;
    }

    public void setPayMode(StringProperty payMode) {
        this.payMode = payMode;
    }

    public IntegerProperty getPayValue() {
        return payValue;
    }

    public void setPayValue(IntegerProperty payValue) {
        this.payValue = payValue;
    }
}
