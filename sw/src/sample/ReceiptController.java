package sample;

import sample.object.*;

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

public class ReceiptController implements Initializable {
    @FXML
    private TableView<Receipt> myTableView;
    @FXML
    private TableColumn<Receipt, Integer> Column1;
    @FXML
    private TableColumn<Receipt, String> Column2;
    @FXML
    private TableColumn<Receipt, String> Column3;
    @FXML
    private TableColumn<Receipt, Integer> Column4;
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
            Receipt r = myTableView.getItems().get(selectedIndex);
            jsql.setFlag(2);
            jsql.delete(r.getRid().get());
            myTableView.getItems().remove(selectedIndex);
        }
    }
    @FXML
    private void handleToLookUp() { //상세 정보화면 이동
        try{
            Parent fxml = FXMLLoader.load(getClass().getResource("goods.fxml"));
            Stage primaryStage = (Stage)ChangeBill.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void handleToPay() { //결제화면 이동
        try{
            Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage primaryStage = (Stage)ChangePayment.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    ObservableList<Receipt> myList;
    int size;

    String USERNAME;
    String PASSWORD;
    String URL;
    Jsql jsql;
    DateFormat dateFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //변수 형시 변경 부탁드립니다 ㅜ

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

        jsql.setFlag(2);
        myList = (ObservableList<Receipt>) jsql.selectAll();
        size = myList.size();
        Column1.setCellValueFactory(cellData -> cellData.getValue().getRid().asObject());
        Column2.setCellValueFactory(cellData -> cellData.getValue().getPayTime());
        Column3.setCellValueFactory(cellData -> cellData.getValue().getPayMode());
        Column4.setCellValueFactory(cellData -> cellData.getValue().getPayValue().asObject());
        myTableView.setItems(myList);
    }
}
