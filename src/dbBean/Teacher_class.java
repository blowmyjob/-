package dbBean;

import java.io.Serializable;

//任课表
public class Teacher_class implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String teacherNo;//教师工号
	String classNo;	//班级编号
	
	public Teacher_class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher_class(String teacherNo, String classNo) {
		super();
		this.teacherNo = teacherNo;
		this.classNo = classNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	
	
	
}
