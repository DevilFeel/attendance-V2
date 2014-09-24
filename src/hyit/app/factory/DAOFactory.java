package hyit.app.factory;

import hyit.app.dao.IClassInfoDAO;
import hyit.app.dao.IDepartmentInfoDAO;
import hyit.app.dao.ISelectionInfoDAO;
import hyit.app.dao.ISemesterInfoDAO;
import hyit.app.dao.ISessionInfoDAO;
import hyit.app.dao.IStudentInfoDAO;
import hyit.app.dao.ISubjectInfoDAO;
import hyit.app.dao.ITeacherInfoDAO;
import hyit.app.dao.proxy.ClassInfoDAOProxy;
import hyit.app.dao.proxy.DepartmentInfoDAOProxy;
import hyit.app.dao.proxy.SelectionInfoDAOProxy;
import hyit.app.dao.proxy.SemesterDAOProxy;
import hyit.app.dao.proxy.SessionInfoDAOProxy;
import hyit.app.dao.proxy.StudentInfoDAOProxy;
import hyit.app.dao.proxy.SubjectInfoProxy;
import hyit.app.dao.proxy.TeacherInfoDAOProxy;

public class DAOFactory {
	public static ITeacherInfoDAO getITeacherInfoDAOInstance() throws Exception {
		return new TeacherInfoDAOProxy();
	}

	public static IStudentInfoDAO getIStudentInfoDAOInstance() throws Exception {
		return new StudentInfoDAOProxy();
	}

	public static IDepartmentInfoDAO getIDepartmentDAOInstance()
			throws Exception {
		return new DepartmentInfoDAOProxy();
	}

	public static IClassInfoDAO getIClassInfoDAOInstance() throws Exception {
		return new ClassInfoDAOProxy();
	}

	public static ISemesterInfoDAO getISemesterDAOInstance() throws Exception {
		return new SemesterDAOProxy();
	}

	public static ISubjectInfoDAO getISubjectInfoDAOInstance() throws Exception {
		return new SubjectInfoProxy();
	}

	public static ISessionInfoDAO getISessionInfoDAOInstance() throws Exception {
		return new SessionInfoDAOProxy();
	}

	public static ISelectionInfoDAO getISelectionInfoDAOInstance()
			throws Exception {
		return new SelectionInfoDAOProxy();
	}
}
