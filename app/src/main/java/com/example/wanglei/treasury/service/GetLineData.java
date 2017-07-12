package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liulo on 2017/7/11.
 */

public class GetLineData {
    private ArrayList<ArrayList<String>> recordList = new ArrayList<ArrayList<String>>();

    public int[] getMoneyInData(ArrayList<ArrayList<String>> recordList, int curMonth) {
        int[] moneyIn = new int[5];
        int beginMonth;
        if (curMonth >= 5) {
            beginMonth = curMonth - 4;
        }
        else {
             beginMonth = curMonth + 12 - 4;
        }

        for (ArrayList<String> record: recordList) {

            String curDateStr = record.get(2);
            Date curDate = new Date(curDateStr);
            int month = curDate.getMonth();
            moneyIn[month-beginMonth] += Integer.parseInt(record.get(3));
        }
        return moneyIn;
    }


    public int[] getLineData(int beginMonth) throws ClassNotFoundException, SQLException, SQLException {
        int[] moneyInAndOut = new int[10];
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();
        int year = 2017;
        String begin;
        if (beginMonth >= 10)
        {
            begin = year + "-" + beginMonth +"-01";
        }
        else
        {
            begin = year + "-0" + beginMonth +"-01";
        }
        String sql = "select * from bill where date > '" + begin + "'";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            String curDateStr = rs.getString("date");
            Date curDate = new Date(curDateStr);
            int month = curDate.getMonth();
            if (rs.getString("type").equals("1")){
                moneyInAndOut[month-beginMonth] += Integer.parseInt(rs.getString("money"));
            }
            else {
                moneyInAndOut[month-beginMonth + 5] += Integer.parseInt(rs.getString("money"));
            }
        }
        rs.close();
        stmt.close();
        conn.close();

        return moneyInAndOut;
    }
}
