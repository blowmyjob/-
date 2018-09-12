package bean;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FaceInfo {
	String face_token;
	int left;
	int top;
	int width;
	int height;
	public String getFace_token() {
		return face_token;
	}
	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
	public FaceInfo(String face_token, int left, int top, int width, int height) {
		super();
		this.face_token = face_token;
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}
	
	public FaceInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public  List<FaceInfo> showInfo(String str) {
		JSONObject json = JSONObject.fromObject(str);
        System.out.println("本次人脸检测结果如下：");
        Long r=(Long)json.get("log_id");
        System.out.println("log_id---"+r);
       
        JSONObject json1=JSONObject.fromObject(json.get("result"));
        System.out.println("face_num---"+json1.get("face_num"));
        
        //建立Javabean数组用以保存人脸信息
        List<FaceInfo> faceInfoList=new ArrayList<FaceInfo>();
        JSONArray r2=(JSONArray)json1.get("face_list");
        if(r2.size()>0){
        	for(int i=0;i<r2.size();i++){
        		System.out.println("");
        		FaceInfo faceInfo=new FaceInfo();
        		JSONObject job = r2.getJSONObject(i); 
        		System.out.println("**********第"+(i+1)+"张人脸信息**********");
        		System.out.println("face_token---"+job.get("face_token"));
        		faceInfo.setFace_token(job.get("face_token").toString());
        		JSONObject location=JSONObject.fromObject(job.get("location"));
        		System.out.println("left---"+location.get("left"));
       		    faceInfo.setLeft((int)Double.parseDouble(location.get("left").toString()));
        		System.out.println("top---"+location.get("top"));
        		faceInfo.setTop((int)Double.parseDouble(location.get("top").toString()));
        		System.out.println("width---"+location.get("width"));
        		faceInfo.setWidth((int)Double.parseDouble(location.get("width").toString()));
        		System.out.println("height---"+location.get("height"));
        		faceInfo.setHeight((int)Double.parseDouble(location.get("height").toString()));
        		faceInfoList.add(faceInfo);
        	}
        }
        
        return faceInfoList;
	}
}
