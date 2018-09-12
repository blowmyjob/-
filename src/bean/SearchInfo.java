package bean;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class SearchInfo {
	String face_token;
	String group_id;
	String user_id;
	String user_info;
	public String getFace_token() {
		return face_token;
	}
	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_info() {
		return user_info;
	}
	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	public SearchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public  SearchInfo showResult(String str){
		
		SearchInfo searchInfo = new SearchInfo();		
		JSONObject json = JSONObject.fromObject(str);
		String error_msg = json.get("error_msg").toString();
		
		if( error_msg.equals("SUCCESS") ) {//此人脸在百度AI人脸库已经注册了的才执行以下代码		
			
			JSONObject result=JSONObject.fromObject(json.get("result"));
	//		System.out.println("face_token---"+result.get("face_token"));
			searchInfo.setFace_token(result.get("face_token").toString());	
			
			JSONArray user_list = (JSONArray)result.get("user_list");
			
			if(user_list.size()>0) {
				for(int i=0;i<user_list.size();i++) {			
					JSONObject user = user_list.getJSONObject(i);
	//				System.out.println("group_id---"+user.get("group_id"));
	//				System.out.println("user_id---"+user.get("user_id"));
	//				System.out.println("user_info---"+user.get("user_info"));
					searchInfo.setGroup_id(user.get("group_id").toString());
					searchInfo.setUser_id(user.get("user_id").toString());
					searchInfo.setUser_info(user.get("user_info").toString());
					
				}
			}
		}
		return searchInfo;
	}
	
}
