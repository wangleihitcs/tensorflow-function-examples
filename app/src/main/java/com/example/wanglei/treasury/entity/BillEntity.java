package com.example.wanglei.treasury.entity;

import java.util.Date;

/**
 * Created by wanglei on 2017/7/6.
 * 账单信息
 */

public class BillEntity {
    private String billId; //账单编号
    private Date date; //日期
    private int type; //类型，1收入,0支出
    private double money; //金额
    private String expalin; //说明
    private String username; //用户名

    public BillEntity() {

    }

    public BillEntity(String billId, Date date, double money, String expalin, int type, String username) {
        this.billId = billId;
        this.date = date;
        this.type = type;
        this.money = money;
        this.expalin = expalin;
        this.username = username;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getExpalin() {
        return expalin;
    }

    public void setExpalin(String expalin) {
        this.expalin = expalin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "BillEntity{" +
                "billId=" + billId +
                ", date=" + date +
                ", type=" + type +
                ", money=" + money +
                ", expalin='" + expalin + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
