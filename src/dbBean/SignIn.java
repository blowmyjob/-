package dbBean;

import java.io.Serializable;

public class SignIn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String studentNo;//学号
	private String courseNo;//课程编号
	private String teacherNo;//教师工号
	private String comfirm;	 //是否确认
	
	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignIn(String studentNo, String courseNo, String teacherNo, String comfirm) {
		super();
		this.studentNo = studentNo;
		this.courseNo = courseNo;
		this.teacherNo = teacherNo;
		this.comfirm = comfirm;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getComfirm() {
		return comfirm;
	}

	public void setComfirm(String comfirm) {
		this.comfirm = comfirm;
	}
	
	
	
}
