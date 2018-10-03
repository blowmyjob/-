package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DBConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.args;


/**
 * Servlet implementation class sendMessage
 */
@WebServlet("/sendMessage")
public class sendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HashMap<String, Object>map=new HashMap<String, Object>();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        JSONArray json = new JSONArray();
		String sql1="select studentName,courseName,classtime,SignNo from Student as a,Course as b,SignIn as c where a.studentNo=c.studentNo and c.courseNo=b.courseNo and c.comfirm=false and a.studentNo="+args.username;
		DBConnection db1=new DBConnection();
		db1.getCon();
		db1.doPstm(sql1);
		ResultSet rs=db1.getRs();
		try {
			while(rs.next()){
				//map.put("msg"+count, tempmsg);
				JSONObject jo = new JSONObject();
				jo.put("studentName", rs.getString(1));
				jo.put("courseName", rs.getString(2));
				jo.put("classtime", rs.getString(3));
				jo.put("id", rs.getString(4));
				jo.put("flag", "考勤成功");
				json.add(jo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db1.closed();
		String sql2="select studentName,courseName,classtime,absenceNo from Student as a,Course as b,Absence as c where a.studentNo=c.studentNo and c.courseNo=b.courseNo and c.remark is null and a.studentNo="+args.username;
		DBConnection db2=new DBConnection();
		db2.getCon();
		db2.doPstm(sql2);
		ResultSet rs2=db2.getRs();
		try {
			while(rs2.next()){
				//map.put("msg"+count, tempmsg);
				JSONObject jo = new JSONObject();
				jo.put("studentName", rs2.getString(1));
				jo.put("courseName", rs2.getString(2));
				jo.put("classtime", rs2.getString(3));
				jo.put("id", rs2.getString(4));
				jo.put("flag", "缺勤");
				json.add(jo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db2.closed();
		String param = json.toString();
		System.out.println(param);
		PrintWriter out = response.getWriter();
        out.write(param);
        out.close();
        /*返回的json数据格式要 [student: ,courseName: ,classTime: ,SignNo: ,AbsenceNo:]*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
