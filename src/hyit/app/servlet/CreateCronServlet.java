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
		boolean flag = true; // ���ò�����ʶ
		Integer sessionNumber = Integer.parseInt(request.getParameter("class")); // ��ȡ�γ̱��
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
		// ������ʱ��Ϣ
		try {
			subjectInfo = DAOFactory.getISubjectInfoDAOInstance().getByID(
					subjectNumber);
			semesterInfo = DAOFactory.getISemesterDAOInstance().getByID(
					subjectInfo.getSemesterNumber()); // ��ȡѧ����Ϣ
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
				DAOFactory.getILessonInfoDAOInstance().doCreate(lessonInfo); // ������ʱ
				lessonInfo = DAOFactory.getILessonInfoDAOInstance().getByOther(
						lessonInfo.getSessionNumber(),
						lessonInfo.getDayOfWeek(), lessonInfo.getStartLesson()); // ���ؿ�ʱ�ı��
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("������ʱ��Ϣʧ�ܣ�");
			}
			try {
				sessionInfo = DAOFactory.getISessionInfoDAOInstance().getByID(
						sessionNumber);
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("ȡ�ÿγ���Ϣʧ�ܣ�");
			}
			// ѭ�����ѧ���ڿ�ʱ�Ŀ�������
			Date date = new Date(semesterInfo.getDate().getTime()); // ��ȡ��ѧ����
			Integer startWeekSemester = semesterInfo.getDayOfWeek();
			Integer startWeekLesson = lessonInfo.getDayOfWeek();
			if (startWeekLesson < startWeekSemester) { // ���㿪ѧ��ÿ�ʱ��һ�ڿ��Ͽ�����
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
				cronInfo.setOrderTime(new Timestamp(System.currentTimeMillis())); // ��������
				date = new Date(date.getTime() + 86400000 * 7);
				try {
					DAOFactory.getICronInfoDAOInstance().doCreate(cronInfo);
				} catch (Exception e) {
					// TODO: handle exception
					flag = false;
					System.out.println("������������ʧ�ܣ�");
				}
			}
		}
		if (flag) {
			request.setAttribute("msg", "�����ɹ���");
		} else {
			request.setAttribute("msg", "����ʧ�ܣ�");
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
