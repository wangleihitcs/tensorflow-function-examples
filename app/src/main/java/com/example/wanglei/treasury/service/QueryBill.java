package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by liulo on 2017/7/8.
 */

public class QueryBill {
    ArrayList<ArrayList<String>> recordList = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> query(String begin, String end, String type) throws ClassNotFoundException, SQLException, SQLException {
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select * from bill where date < '" + end + "' and "
                + "date > '" + begin + "' and type = " + type;
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            ArrayList<String> record = new ArrayList<String>();
            int n = 6;
            for (int i = 1; i <= n; i++) {
                record.add(rs.getString(i));
            }

            recordList.add(record);
        }
        rs.close();
        stmt.close();
        conn.close();

        return recordList;
    }

    public ArrayList<ArrayList<String>> query(String type) throws ClassNotFoundException, SQLException, SQLException {
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select * from bill where type = " + type;
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            ArrayList<String> record = new ArrayList<String>();
            int n = 6;
            for (int i = 1; i <= n; i++) {
                if (i == 2) {
                    record.add(rs.getDate(i).toString());
                }
                else{
                    record.add(rs.getString(i));
                }
            }

            recordList.add(record);
        }
        rs.close();
        stmt.close();
        conn.close();

        return recordList;
    }

    public ArrayList<ArrayList<String>> query(String begin, String end) throws ClassNotFoundException, SQLException, SQLException {
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select * from bill where date < '" + end + "' and "
                + "date > '" + begin + "'";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            ArrayList<String> record = new ArrayList<String>();
            int n = 6;
            for (int i = 1; i <= n; i++) {
                record.add(rs.getString(i));
            }

            recordList.add(record);
        }
        rs.close();
        stmt.close();
        conn.close();

        return recordList;
    }
}
