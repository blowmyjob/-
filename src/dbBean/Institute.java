package dbBean;

import java.io.Serializable;

//院系类
public class Institute implements Serializable{

	private static final long serialVersionUID = 1L;
	String instituteNo;		//院系编号
	String instituteName;	//院系名称
	
	
	public Institute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Institute(String instituteNo, String instituteName) {
		super();
		this.instituteNo = instituteNo;
		this.instituteName = instituteName;
	}

	public String getInstituteNo() {
		return instituteNo;
	}

	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	
}
