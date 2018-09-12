package dbBean;

import java.io.Serializable;


public class Absence implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String studentNo;	//学号 
	private String teacherNo;	//工号
	private String courseNo;	//课程编号
	private int flag;		//是否注销
	private String sp;			//经手人
	private String remark;		//备注
	
	public Absence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Absence( String studentNo, String teacherNo, String courseNo, int flag, String sp,
			String remark) {
		super();
		
		this.studentNo = studentNo;
		this.teacherNo = teacherNo;
		this.courseNo = courseNo;
		this.flag = flag;
		this.sp = sp;
		this.remark = remark;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
