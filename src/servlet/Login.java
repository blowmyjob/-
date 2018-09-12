package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;
import tools.GsonUtils;
import tools.args;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("username");
		String pwd=request.getParameter("password");
		String flag1="flase";
		String flag2="true";
		Map<String, Object> map1 = new HashMap<>();
		

		DBConnection db=new DBConnection();
		db.getCon();
		if (userName.length() == 12) {
			String sql = "select pwd,imageFlag from Student where studentNo=" + userName;
			db.doPstm(sql);
			ResultSet rs = db.getRs();
			try {
				while (rs.next()) {
					String temppwd = rs.getString(1);
					System.out.println(temppwd);
					if (temppwd.equals(pwd)) {
						flag1 = "Student";
						args.username = userName;
						if (rs.getString(2).equals("false")) {
							flag2="false";
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userName.length() == 7) {
			String sql = "select pwd from Teacher where teacherNo=" + "'"+userName+"'";
			db.doPstm(sql);
			ResultSet rs = db.getRs();
			try {
				while (rs.next()) {
					String temppwd = rs.getString(1);
					System.out.println(temppwd);
					if (temppwd.equals(pwd)) {
						flag1 = "Teacher";
						args.username = userName;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map1.put("image1",flag1);
		map1.put("image2", flag2);
        String param = GsonUtils.toJson(map1);
        PrintWriter out = response.getWriter();
		out.write(param);
        out.close();
        db.closed();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
