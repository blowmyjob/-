package faceService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import bean.FaceInfo;

/*
@param lastDir
*            后缀
* @param srcPath
*            图片地址
* @param faceInfoList
* 			 图片里所有人脸的信息
* @param subPath
*            保存图片地址
*/
public class FaceDusk {
   
	//实现人脸摸黑，调用此函数一次，可把faceInfoList.size()张人脸全给摸黑
    public static void duskPicture(String lastDir, String srcPath, List<FaceInfo> faceInfoList, String subPath) throws Exception {
        
        File file = new File(srcPath);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(int f=0 ; f<faceInfoList.size() ; f++) {
        	
        	int width = faceInfoList.get(f).getWidth();
        	int height = faceInfoList.get(f).getHeight();
        	int x = faceInfoList.get(f).getLeft();
        	int y = faceInfoList.get(f).getTop();
        	
	        for (int i = x; i < x+width; i++) {
	            for (int j = y; j < y+height; j++) {
	                
	               bi.setRGB(i, j, 0);
	            }
	        }
	    }
        ImageIO.write(bi, lastDir, new File(subPath));//生成新的图片
    }
    
}