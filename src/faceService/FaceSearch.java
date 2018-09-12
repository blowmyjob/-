package faceService;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import auth.AuthService;
import dao.DBConnection;
import tools.GsonUtils;
import tools.HttpUtil;



/**
* 人脸搜索
*/
public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
	
	
    public static String search(String face_token) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	
        	String classNoList = "";
        	
        	//连接数据库，获取班级编号作为百度AI的人脸库组别进行以下的人脸搜索
        	DBConnection db=new DBConnection();
        	db.getCon();
        	String sql = "select classNo from Class";
        	db.doPstm(sql);
        	ResultSet rs = db.getRs();
        	try {
				while (rs.next()) {//所有班级编号以逗号“,”分隔
					classNoList += rs.getString(1)+",";
				}				
				//去除最后一个逗号
				classNoList = classNoList.substring(0, classNoList.length()-1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	
            Map<String, Object> map = new HashMap<>();
            map.put("image", face_token);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", classNoList);
            map.put("image_type", "FACE_TOKEN");
            map.put("quality_control", "NONE");
            map.put("liveness_control", "NONE");
            map.put("max_user_num", "1");
            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            
            db.closed();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}