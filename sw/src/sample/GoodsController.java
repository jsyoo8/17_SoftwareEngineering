package sample;

import sample.object.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class GoodsController implements Initializable {
    @FXML
    private TableView<Goods> myTableView;
    @FXML
    private TableColumn<Goods, Integer> Column1;
    @FXML
    private TableColumn<Goods, String> Column2;
    @FXML
    private TableColumn<Goods, Integer> Column3;
    @FXML
    private TableColumn<Goods, Integer> Column4;
    @FXML
    private TextField Field11;
    @FXML
    private TextField Field12;
    @FXML
    private TextField Field13;
    @FXML
    private TextField Field14;
    @FXML
    private TextField Field21;
    @FXML
    private TextField Field22;
    @FXML
    private TextField Field23;
    @FXML
    private TextField Field24;
    @FXML
    private Button addButton;
    @FXML
    private Button ChangePayment;
    @FXML
    private Button ChangeBill;

    @FXML
    private void handleDeletePerson() { //삭제
        if (myTableView.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView.getSelectionModel().getSelectedIndex();
            Goods p = myTableView.getItems().get(selectedIndex);
            jsql.setFlag(1);
            jsql.delete(p.getGid().get());
            myTableView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    private void handleSetPerson() { //수정
        if (myTableView.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView.getSelectionModel().getSelectedIndex();
            Goods p = myTableView.getItems().get(selectedIndex);
            jsql.setFlag(1);
            jsql.update(p.getGid().get(), Field22.getText(),
                    Integer.parseInt(Field23.getText()), Integer.parseInt(Field24.getText()));
            Goods input = new Goods(p.getGid().get(), Field22.getText(),
                    Integer.parseInt(Field23.getText()), Integer.parseInt(Field24.getText()));
            myTableView.getItems().set(selectedIndex, input);
        }
    }

    @FXML
    private void handleAddPerson() { //추가
        jsql.setFlag(1);
        int lastIndex = jsql.getLastIndex(1) + 1;
        jsql.insert(lastIndex, Field12.getText(),
                Integer.parseInt(Field13.getText()), Integer.parseInt(Field14.getText()));
        Goods input = new Goods(lastIndex, Field12.getText(),
                Integer.parseInt(Field13.getText()), Integer.parseInt(Field14.getText()));
        myTableView.getItems().add(input);
    }

    @FXML
    private void handleToPay() { //결제화면 이동
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage primaryStage = (Stage) addButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToBill() { //영수증화면 이동
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("receipt.fxml"));
            Stage primaryStage = (Stage) addButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ObservableList<Goods> myList;
    int size;

    String USERNAME;
    String PASSWORD;
    String URL;
    Jsql jsql;
    DateFormat dateFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        USERNAME = "root";
        PASSWORD = "dizzyy";
        URL = "jdbc:mysql://127.0.0.1:3306/pos?autoReconnect=true&useSSL=false";
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            jsql = new Jsql(conn);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ClassNotFoundException");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("SQLException");
        }

        // goods table
        jsql.setFlag(1);
        myList = (ObservableList<Goods>) jsql.selectAll();
        size = myList.size();
        Column1.setCellValueFactory(cellData -> cellData.getValue().getGid().asObject());
        Column2.setCellValueFactory(cellData -> cellData.getValue().getGoodsName());
        Column3.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        Column4.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        myTableView.setItems(myList);
    }
}
