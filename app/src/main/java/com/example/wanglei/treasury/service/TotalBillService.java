package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;
import com.example.wanglei.treasury.utils.PublicData;

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

    public double[] query() throws ClassNotFoundException, SQLException {
        double[] moneyTotal = {0, 0};
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select * from bill"
                + " where username = '" + PublicData.userEntity.getUsername() + "'";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            if (rs.getString("type").equals("1")) {
                moneyTotal[0] += Double.parseDouble(rs.getString("money"));
            }
            else {
                moneyTotal[1] += Double.parseDouble(rs.getString("money"));
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        return moneyTotal;
    }
}
