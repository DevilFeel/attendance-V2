package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.SemesterInfo;
import hyit.app.model.SubjectInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddSubjecctServlet
 */
@WebServlet("/addsubject")
public class AddSubjecctServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubjecctServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		boolean flag = false;
		String subjectName = request.getParameter("subject");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		Integer teacherNumber = Integer.parseInt(request.getSession().getAttribute("teacherNumber").toString());
		List<SemesterInfo> list = new ArrayList<SemesterInfo>();
		Iterator<SemesterInfo> iter = null;
		SemesterInfo semesterInfo = new SemesterInfo();
		SubjectInfo subInfo = null;
		try {
			list = DAOFactory.getISemesterDAOInstance().getAll();
			iter = list.iterator();
			while(iter.hasNext()){	//找出学期编号
				semesterInfo = iter.next();
				if(semesterInfo.getYear().equals(year) && semesterInfo.getSemester().equals(semester)){
					break;
				}else{
					semesterInfo = null;
				}
			}
			if(null!=semesterInfo){
				subInfo = new SubjectInfo();
				subInfo.setName(subjectName);
				subInfo.setSemesterNumber(semesterInfo.getSemesterNumber());
				subInfo.setTeacherNumber(teacherNumber);
				flag = DAOFactory.getISubjectInfoDAOInstance().doCreate(subInfo);	//插入课程系信息
			}else{
				request.setAttribute("msg", "没有该学期信息，请联系管理员添加！");
				request.getRequestDispatcher("subject.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(flag){
			request.setAttribute("msg", "操作成功！");
		}else{
			request.setAttribute("msg", "操作失败，重复添加课程！");
		}
		if(!response.isCommitted()){
			request.getRequestDispatcher("subject.jsp").forward(request, response);
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
