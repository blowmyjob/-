package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbBean.SignIn;

public class InputSignIn {
		
	//根据人脸检测返回的所有已到学生学号userIdList，
	//去获取班级编号为groupId的某班已到学生学号signStudent，并作为参数返回
	public ArrayList<String> getSign(ArrayList<String> userIdList,String groupId) throws SQLException{
		ArrayList<String> signStudent=new ArrayList<String>();
		DBConnection db=new DBConnection();
		db.getCon();
		String sql="select studentNo from Student where classNo="+groupId;
		db.doPstm(sql);
		ResultSet rs=db.getRs();
		while(rs.next()){
			String tempId=rs.getString(1);
			if(userIdList.contains(tempId)){
				signStudent.add(tempId);
			}
		}
		
		db.closed();
		return signStudent;
	}
	
	
	//根据已到的学生学号signStudent，将其写进云数据库的签到表SignIn
	public void toDataBase(ArrayList<String> signStudent){
		ArrayList<SignIn> signList = new ArrayList<SignIn>();
		String comfirm="false";
		for(String item: signStudent){
			String sql ="select teacherNo,courseNo from ChooseCourse as a,Student as b where a.studentNo=b.studentNo  and a.studentNo="+item;
			String sql2="insert into SignIn(studentNo,courseNo,teacherNo,classtime,comfirm) values(?,?,?,?,?)";
			DBConnection db=new DBConnection();
			db.getCon();
			db.doPstm(sql);
			ResultSet rs=db.getRs();
			try {
				while(rs.next()){
					SignIn temp=new SignIn();
					temp.setStudentNo(item);
					temp.setTeacherNo(rs.getString(1));
					temp.setCourseNo(rs.getString(2));
					temp.setComfirm(comfirm);
					signList.add(temp);
				}
				db.insertSIData(signList, sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closed();
		}
	}
	
}
