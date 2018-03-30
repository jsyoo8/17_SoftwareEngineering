package sample;

import sample.object.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TableView<Paylist> myTableView;
    @FXML
    private TableColumn<Paylist, String> Column11;
    @FXML
    private TableColumn<Paylist, Integer> Column12;
    @FXML
    private TableColumn<Paylist, Integer> Column13;
    @FXML
    private TableColumn<Paylist, Integer> Column14;
    @FXML
    private TableView<Goods> myTableView2;
    @FXML
    private TableColumn<Goods, Integer> Column21;
    @FXML
    private TableColumn<Goods, String> Column22;
    @FXML
    private TableColumn<Goods, Integer> Column23;
    @FXML
    private TableColumn<Goods, Integer> Column24;
    @FXML
    private TextField SumPrice;
    @FXML
    private Button addButton;
    @FXML
    private Button delButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button ChangePayment;
    @FXML
    private Button ChangeBill;
    @FXML
    private CheckBox chkCard;

    @FXML
    private void handleDeletePerson() { //초기화하는 함수
        jsql.setFlag(3);
        jsql.delete(-1);
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage primaryStage = (Stage) refreshButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSubPerson() { //계산된 항목의 갯수를 빼는 함수
        if (myTableView.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView.getSelectionModel().getSelectedIndex();
            Paylist p = myTableView.getItems().get(selectedIndex);
            jsql.minusList(p.getGoodsName());
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
                Stage primaryStage = (Stage) refreshButton.getScene().getWindow();
                primaryStage.setScene(new Scene(fxml, 700, 900));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (myTableView2.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView2.getSelectionModel().getSelectedIndex();
            Goods g = myTableView2.getItems().get(selectedIndex);
            jsql.minusList(g.getGoodsName().get());
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
                Stage primaryStage = (Stage) refreshButton.getScene().getWindow();
                primaryStage.setScene(new Scene(fxml, 700, 900));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleAddPerson() { //계산 메뉴에 항목을 추가하는 함수
        if (myTableView.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView.getSelectionModel().getSelectedIndex();
            Paylist p = myTableView.getItems().get(selectedIndex);
            jsql.plusList(p.getGoodsName());
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
                Stage primaryStage = (Stage) addButton.getScene().getWindow();
                primaryStage.setScene(new Scene(fxml, 700, 900));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (myTableView2.getSelectionModel().getSelectedIndex() >= 0) {
            int selectedIndex = myTableView2.getSelectionModel().getSelectedIndex();
            Goods g = myTableView2.getItems().get(selectedIndex);
            jsql.plusList(g.getGoodsName().get());
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
                Stage primaryStage = (Stage) addButton.getScene().getWindow();
                primaryStage.setScene(new Scene(fxml, 700, 900));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleToLookUp() { //상세 정보화면 이동
        jsql.setFlag(3);
        jsql.delete(-1);
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("goods.fxml"));
            Stage primaryStage = (Stage) addButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToBill() { //영수증화면 이동
        jsql.setFlag(3);
        jsql.delete(-1);
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("receipt.fxml"));
            Stage primaryStage = (Stage) ChangeBill.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleToPay() { //계산 화면 새로고침
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage primaryStage = (Stage) refreshButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePayment() { //결제 버튼
        jsql.setFlag(3);
        ObservableList<Paylist> p = (ObservableList<Paylist>) jsql.selectAll();
        int lastIndex = jsql.getLastIndex(2) + 1;
        jsql.setFlag(0);
        for (int i = 0; i < p.size(); i++) {
            jsql.setFlag(0);
            jsql.insert(lastIndex, p.get(i).getGoodsName(), p.get(i).getPrice(), p.get(i).getCount());
            jsql.setFlag(1);
            jsql.update(-1,p.get(i).getGoodsName(), p.get(i).getPrice(), p.get(i).getCount());
        }
        jsql.setFlag(2);
        Date dt = new Date();
        String time = dateFormat.format(dt);
        int value = Integer.parseInt(SumPrice.getText());
        if (iscard) {
            jsql.insert(lastIndex, time, "카드", value);
        } else {
            jsql.insert(lastIndex, time, "현금", value);
        }
        jsql.setFlag(3);
        jsql.delete(-1);
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("payment.fxml"));
            Stage primaryStage = (Stage) refreshButton.getScene().getWindow();
            primaryStage.setScene(new Scene(fxml, 700, 900));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlecCheckCard() { //카드 결제 체크박스
        iscard = chkCard.isSelected(); //체크 유무를 boolean 값으로 받는듯?
    }

    ObservableList<Paylist> myList;
    ObservableList<Goods> myList2;
    int size;
    int size2;
    boolean iscard;
    int sumPrice;

    String USERNAME;
    String PASSWORD;
    String URL;
    Jsql jsql;
    DateFormat dateFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //변수 형식 변경 부탁드립니다 ㅜㅜ
        sumPrice = 0;
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

        // paylist table
        jsql.setFlag(3);
        myList = (ObservableList<Paylist>) jsql.selectAll();
        size = myList.size();
        Column11.setCellValueFactory(cellData -> cellData.getValue().goodsNameProperty());
        Column12.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        Column13.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());
        Column14.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());
        myTableView.setItems(myList);

        // goods table
        jsql.setFlag(1);
        myList2 = (ObservableList<Goods>) jsql.selectAll();
        size2 = myList2.size();
        Column21.setCellValueFactory(cellData -> cellData.getValue().getGid().asObject());
        Column22.setCellValueFactory(cellData -> cellData.getValue().getGoodsName());
        Column23.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        Column24.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
        myTableView2.setItems(myList2);

        jsql.setFlag(3);
        ObservableList<Paylist> p = (ObservableList<Paylist>) jsql.selectAll();
        for (int i = 0; i < p.size(); i++) {
            sumPrice += p.get(i).getTotal();
        }
        SumPrice.setText(String.valueOf(sumPrice));
    }
}
