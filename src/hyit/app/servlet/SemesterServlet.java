package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.SemesterInfo;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SemesterServlet
 */
@WebServlet("/semester")
public class SemesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SemesterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String startDay = request.getParameter("startDay");
		java.util.Date curDate = new java.util.Date(startDay);
		Date date = new Date(curDate.getTime());
		Integer dayOfWeek = curDate.getDay();
		SemesterInfo info = new SemesterInfo();
		boolean flag = true;
		info.setYear(year);
		info.setSemester(semester);
		info.setDate(date);
		info.setDayOfWeek(dayOfWeek);
		try {
			DAOFactory.getISemesterDAOInstance().doCreate(info);
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("创建学期信息失败！");
		}
		if(flag){
			request.setAttribute("msg", "操作成功！");
		}else{
			request.setAttribute("msg", "操作失败！");
		}
		RequestDispatcher rd = request.getRequestDispatcher("semester.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
