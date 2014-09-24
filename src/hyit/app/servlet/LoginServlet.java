package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.TeacherInfo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		TeacherInfo info = new TeacherInfo();
		info.setAccount(username);
		info.setPassword(password);
		TeacherInfo teacher = null;
		RequestDispatcher rd = null;
		String path = null;
		try {
			teacher = DAOFactory.getITeacherInfoDAOInstance().check(info);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (teacher == null) {
			path = "login.html";
		} else {
			if(teacher.getRank()==0){
				path = "admin.jsp";
			}else{
				path = "index.jsp";
			}
			request.getSession().setAttribute("username", teacher.getName());
			request.getSession().setAttribute("teacherNumber", teacher.getTeacherNumber());
			request.getSession().setAttribute("rank", teacher.getRank());
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
