package main;



import java.util.HashMap;

import java.util.Map;

import auth.AuthService;

import tools.GsonUtils;
import tools.HttpUtil;
import tools.Read;


public class Test {

	 public static String detect(String path) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", Read.getImageBinary(path));
	            map.put("face_field", "faceshape,facetype");
	            map.put("image_type", "BASE64");
	            map.put("max_face_num", "10");
	            String param = GsonUtils.toJson(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = AuthService.getAuth();

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            System.out.println(result);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		detect("D:/faceDusk/2018001-2018_07_26-23_05_32_426.jpg");
		
		
	}

}
