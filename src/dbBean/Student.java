package dbBean;

import java.io.Serializable;

//学生类
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String studentNo;	//学号
	String studentName;	//姓名
	String pwd;			//密码
	String sex;			//性别
	String phone;		//电话
	String classNo;		//班级号
	String majorNo;		//专业编号
	String instituteNo;	//院系编号
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String studentNo, String studentName, String pwd, String sex, String phone, String classNo,
			String majorNo, String instituteNo) {
		super();
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.pwd = pwd;
		this.sex = sex;
		this.phone = phone;
		this.classNo = classNo;
		this.majorNo = majorNo;
		this.instituteNo = instituteNo;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getMajorNo() {
		return majorNo;
	}
	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}
	public String getInstituteNo() {
		return instituteNo;
	}
	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
	}
	
	
}
