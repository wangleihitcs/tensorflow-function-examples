package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;
import com.example.wanglei.treasury.utils.PublicData;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liulo on 2017/7/11.
 */

public class GetLineData {
    public double[] getLineData(int beginMonth) throws ClassNotFoundException, SQLException, SQLException, ParseException {
        double[] moneyInAndOut = new double[10];
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
        String sql = "select * from bill where date > '" + begin + "'"
                + " and username = '" + PublicData.userEntity.getUsername() + "'";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            String curDateStr = rs.getString("date");
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            Date curDate = sdf.parse(curDateStr);
            int month = curDate.getMonth();
            if (rs.getString("type").equals("1")){
                moneyInAndOut[month-beginMonth] += Double.parseDouble(rs.getString("money"));

            }
            else {
                moneyInAndOut[month-beginMonth + 5] += Double.parseDouble(rs.getString("money"));
            }
        }
        rs.close();
        stmt.close();
        conn.close();
//        for (double i : moneyInAndOut) {
//            System.out.println(i);
//        }
        System.out.println();
        return moneyInAndOut;
    }
}
