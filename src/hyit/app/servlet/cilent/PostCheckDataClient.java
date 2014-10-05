package hyit.app.servlet.cilent;

import hyit.app.factory.DAOFactory;
import hyit.app.model.CheckInfo;
import hyit.app.model.CronInfo;
import hyit.app.model.StudentInfo;
import hyit.app.model.TeacherInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostCheckDataClient
 */
@WebServlet("/postCheckData")
public class PostCheckDataClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostCheckDataClient() {
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
		Integer teacherNumber = Integer.parseInt(request
				.getParameter("teacherNumber"));
		Integer cronNumber = Integer.parseInt(request
				.getParameter("cronNumber"));
		Time executeTime = Time.valueOf(request.getParameter("executeTime"));
		String[] studentNumberArray = request
				.getParameterValues("studentNumber");
		String[] cardMac = request.getParameterValues("cardMac");
		String[] absent = request.getParameterValues("absent");
		String[] date = request.getParameterValues("date");
		String[] time = request.getParameterValues("time");
		StudentInfo studentInfo = null;
		TeacherInfo teacherInfo = null;
		CronInfo cronInfo = null;
		CheckInfo checkInfo = null;
		boolean flag = true;
		try {
			teacherInfo = DAOFactory.getITeacherInfoDAOInstance().getByID(
					teacherNumber);
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("’À∫≈—È÷§ ß∞‹£°");
		}
		if (null != teacherInfo) {
			for (int i = 0; i < studentNumberArray.length; i++) {
				// ø®∫≈∏¸–¬
				studentInfo = new StudentInfo();
				try {
					studentInfo = DAOFactory.getIStudentInfoDAOInstance()
							.getByID(Long.parseLong(studentNumberArray[i]));
					if (!cardMac[i].equals(studentInfo.getCardMac())) {
						studentInfo.setCardMac(cardMac[i]);
						DAOFactory.getIStudentInfoDAOInstance().update(
								studentInfo);
					}
				} catch (Exception e) {
					// TODO: handle exception
					flag = false;
					System.out.println("∏¸–¬ø®∫≈ ß∞‹");
				}
				// º«¬ºøº«⁄
				checkInfo = new CheckInfo();
				checkInfo.setCronNumber(cronNumber);
				checkInfo.setStudentNumber(Long
						.parseLong(studentNumberArray[i]));
				checkInfo.setAbsent(absent[i]);
				checkInfo.setDate(Date.valueOf(date[i]));
				checkInfo.setTime(Time.valueOf(time[i]));
				try {
					DAOFactory.getICheckInfoDAOInstance().doCreate(checkInfo);
				} catch (Exception e) {
					// TODO: handle exception
					flag = false;
					System.out.println("º«¬ºøº«⁄ ß∞‹£°");
				}
			}
			// ∏¸–¬øº«⁄»ŒŒÒ◊¥Ã¨
			try {
				cronInfo = DAOFactory.getICronInfoDAOInstance().getByID(
						cronNumber);
				cronInfo.setExecuteTime(executeTime);
				cronInfo.setStatus(1);
				flag = DAOFactory.getICronInfoDAOInstance().update(cronInfo);
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("∏¸–¬øº«⁄»ŒŒÒ◊¥Ã¨ ß∞‹£°");
				e.printStackTrace();
			}
		} else {
			flag = false;
		}
		if (flag) {
			PrintWriter pw = response.getWriter();
			pw.print("succeed");
			pw.close();
		}
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
