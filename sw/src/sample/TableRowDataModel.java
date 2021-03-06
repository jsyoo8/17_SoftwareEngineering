package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TableRowDataModel {
    private StringProperty name;
    private StringProperty address;
    private StringProperty gender;
    private IntegerProperty classNum;

    public TableRowDataModel(StringProperty name, StringProperty address, StringProperty gender, IntegerProperty classNum) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.classNum = classNum;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public IntegerProperty classNumProperty() {
        return classNum;
    }
}