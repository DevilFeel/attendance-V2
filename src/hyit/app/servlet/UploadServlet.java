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
		boolean flag = true; // 设置标识
		String p = request.getSession().getServletContext().getRealPath("/"); // 获取物理路径
		String path = null;
		SmartUpload mySmartUpload = new SmartUpload();
		mySmartUpload.setMaxFileSize(1024 * 1024 * 2); // 设置每个文件大小上限为2M
		try {
			mySmartUpload.initialize(config, request, response);
			mySmartUpload.upload();
			com.jspsmart.upload.File file = mySmartUpload.getFiles().getFile(0);
			year = mySmartUpload.getRequest().getParameter("year"); // 获取入学年份
			name = "/" + year + "学生名单.xls";
			file.saveAs(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("文件上传失败！");
			flag = false;
		}
		// 处理xls名单
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
			System.out.println("打开xls失败");
			flag = false;
		}
		Sheet sheet = rwb.getSheet(0); // 获取第一张表
		int row = sheet.getRows(); // 获得最大行数
		int cloumn = sheet.getColumns(); // 获得最大列数
		String[] key = new String[cloumn]; // 用于取得xls内第一行的字段，用于下面匹配
		for (int i = 0; i < cloumn; i++) {
			Cell cell = sheet.getCell(i, 0);
			key[i] = cell.getContents();
		}
		for (int j = 1; j < row; j++) {
			map = new HashMap<String, String>(); // 每行记录保存在Map中，根据字段提取
			for (int i = 0; i < cloumn; i++) {
				Cell cell = sheet.getCell(i, j);
				map.put(key[i], cell.getContents());
			}
			all.add(map);
		}
		Iterator<Map<String, String>> iter = all.iterator();
		while (iter.hasNext()) {
			StudentInfo studentInfo = null; // 初始化变量
			DepartmentInfo deInfo = null; // 初始化变量
			ClassInfo classInfo = null; // 初始化变量
			map = iter.next();
			// 把info插入要数据库中
			/*
			 * 进行以下操作，插入department 使用deID创建class与 使用classID和deID插入student
			 */
			deInfo = new DepartmentInfo();
			deInfo.setName((String) map.get("学院"));
			try {
				DAOFactory.getIDepartmentDAOInstance().doCreate(deInfo); // 创建学院信息
				deInfo = DAOFactory.getIDepartmentDAOInstance().getByName(
						deInfo.getName());
				classInfo = new ClassInfo();
				classInfo.setDepartmentNumber(deInfo.getDepartmentNumber());
				classInfo.setName((String) map.get("行政班"));
				DAOFactory.getIClassInfoDAOInstance().doCreate(classInfo); // 创建班级信息
				classInfo = DAOFactory.getIClassInfoDAOInstance().getByName(
						classInfo.getName());
				studentInfo = new StudentInfo();
				studentInfo.setName((String) map.get("姓名"));
				studentInfo.setSex((String) map.get("性别"));
				studentInfo.setProfession((String) map.get("专业名称"));
				studentInfo.setStudentNumber(Long.parseLong((String) map
						.get("学号")));
				studentInfo.setClassNumber(classInfo.getClassNumber());
				studentInfo.setDepartmentNumber(deInfo.getDepartmentNumber());
				studentInfo.setEnterYear(year);
				DAOFactory.getIStudentInfoDAOInstance().doCreate(studentInfo); // 创建学生信息
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("创建信息失败！");
				flag = false;
			}
		}
		// 导入信息完成
		if (flag) {
			request.setAttribute("msg", "上传成功！");
		} else {
			request.setAttribute("msg", "上传失败！");
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
