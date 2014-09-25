package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.CronInfo;
import hyit.app.model.LessonInfo;
import hyit.app.model.SemesterInfo;
import hyit.app.model.SessionInfo;
import hyit.app.model.SubjectInfo;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCronServlet
 */
@WebServlet("/createCron")
public class CreateCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCronServlet() {
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
		boolean flag = true; // 设置操作标识
		Integer sessionNumber = Integer.parseInt(request.getParameter("class")); // 获取课程编号
		String[] week = request.getParameterValues("week");
		String[] evenOld = request.getParameterValues("evenAndOld");
		String[] room = request.getParameterValues("classroom");
		String[] start = request.getParameterValues("start");
		String[] end = request.getParameterValues("end");
		Integer subjectNumber = Integer.parseInt(request
				.getParameter("subject"));
		SubjectInfo subjectInfo = null;
		LessonInfo lessonInfo = null;
		SessionInfo sessionInfo = null;
		CronInfo cronInfo = null;
		SemesterInfo semesterInfo = null;
		// 创建课时信息
		try {
			subjectInfo = DAOFactory.getISubjectInfoDAOInstance().getByID(
					subjectNumber);
			semesterInfo = DAOFactory.getISemesterDAOInstance().getByID(
					subjectInfo.getSemesterNumber()); // 获取学期信息
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
		}
		for (int i = 0; i < room.length; i++) {
			lessonInfo = new LessonInfo();
			lessonInfo.setSessionNumber(sessionNumber);
			lessonInfo.setDayOfWeek(Integer.parseInt(week[i]));
			lessonInfo.setEvenOld(Integer.parseInt(evenOld[i]));
			lessonInfo.setStartLesson(Integer.parseInt(start[i]));
			lessonInfo.setEndLesson(Integer.parseInt(end[i]));
			lessonInfo.setClassroom(room[i]);
			try {
				DAOFactory.getILessonInfoDAOInstance().doCreate(lessonInfo); // 创建课时
				lessonInfo = DAOFactory.getILessonInfoDAOInstance().getByOther(
						lessonInfo.getSessionNumber(),
						lessonInfo.getDayOfWeek(), lessonInfo.getStartLesson()); // 返回课时的编号
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("创建课时信息失败！");
			}
			try {
				sessionInfo = DAOFactory.getISessionInfoDAOInstance().getByID(
						sessionNumber);
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("取得课程信息失败！");
			}
			// 循环添加学期内课时的考勤任务
			Date date = new Date(semesterInfo.getDate().getTime()); // 获取开学日期
			Integer startWeekSemester = semesterInfo.getDayOfWeek();
			Integer startWeekLesson = lessonInfo.getDayOfWeek();
			if (startWeekLesson < startWeekSemester) { // 计算开学后该课时第一节课上课日期
				date = new Date(date.getTime() + 86400000
						* (startWeekLesson - startWeekSemester + 7));
			} else {
				date = new Date(date.getTime() + 86400000
						* (startWeekLesson - startWeekSemester));
			}
			for (int j = sessionInfo.getStartWeek(); j <= sessionInfo
					.getEndWeek(); j++) {
				cronInfo = new CronInfo();
				cronInfo.setLessonNumber(lessonInfo.getLessonNumber());
				cronInfo.setExecuteDate(date);
				cronInfo.setOrderTime(new Timestamp(System.currentTimeMillis())); // 创建任务
				date = new Date(date.getTime() + 86400000 * 7);
				try {
					DAOFactory.getICronInfoDAOInstance().doCreate(cronInfo);
				} catch (Exception e) {
					// TODO: handle exception
					flag = false;
					System.out.println("创建考勤任务失败！");
				}
			}
		}
		if (flag) {
			request.setAttribute("msg", "操作成功！");
		} else {
			request.setAttribute("msg", "操作失败！");
		}
		request.getRequestDispatcher("cron.jsp").forward(request, response);
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
