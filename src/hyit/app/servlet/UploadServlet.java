package hyit.app.servlet;

import hyit.app.factory.DAOFactory;
import hyit.app.model.ClassInfo;
import hyit.app.model.DepartmentInfo;
import hyit.app.model.StudentInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.jspsmart.upload.SmartUpload;

public class UploadServlet extends HttpServlet {
	private ServletConfig config;

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
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
		String year = null;
		String name = null;
		boolean flag = true; // ���ñ�ʶ
		String p = request.getSession().getServletContext().getRealPath("/"); // ��ȡ����·��
		String path = null;
		SmartUpload mySmartUpload = new SmartUpload();
		mySmartUpload.setMaxFileSize(1024 * 1024 * 2); // ����ÿ���ļ���С����Ϊ2M
		try {
			mySmartUpload.initialize(config, request, response);
			mySmartUpload.upload();
			com.jspsmart.upload.File file = mySmartUpload.getFiles().getFile(0);
			year = mySmartUpload.getRequest().getParameter("year"); // ��ȡ��ѧ���
			name = "/" + year + "ѧ������.xls";
			file.saveAs(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("�ļ��ϴ�ʧ�ܣ�");
			flag = false;
		}
		// ����xls����
		path = p + name;
		//System.out.println(path);
		Map<String, String> map = null;
		List<Map<String, String>> all = new ArrayList<Map<String, String>>();
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(new File(path));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��xlsʧ��");
			flag = false;
		}
		Sheet sheet = rwb.getSheet(0); // ��ȡ��һ�ű�
		int row = sheet.getRows(); // ����������
		int cloumn = sheet.getColumns(); // ����������
		String[] key = new String[cloumn]; // ����ȡ��xls�ڵ�һ�е��ֶΣ���������ƥ��
		for (int i = 0; i < cloumn; i++) {
			Cell cell = sheet.getCell(i, 0);
			key[i] = cell.getContents();
		}
		for (int j = 1; j < row; j++) {
			map = new HashMap<String, String>(); // ÿ�м�¼������Map�У������ֶ���ȡ
			for (int i = 0; i < cloumn; i++) {
				Cell cell = sheet.getCell(i, j);
				map.put(key[i], cell.getContents());
			}
			all.add(map);
		}
		Iterator<Map<String, String>> iter = all.iterator();
		while (iter.hasNext()) {
			StudentInfo studentInfo = null; // ��ʼ������
			DepartmentInfo deInfo = null; // ��ʼ������
			ClassInfo classInfo = null; // ��ʼ������
			map = iter.next();
			// ��info����Ҫ���ݿ���
			/*
			 * �������²���������department ʹ��deID����class�� ʹ��classID��deID����student
			 */
			deInfo = new DepartmentInfo();
			deInfo.setName((String) map.get("ѧԺ"));
			try {
				DAOFactory.getIDepartmentDAOInstance().doCreate(deInfo); // ����ѧԺ��Ϣ
				deInfo = DAOFactory.getIDepartmentDAOInstance().getByName(
						deInfo.getName());
				classInfo = new ClassInfo();
				classInfo.setDepartmentNumber(deInfo.getDepartmentNumber());
				classInfo.setName((String) map.get("������"));
				DAOFactory.getIClassInfoDAOInstance().doCreate(classInfo); // �����༶��Ϣ
				classInfo = DAOFactory.getIClassInfoDAOInstance().getByName(
						classInfo.getName());
				studentInfo = new StudentInfo();
				studentInfo.setName((String) map.get("����"));
				studentInfo.setSex((String) map.get("�Ա�"));
				studentInfo.setProfession((String) map.get("רҵ����"));
				studentInfo.setStudentNumber(Long.parseLong((String) map
						.get("ѧ��")));
				studentInfo.setClassNumber(classInfo.getClassNumber());
				studentInfo.setDepartmentNumber(deInfo.getDepartmentNumber());
				studentInfo.setEnterYear(year);
				DAOFactory.getIStudentInfoDAOInstance().doCreate(studentInfo); // ����ѧ����Ϣ
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("������Ϣʧ�ܣ�");
				flag = false;
			}
		}
		// ������Ϣ���
		if (flag) {
			request.setAttribute("msg", "�ϴ��ɹ���");
		} else {
			request.setAttribute("msg", "�ϴ�ʧ�ܣ�");
		}
		request.getRequestDispatcher("import.jsp").forward(request, response);
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

}
