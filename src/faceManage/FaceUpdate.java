package faceManage;

import java.util.*;

import auth.AuthService;
import tools.GsonUtils;
import tools.HttpUtil;
import tools.Read;

/**
* 人脸更新
*/
public class FaceUpdate {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String update(String filePath ,String fileName) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
        
        String srcPath = filePath;
        String group_id = fileName.substring(0, 10);//根据文件名获取班级编号
        System.out.println("group_id=" + group_id);       
        String user_id = fileName.substring(0, 12);//根据文件名获取学生学号
        System.out.println("user_id=" + user_id);
        String userName = "";
        
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",  Read.getImageBinary(srcPath));
            map.put("group_id", group_id);
            map.put("user_id", user_id);
            map.put("user_info", "userName:" + userName);
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

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

}