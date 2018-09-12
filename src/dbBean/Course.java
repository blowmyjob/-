package dbBean;

import java.io.Serializable;

//课程类
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String courseNo;	//课程号
	String courseName;	//课程名
	String courseXZ;	//课程性质
	String teacherNo;	//任教老师工号

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String courseNo, String courseName, String courseXZ, String teacherNo) {
		super();
		this.courseNo = courseNo;
		this.courseName = courseName;
		this.courseXZ = courseXZ;
		this.teacherNo = teacherNo;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseXZ() {
		return courseXZ;
	}

	public void setCourseXZ(String courseXZ) {
		this.courseXZ = courseXZ;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	
	
}
