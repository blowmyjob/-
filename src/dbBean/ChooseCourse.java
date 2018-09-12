package dbBean;

import java.io.Serializable;

//选课表
public class ChooseCourse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String studentNo;//学号
	String courserNo;//课程号
	
	public ChooseCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChooseCourse(String studentNo, String courserNo) {
		super();
		this.studentNo = studentNo;
		this.courserNo = courserNo;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getCourserNo() {
		return courserNo;
	}

	public void setCourserNo(String courserNo) {
		this.courserNo = courserNo;
	}
	
	
}
