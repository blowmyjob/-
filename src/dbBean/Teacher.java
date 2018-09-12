package dbBean;

import java.io.Serializable;

//教师类
public class Teacher implements Serializable{

	private static final long serialVersionUID = 1L;
	String teacherNo;	//工号
	String teacherName;	//姓名
	String pwd;			//密码
	String title;		//职称
	String instituteNo;	//院系编号
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(String teacherNo, String teacherName, String pwd, String title, String instituteNo) {
		super();
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.pwd = pwd;
		this.title = title;
		this.instituteNo = instituteNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstituteNo() {
		return instituteNo;
	}

	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
	}
	
	
}
