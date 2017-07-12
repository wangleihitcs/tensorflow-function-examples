package com.example.wanglei.treasury.service;

import android.os.Debug;
import android.util.Log;

import com.example.wanglei.treasury.entity.BillEntity;
import com.example.wanglei.treasury.dao.MySQLProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author think
 *
 */
public class SavaBill {

	public void saveBill(BillEntity bill) throws ClassNotFoundException, SQLException {

		String billId = bill.getBillId(); //账单编号
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat ("yyyy-MM-dd");
		String date = formatter.format(bill.getDate()); //日期
		int type = bill.getType(); //类型，1收入,0支出
		double money = bill.getMoney(); //金额
		String expalin = bill.getExpalin(); //说明
		String username = bill.getUsername(); //用户名

		MySQLProperties mp = new MySQLProperties();
		Class.forName(mp.getDriver());

		Connection conn = DriverManager.getConnection(mp.getUrl(), mp.getUsername(), mp.getPassword());
		String insert_sql="insert into bill values("+"'"+billId+"','"+date+"','"+money+"','"+expalin+"','"+type+"','"+username+"')";
		System.out.println(insert_sql);
		Statement stmt=conn.createStatement();
		stmt.executeUpdate(insert_sql);

		stmt.close();
		conn.close();
	}
}
