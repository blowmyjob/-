package dbBean;

import java.io.Serializable;

//班级类
public class Class implements Serializable{

	private static final long serialVersionUID = 1L;
	String classNo;		//班级号
	String className;	//班级名称
	int grade;			//年级
	int numbers;		//人数
	String marjorNo;	//专业编号
	String mainTeacherNo;//班主任工号
	String monitorNo;	//班长学号
	
	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Class(String classNo, String className, int grade, int numbers, String marjorNo, String mainTeacherNo,
			String monitorNo) {
		super();
		this.classNo = classNo;
		this.className = className;
		this.grade = grade;
		this.numbers = numbers;
		this.marjorNo = marjorNo;
		this.mainTeacherNo = mainTeacherNo;
		this.monitorNo = monitorNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public String getMarjorNo() {
		return marjorNo;
	}

	public void setMarjorNo(String marjorNo) {
		this.marjorNo = marjorNo;
	}

	public String getMainTeacherNo() {
		return mainTeacherNo;
	}

	public void setMainTeacherNo(String mainTeacherNo) {
		this.mainTeacherNo = mainTeacherNo;
	}

	public String getMonitorNo() {
		return monitorNo;
	}

	public void setMonitorNo(String monitorNo) {
		this.monitorNo = monitorNo;
	}
	
	
}
