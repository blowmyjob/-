package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import dbBean.Absence;
import dbBean.SignIn;

public class DBConnection {
	private String name;
	private String password;
	private Connection con; 		// 数据库连接对象
	private PreparedStatement pstm; // 数据库预编译处理对象
	private String className; 		// 驱动名
	private String url;

	public DBConnection() {
		className = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://119.29.141.122:3306/kq?characterEncoding=utf-8";
		name = "root";
		password = "527128";
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动程序失败！");
			e.printStackTrace();
		}
	}

	public void getCon() {
		try {
			con = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			System.out.println("获取数据库连接失败！");
			e.printStackTrace();
		}
	}

	// 对象数组。如：String[] obj = new String[]{"宾桀锋","201321173083"};
	public void doPstm(String sql) {
		if (sql != null && !sql.equals("")) {
			System.out.println(sql);
			if (con == null)
				getCon();
			try {
				pstm = con.prepareStatement(sql);
				pstm.execute();
			} catch (SQLException e) {
				System.out.println("调用DB类中doPstm方法时出错！");
				e.printStackTrace();
			}
		}
	}
	
	//更新云数据库的一个方法
	//现用于在人脸库进行人脸注册后，置Student表中的imageFlag为true
	public void doUpdate(String sql) {
		if (sql != null && !sql.equals("")) {
			System.out.println(sql);
			if (con == null)
				getCon();
			try {
				pstm = con.prepareStatement(sql);
				pstm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("调用DB类中doUpdate方法时出错！");
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getRs() {
		try {
			return pstm.getResultSet();
		} catch (SQLException e) {
			System.out.println("DB类中的getRs()方法出错！");
			e.printStackTrace();
			return null;
		}
	}
	
	//记录缺勤情况到缺勤表
	public void insertQXData(ArrayList<Absence> absenceList,String sql){      
		Timestamp time= new Timestamp(new Date().getTime());
		try {
			pstm = con.prepareStatement(sql);
			con.setAutoCommit(false);
			for(int i = 0; i < absenceList.size(); i++){
				pstm.setTimestamp(1,time);
				pstm.setString(2,absenceList.get(i).getStudentNo());
				pstm.setString(3,absenceList.get(i).getTeacherNo());
				pstm.setString(4,absenceList.get(i).getCourseNo() );
				pstm.setInt(5,absenceList.get(i).getFlag());
				pstm.setString(6,absenceList.get(i).getSp());
				pstm.setString(7,absenceList.get(i).getRemark());
				pstm.addBatch();
			}
			pstm.executeBatch();
	        con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//记录签到情况到签到表
	public void insertSIData(ArrayList<SignIn> signList,String sql){     
		Timestamp time= new Timestamp(new Date().getTime());
		try {
			pstm = con.prepareStatement(sql);
			con.setAutoCommit(false);
			for(int i = 0; i < signList.size(); i++){
				pstm.setString(1, signList.get(i).getStudentNo());
				pstm.setString(2, signList.get(i).getCourseNo());
				pstm.setString(3, signList.get(i).getTeacherNo());
				pstm.setTimestamp(4, time);
				pstm.setString(5, signList.get(i).getComfirm());
				pstm.addBatch();
			}
			pstm.executeBatch();
	        con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getUpdate() {
		try {
			return pstm.getUpdateCount();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void closed() {
		try {
			if (pstm != null)
				pstm.close();
		} catch (Exception e) {
			System.out.println("关闭pstm对象失败！");
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("关闭con对象失败！");
		}
	}
}
