package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import bean.FaceInfo;
import bean.SearchInfo;
import faceService.FaceCut;
import faceService.FaceDetect;
import faceService.FaceDusk;
import faceService.FaceSearch;
import net.sf.json.JSONObject;
import tools.args;

public class Main {

	public void work(String filePath ,String fileName) {
		
		//源图片路径，可相对路径，也可绝对路径，往后可用URL代替
		String srcPath=filePath;
		
		//切割出来的人脸存放的目标路径，此处根据源图片中有多少个人脸确定有多少个路径，往后可用URL代替
		ArrayList<String> subPath = new ArrayList<String>();
		
		//摸黑的人脸存放的目标路径
		String duskPath ;
		
		//建立人脸信息数组，保存图片中的所有人脸信息
		List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
		
		
/**----------------------------先对源图片srcPath进行第一次的检测、切割、摸黑等一系列处理---------------------------*/
		
		//人脸检测，获取人脸信息
		String str=FaceDetect.detect(srcPath);
		FaceInfo faceInfo=new FaceInfo();
        faceInfoList=faceInfo.showInfo(str);
        
        
        
        //确定每张人脸切割的存放路径以及文件名
        String dir = "D:\\faceCut";    
	    File dirFile = new File(dir);
	    if (!dirFile.exists()) {//若此文件夹不存在，则创建
	    	dirFile.mkdirs();
	    }
        for(int i=0 ; i<faceInfoList.size(); i++) {
        	//使用faceToken.jpg对单个人脸命名
        	subPath.add( dir + "\\" + faceInfoList.get(i).getFace_token() + ".jpg");
        }
        
        
        
        //确定摸黑后人脸的存放路径以及文件名
        String dir2 = "D:\\faceDusk";    
	    File dirFile2 = new File(dir2);
	    if (!dirFile2.exists()) {//若此文件夹不存在，则创建
	    	dirFile2.mkdirs();
	    }
	    //摸黑后的文件名与源文件名相同，此有利于循环摸黑
	    duskPath = dir2 + "\\" + fileName;
        
	    
	    
        int left,top,width,height;
        
        //人脸切割
        for(int i=0;i<faceInfoList.size();i++) {
        	left=faceInfoList.get(i).getLeft();
        	top=faceInfoList.get(i).getTop();
        	width=faceInfoList.get(i).getWidth();
        	height=faceInfoList.get(i).getHeight();
        	
	        try {
				FaceCut.cutPicture("jpg", srcPath, left, top, width, height, subPath.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        //人脸摸黑      
        try {
			FaceDusk.duskPicture("jpg", srcPath, faceInfoList, duskPath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
/**------------------------------对源图片的第一次处理结束----------------------------------- */		

        
/**----------------------------接着对摸黑图片duskPath进行循环的检测、切割、摸黑等一系列处理---------------------------*/
        
/**说明：经调试，此段代码是没有问题的，之所以把它注释起来，是因为有些图片进行人脸摸黑后还能检测出人脸（此步骤是百度AI的问题），
 * 所以会导致程序运行错误，而目前阶段测试所用到的图片里人脸数都不超过10，故不需要循环处理都可正常运转，因此就先把此段代码注释，
 * 等正式使用时再把注释去掉
 */
        
        //对摸黑后的图片进行循环的检测，看摸黑后的图片是否还有人脸尚未检测
//		String result = FaceDetect.detect(duskPath);		
//		
//		JSONObject json = JSONObject.fromObject(result);
//		String error_msg = json.get("error_msg").toString();
//		
//		
//		int index = 10;//记录切割路径subPath的参数
//		
//		while( error_msg.equals("SUCCESS")) {//若还有人脸尚未检测，则循环执行此代码
//		
//			//建立临时人脸信息对象数组，保存此次循环所得到的人脸信息
//			List<FaceInfo> tempList = new ArrayList<FaceInfo>();
//			
//			//把再次获取到的人脸信息添加到faceInfoList
//	        faceInfoList.addAll(tempList);
//	        
//	        
//			//人脸检测，获取人脸信息
//			str=FaceDetect.detect(srcPath);			
//	        tempList=faceInfo.showInfo(str);
//	        
//	        
//	        
//	        //确定每张人脸切割的存放路径以及文件名
//	        for(int i=0 ; i<tempList.size(); i++) {		        	
//	        	//使用faceToken.jpg对单个人脸命名
//	        	subPath.add( dir + "\\" + tempList.get(i).getFace_token() + ".jpg");
//	        }
//	        
//	        
//	        
//	        //再次摸黑后人脸的存放路径以及文件名跟第一次执行时一样，
//	        //即覆盖第一张摸黑后的图片
//
//	        
//	        
//	        //人脸切割
//	        for(int i=0;i<tempList.size();i++) {
//	        	left=tempList.get(i).getLeft();
//	        	top=tempList.get(i).getTop();
//	        	width=tempList.get(i).getWidth();
//	        	height=tempList.get(i).getHeight();
//	        	
//		        try {
//					FaceCut.cutPicture("jpg", srcPath, left, top, width, height, subPath.get( index++ ));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	        }
//	        
//	        //人脸摸黑      
//	        try {
//				FaceDusk.duskPicture("jpg", duskPath, tempList, duskPath);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//	        
//	        
//	        
//	        //再次把摸黑图片送去检测，获取error_msg，作为下次循环的条件
//	        result = FaceDetect.detect(duskPath);
//			json = JSONObject.fromObject(result);
//			error_msg = json.get("error_msg").toString();
//		}

/**------------------------------对摸黑图片处理完毕-------------------------------*/		
		
        //人脸搜索
        List<SearchInfo> searchInfoList = new ArrayList<SearchInfo>();
        for(int i=0;i<faceInfoList.size();i++) {
        	String string = FaceSearch.search(faceInfoList.get(i).getFace_token());
        	SearchInfo searchInfo = new SearchInfo();       	
        	searchInfoList.add(searchInfo.showResult(string));
        }     
        args.searchInfoList.addAll(searchInfoList);
	}
	
	public void showResult() {
		
		System.out.println("搜索结果如下：");
        for(int i=0;i<args.searchInfoList.size();i++) {
        	System.out.println("");
        	System.out.println("**********第"+(i+1)+"张人脸对应的人员信息**********");
        	System.out.println("face_token---"+args.searchInfoList.get(i).getFace_token());
        	System.out.println("group_id---"+args.searchInfoList.get(i).getGroup_id());
        	System.out.println("user_id---"+args.searchInfoList.get(i).getUser_id());
        	System.out.println("user_info---"+args.searchInfoList.get(i).getUser_info());
        }
	}
}
