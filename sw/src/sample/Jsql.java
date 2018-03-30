package sample;

import sample.object.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Jsql {
    Connection conn = null;
    Statement stmt = null;
    int flag; // (0:payment), (1:goods), (2:receipt), (3:paylist)
    ArrayList<Integer> lastIndex;

    public Jsql(Connection conn) {
        this.conn = conn;
        lastIndex = new ArrayList<>();
        lastIndex.add(0);
        lastIndex.add(0);
        lastIndex.add(0);
        lastIndex.add(0);
        try {
            this.stmt = conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    // 삽입
    public void insert(Object i0, Object i1, Object i2, Object i3) {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            String sql = sb.append("insert into payment values(")
                    .append(i0 + ",")
                    .append("'" + i1 + "',")
                    .append(i2 + ",")
                    .append(i3)
                    .append(");")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 1) {
            String sql = sb.append("insert into goods values(")
                    .append(i0 + ",")
                    .append("'" + i1 + "',")
                    .append(i2 + ",")
                    .append(i3)
                    .append(");")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 2) {
            String sql = sb.append("insert into receipt values(")
                    .append(i0 + ",")
                    .append("'" + i1 + "',")
                    .append("'" + i2 + "',")
                    .append(i3)
                    .append(");")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String sql = sb.append("insert into paylist values(")
                    .append("'" + i0 + "',")
                    .append(i1 + ",")
                    .append(i2 + ",")
                    .append(i3)
                    .append(");")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 삭제
    public void delete(Object id) {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            String sql = sb.append("delete from payment where pid = ")
                    .append(id)
                    .append(";")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 1) {
            String sql = sb.append("delete from goods where gid = ")
                    .append(id)
                    .append(";")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 2) {
            String sql = sb.append("delete from receipt where rid = ")
                    .append(id)
                    .append(";")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            if ((int) id == -1) {
                String sql = sb.append("delete from paylist")
                        .append(";")
                        .toString();
                try {
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                String sql = sb.append("delete from paylist where goods_name = '")
                        .append(id + "'")
                        .append(";")
                        .toString();
                try {
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // 수정
    public void update(Object i0, Object i1, Object i2, Object i3) {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            String sql = sb.append("update payment set")
                    .append(" pid = " + i0 + ",")
                    .append("goods_name = '" + i1 + "',")
                    .append("price = " + i2 + ",count = ")
                    .append(i3)
                    .append(" where pid = ")
                    .append(i0)
                    .append(";").toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 1) {
            String sql;
            if ((int) i0 == -1) {
                sb.append("select stock from goods where goods_name = '" + i1 + "';");
                int stock = 0;
                try {
                    ResultSet rs = stmt.executeQuery(sb.toString());
                    while (rs.next()) {
                        stock = rs.getInt("stock");
                    }
                } catch (Exception e) {

                }
                sb = new StringBuilder();
                sql = sb.append("update goods set")
                        .append(" goods_name = ")
                        .append("'" + i1 + "',price = ")
                        .append(i2 + ",stock = ")
                        .append((stock - (int) i3))
                        .append(" where goods_name = '")
                        .append(i1)
                        .append("';").toString();

            } else {
                sql = sb.append("update goods set")
                        .append(" gid = " + i0 + ",goods_name = ")
                        .append("'" + i1 + "',price = ")
                        .append(i2 + ",stock = ")
                        .append(i3)
                        .append(" where gid = ")
                        .append(i0)
                        .append(";").toString();
            }
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (flag == 2) {
            String sql = sb.append("update receipt set")
                    .append(" rid = " + i0 + ",pay_time = ")
                    .append("'" + i1 + "',pay_mode = ")
                    .append("'" + i2 + "',pay_value = ")
                    .append(i3)
                    .append(" where id = ")
                    .append(i0)
                    .append(";").toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String sql = sb.append("update paylist set")
                    .append(" goods_name = '" + i0 + "',price = ")
                    .append(i1 + ",count = ")
                    .append(i2 + ",total = ")
                    .append(i3)
                    .append(" where goods_name = '")
                    .append(i0 + "'")
                    .append(";").toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 모든 검색
    public Object selectAll() {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            ObservableList<Payment> re = FXCollections.observableArrayList();
            Payment payment;
            String sql = sb.append("select * from payment")
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    payment = new Payment(rs.getInt("pid"), rs.getString("goods_name"), rs.getInt("price"), rs.getInt("count"));
                    re.add(payment);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else if (flag == 1) {
            ObservableList<Goods> re = FXCollections.observableArrayList();
            Goods goods;
            String sql = sb.append("select * from goods")
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    goods = new Goods(rs.getInt("gid"), rs.getString("goods_name"), rs.getInt("price"), rs.getInt("stock"));
                    re.add(goods);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else if (flag == 2) {
            ObservableList<Receipt> re = FXCollections.observableArrayList();
            Receipt receipt;
            String sql = sb.append("select * from receipt")
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    receipt = new Receipt(rs.getInt("rid"), rs.getString("pay_time"), rs.getString("pay_mode"), rs.getInt("pay_value"));
                    re.add(receipt);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else {
            ObservableList<Paylist> re = FXCollections.observableArrayList();
            Paylist paylist;
            String sql = sb.append("select * from paylist")
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    paylist = new Paylist(rs.getString("goods_name"), rs.getInt("price"), rs.getInt("count"), rs.getInt("total"));
                    re.add(paylist);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        }
    }

    // 검색
    public Object select(Object id) {
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            ObservableList<Payment> re = FXCollections.observableArrayList();
            Payment payment;
            String sql = sb.append("select * from payment where")
                    .append(" pid = ")
                    .append((int) id)
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    payment = new Payment(rs.getInt("pid"), rs.getString("goods_name"), rs.getInt("price"), rs.getInt("count"));
                    re.add(payment);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else if (flag == 1) {
            ObservableList<Goods> re = FXCollections.observableArrayList();
            Goods goods;
            String sql = sb.append("select * from goods where")
                    .append(" gid = ")
                    .append((int) id)
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    goods = new Goods(rs.getInt("gid"), rs.getString("goods_name"), rs.getInt("price"), rs.getInt("stock"));
                    re.add(goods);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else if (flag == 2) {
            ObservableList<Receipt> re = FXCollections.observableArrayList();
            Receipt receipt;
            String sql = sb.append("select * from receipt where")
                    .append(" rid = ")
                    .append((int) id)
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    receipt = new Receipt(rs.getInt("rid"), rs.getString("pay_time"), rs.getString("pay_mode"), rs.getInt("pay_value"));
                    re.add(receipt);
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        } else {
            ObservableList<Paylist> re = FXCollections.observableArrayList();
            Paylist paylist;
            String sql = sb.append("select * from paylist where")
                    .append(" goods_name = ")
                    .append((String) id)
                    .append(";").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    paylist = new Paylist(rs.getString("goods_name"), rs.getInt("price"), rs.getInt("count"), rs.getInt("total"));
                    re.add(paylist);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return re;
        }
    }

    public int getLastIndex(int flag) {
        int re = 0;
        StringBuilder sb = new StringBuilder();
        if (flag == 0) {
            String sql = sb.append("select pid from payment order by pid desc limit 1;").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    re = rs.getInt("pid");
                }
                lastIndex.set(0, re);
                return re;
            } catch (SQLException e) {
                lastIndex.set(0, 0);
                return 0;
            }
        } else if (flag == 1) {
            String sql = sb.append("select gid from goods order by gid desc limit 1;").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    re = rs.getInt("gid");
                }
                lastIndex.set(1, re);
                return re;
            } catch (SQLException e) {
                lastIndex.set(1, 0);
                return 0;
            }
        } else if (flag == 2) {
            String sql = sb.append("select rid from receipt order by rid desc limit 1;").toString();
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    re = rs.getInt("rid");
                }
                lastIndex.set(2, re);
                return re;
            } catch (SQLException e) {
                lastIndex.set(2, 0);
                return 0;
            }
        }
        return re;
    }

    public void plusList(Object name) {
        StringBuilder sb = new StringBuilder();
        sb.append("select goods_name from paylist where goods_name = '" + name + "';");
        Boolean isEmpty = false;
        try {
            ResultSet rs = stmt.executeQuery(sb.toString());
            if (!rs.isBeforeFirst()) {
                isEmpty = true;
            }
            if (isEmpty) {
                sb = new StringBuilder();
                sb.append("select * from goods where goods_name = '" + name + "';");
                ResultSet rs3 = stmt.executeQuery(sb.toString());
                sb = new StringBuilder();
                int price = 0;
                while (rs3.next()) {
                    price = rs3.getInt("price");
                }
                sb.append("insert into paylist values('" + name + "'," + price + ",1," + price + ");");
                stmt.executeUpdate(sb.toString());
            } else {
                sb = new StringBuilder();
                sb.append("select stock from goods where goods_name = '" + name + "';");
                ResultSet rs1 = stmt.executeQuery(sb.toString());
                int stock = 0;
                while (rs1.next()) {
                    stock = rs1.getInt("stock");
                }
                sb = new StringBuilder();
                sb.append("select * from paylist where goods_name = '" + name + "';");
                ResultSet rs2 = stmt.executeQuery(sb.toString());
                int count = 0;
                int total = 0;
                int price = 0;
                while (rs2.next()) {
                    count = rs2.getInt("count");
                    total = rs2.getInt("total");
                    price = rs2.getInt("price");
                }
                if (count < stock) {
                    sb = new StringBuilder();
                    sb.append("update paylist set count=" + (count + 1) + ",total=" + (total + price) + " where goods_name = '" + name + "';");
                    stmt.executeUpdate(sb.toString());
                }
            }
        } catch (SQLException e) {
        }
    }

    public void minusList(Object name) {
        StringBuilder sb = new StringBuilder();
        sb.append("select goods_name from paylist where goods_name = '" + name + "';");
        Boolean isExist = true;
        try {
            ResultSet rs = stmt.executeQuery(sb.toString());
            if (!rs.isBeforeFirst()) {
                isExist = false;
            }
            if (isExist) {
                stmt.executeQuery(sb.toString());
                sb = new StringBuilder();
                sb.append("select * from paylist where goods_name = '" + name + "';");
                ResultSet rs1 = stmt.executeQuery(sb.toString());
                int count = 0;
                int total = 0;
                int price = 0;
                while (rs1.next()) {
                    count = rs1.getInt("count");
                    total = rs1.getInt("total");
                    price = rs1.getInt("price");
                }
                sb = new StringBuilder();
                sb.append("update paylist set count=" + (count - 1) + ",total=" + (total - price) + " where goods_name = '" + name + "';");
                stmt.executeUpdate(sb.toString());
                sb = new StringBuilder();
                sb.append("select count from paylist where goods_name = '" + name + "';");
                ResultSet rs2 = stmt.executeQuery(sb.toString());
                while (rs2.next()) {
                    count = rs2.getInt("count");
                }
                if (count == 0) {
                    sb = new StringBuilder();
                    sb.append("delete from paylist where goods_name = '" + name + "';");
                    stmt.executeUpdate(sb.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}