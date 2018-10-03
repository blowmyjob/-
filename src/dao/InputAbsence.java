package dao;
import java.sql.*;
import java.util.*;

import dbBean.Absence;

public class InputAbsence {
	
	//根据人脸检测返回的所有已到学生学号userIdList，
	//去获取班级编号为groupId的某班缺勤学生学号absenceStudent，并作为参数返回
	public ArrayList<String> getAbsence(ArrayList<String> userIdList,String groupId) throws SQLException{
		ArrayList<String> absenceStudent=new ArrayList<String>();
		DBConnection db=new DBConnection();
		db.getCon();
		String sql="select studentNo from Student where classNo="+groupId;
		db.doPstm(sql);
		ResultSet rs=db.getRs();
		while(rs.next()){
			String tempId=rs.getString(1);
			if(!userIdList.contains(tempId)){
				absenceStudent.add(tempId);
			}
		}
		db.closed();
		return absenceStudent;
	}
	
	//根据缺勤的学生学号absenceStudent，将其写进云数据库的缺勤表Absence
	public void toDataBase(ArrayList<String> absenceStudent){
		ArrayList<Absence> absenceList = new ArrayList<Absence>();
		int flag=1;
		String sql2="insert into Absence(classtime,studentNo,teacherNo,courseNo,flag,sp) values(?,?,?,?,?,?)";
		for(String item: absenceStudent){
			String sql ="select teacherNo,courseNo,monitorNo from ChooseCourse as a,Student as b,Class as c where a.studentNo=b.studentNo and b.classNo=c.classNo and a.studentNo="+item;
			DBConnection db=new DBConnection();
			db.getCon();
			db.doPstm(sql);
			ResultSet rs=db.getRs();
			try {
				while(rs.next()){
					Absence temp=new Absence();
					temp.setStudentNo(item);
					temp.setTeacherNo(rs.getString(1));
					temp.setCourseNo(rs.getString(2));
					temp.setFlag(flag);
					temp.setSp(rs.getString(3));
					absenceList.add(temp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.closed();
		}
		DBConnection db2=new DBConnection();
		db2.getCon();
		db2.insertQXData(absenceList, sql2);
		db2.closed();
	}
	
}
