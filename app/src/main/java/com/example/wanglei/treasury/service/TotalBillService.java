package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by liulo on 2017/7/12.
 */

public class TotalBillService {

    public HashMap<String, Double> query() throws ClassNotFoundException, SQLException {
        HashMap<String, Double> balance = new HashMap<String, Double>();
        double totalIn = 0.0;
        double totalOut = 0.0;
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select * from bill";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            if (rs.getString("type").equals("1")) {
                totalIn += Double.parseDouble(rs.getString("money"));
            }
            else {
                totalOut += Double.parseDouble(rs.getString("money"));
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        balance.put("moneyIn", totalIn);
        balance.put("moneyOut", totalOut);
        balance.put("balance", totalIn - totalOut);
        return balance;
    }
}
