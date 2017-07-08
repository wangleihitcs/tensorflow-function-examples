package com.example.wanglei.treasury.dao;

public class MySQLProperties {
	private String servername;
	private String databasename;
	private String serverport;
	private String url;
	private String username;
	private String password;
	private String driver;


	private String filepath;
	

	public MySQLProperties () {
		//连接MySQL数据库的相关参数
		driver = "com.mysql.jdbc.Driver";
//
//		servername = "192.168.6.85";
//		serverport = "3306";
//		databasename = "treasory";
//		username = "admin";
//		password = "admin";
		servername = "192.168.6.52";
		serverport = "3306";
		databasename = "treasury";
		username = "test";
		password = "test";
		//备份文件路径
		filepath = "./treasury.sql";
		url = "jdbc:mysql://"+ servername +":" +
				serverport + "/" + databasename;

	}
	
	public String getServername() {
		return servername;
	}
	
	public String getDatabasename() {
		return databasename;
	}
	
	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public String getDriver() {
		return driver;
	}
}
