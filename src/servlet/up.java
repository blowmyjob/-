package servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.InputAbsence;
import dao.InputSignIn;
import faceManage.FaceAdd;
import main.Main;
import tools.args;
 
/**
 * Servlet implementation class wechat
 */
@WebServlet("/up")
public class up extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String uploadFilePath = "E:\\upload";
	File tempFile;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public up() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("图片存放");
	    request.setCharacterEncoding("UTF-8");    
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    if (isMultipart)
	    {
    	  String dir1 = "D:\\upload\\Student";
		  String dir2 = "D:\\upload\\Teacher";  
	      File dirFile1 = new File(dir1);
	      File dirFile2 = new File(dir2);
	      if (!dirFile1.exists()) {
	        dirFile1.mkdirs();
	      }
	      if (!dirFile2.exists()) {
				dirFile2.mkdirs();
			}
	      try
	      {
		        FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload fileUpload = new ServletFileUpload(factory);
		        List<FileItem> items = fileUpload.parseRequest(request);
		        for (FileItem item : items) {
			          if (item.isFormField()) {
			            String name = item.getFieldName();
			            System.out.println(name+" "+URLDecoder.decode(item.getString(), "utf-8"));  
			          }else{	
			            
			        	  if (args.username.length() == 12) { //登陆的是学生的账号，图片命名用“学号”.jpg
								
								String fileName = args.username + ".jpg";
								String filePath = dir1 + "\\" + fileName ;
								System.out.println("filePath=" + filePath);
								File saveFile = new File(dirFile1, fileName);
								item.write(saveFile);
								
								//在百度AI的人脸库中进行注册
								FaceAdd.add(filePath, fileName);
								
							}else {  //登陆的是老师的账号，图片命名用“工号+时间”.jpg
								
								//精确到毫秒的时间格式
				                SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss_SSS");
				      		    String date = df.format(new Date());
				      		    System.out.println(date);
				      		  
				      		    //图片命名：登录账号+时间(精确到毫秒)
				                String fileName = args.username + "-" + date + ".jpg";
				                String filePath = dir2 + "\\" + fileName ;
				                System.out.println("dir2="+ dir2);
				                System.out.println("filePath=" + filePath);
				                File saveFile = new File(dirFile2, fileName);
				                item.write(saveFile); 
				              
				                //对源图片路径中的“\\”全部转换成“/”
				                filePath = filePath.replaceAll("\\\\", "/");
				                Main main = new Main();
				                main.work(filePath, fileName);
				                main.showResult();
				                
				                
				                //写入数据库操作
				                //写入数据库操作
				                ArrayList<String> userIdList = new ArrayList<String>();
				                for(int i=0 ; i<args.searchInfoList.size() ; i++)
				                {
				                	userIdList.add(args.searchInfoList.get(i).getUser_id());
				                }			           
				                InputAbsence inputAbsence = new InputAbsence();
				                InputSignIn inputSignIn = new InputSignIn();
				                ArrayList<String> absenceStudent = new ArrayList<String>();
				                ArrayList<String> signStudent = new ArrayList<String>();			                
				                absenceStudent = inputAbsence.getAbsence(userIdList, "2016414122");
				                inputAbsence.toDataBase(absenceStudent);				                
				                signStudent = inputSignIn.getSign(userIdList, "2016414122");
				                inputSignIn.toDataBase(signStudent);    
							}
			          }
		        }
	        
	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	      }
	    }
	    
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
 
}