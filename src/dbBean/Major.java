package dbBean;

import java.io.Serializable;

//专业类
public class Major implements Serializable{

	private static final long serialVersionUID = 1L;
	String majorNo;//专业号
	String majorName;//专业名称
	String instituteNo;//院系编号
	
	public Major() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Major(String majorNo, String majorName, String instituteNo) {
		super();
		this.majorNo = majorNo;
		this.majorName = majorName;
		this.instituteNo = instituteNo;
	}

	public String getMajorNo() {
		return majorNo;
	}

	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getInstituteNo() {
		return instituteNo;
	}

	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
	}
	
	
	
}
