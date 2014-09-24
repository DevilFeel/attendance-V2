package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.ClassInfo;
import hyit.app.model.SelectionInfo;
import hyit.app.model.SessionInfo;
import hyit.app.model.StudentInfo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddClassServlet
 */
@WebServlet("/addClass")
public class AddClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClassServlet() {
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
		Integer subjectNumber = Integer.parseInt(request
				.getParameter("subject"));
		Integer classNumber = Integer.parseInt(request.getParameter("class"));
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer end = Integer.parseInt(request.getParameter("end"));
		boolean flag = true; // ���ò�����ʶ
		ClassInfo classInfo = null;
		StudentInfo stuInfo = null;
		SelectionInfo selInfo = null;
		List<StudentInfo> list = null;
		Iterator<StudentInfo> iter = null;
		SessionInfo sessionInfo = null;
		try {
			classInfo = DAOFactory.getIClassInfoDAOInstance().getByID(
					classNumber); // ��ȡ�༶��Ϣ
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("��ȡ�༶��Ϣʧ�ܣ�");
		}
		try {
			list = DAOFactory.getIStudentInfoDAOInstance().getByClassNumber(
					classNumber); // ��ȡȫ��ѧ����Ϣ
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("��ȡѧ����Ϣʧ�ܣ�");
		}
		sessionInfo = new SessionInfo();
		sessionInfo.setClassName(classInfo.getName());
		sessionInfo.setSubjectNumber(subjectNumber);
		sessionInfo.setStartWeek(start);
		sessionInfo.setEndWeek(end);
		try {
			DAOFactory.getISessionInfoDAOInstance().doCreate(sessionInfo);
			sessionInfo = DAOFactory.getISessionInfoDAOInstance().getByName(
					sessionInfo.getSubjectNumber(), sessionInfo.getClassName());
		} catch (Exception e) {
			// TODO: handle exception
			flag = false;
			System.out.println("�����γ���Ϣʧ�ܣ�");
		}
		iter = list.iterator();
		while (iter.hasNext()) {
			stuInfo = iter.next();
			selInfo = new SelectionInfo();
			selInfo.setSessionNumber(sessionInfo.getSessionNumber());
			selInfo.setStudentNumber(stuInfo.getStudentNumber());
			try {
				DAOFactory.getISelectionInfoDAOInstance().doCreate(selInfo);
			} catch (Exception e) {
				// TODO: handle exception
				flag = false;
				System.out.println("�����γ���ѧ������ʧ�ܣ�");
			}
		}
		if (flag) {
			request.setAttribute("msg", "�����ɹ���");
		} else {
			request.setAttribute("msg", "����ʧ�ܣ�");
		}
		request.getRequestDispatcher("class.jsp").forward(request, response);

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
