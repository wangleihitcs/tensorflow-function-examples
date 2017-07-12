package com.example.wanglei.treasury.service;

import com.example.wanglei.treasury.dao.MySQLProperties;
import com.example.wanglei.treasury.entity.UserEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by liulo on 2017/7/12.
 */

public class UserService {
    public void addUser(UserEntity user) throws ClassNotFoundException, SQLException {
        String username = user.getUsername();
        String password = user.getUserpassword();
        String name = user.getName();
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());

        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        String insert_sql="insert into user values("+"'"+username+"','"+password+"','"+name+"')";
        System.out.println(insert_sql);
        Statement stmt=conn.createStatement();
        stmt.executeUpdate(insert_sql);

        stmt.close();
        conn.close();
    }

    public UserEntity checkLogin(String username, String psw) throws ClassNotFoundException, SQLException {
        UserEntity user = null;
        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();

        String sql = "select password, name from user where username = '" + username + "'";
        ResultSet rs = stmt.executeQuery(sql);
        //计算指定月份的
        while (rs.next()) {
            if (psw.equals(rs.getString("password"))){
                user = new UserEntity();
                user.setUsername(username);
                user.setUserpassword(psw);
                user.setName(rs.getString("name"));
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

    public void update(UserEntity user) throws ClassNotFoundException, SQLException {

        MySQLProperties mp = new MySQLProperties();
        Class.forName(mp.getDriver());  //加载数据库连接驱动
        //连接数据库
        Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
        Statement stmt = conn.createStatement();
        String update_sql = "update user set password = '" + user.getUserpassword() +
                "', name = '" + user.getName() +
                "' where username = '" + user.getUsername() + "'";
        stmt.executeUpdate(update_sql);
        stmt.close();
        conn.close();
    }
}
