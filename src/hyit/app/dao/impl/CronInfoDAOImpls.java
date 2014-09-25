package hyit.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import hyit.app.dao.ICronInfoDAO;
import hyit.app.model.CronInfo;

public class CronInfoDAOImpls implements ICronInfoDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public CronInfoDAOImpls(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn = conn;
	}

	@Override
	public boolean doCreate(CronInfo info) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "INSERT INTO cron_info VALUES(NULL,?,NULL,?,NULL,NULL,?,0)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, info.getLessonNumber());
		this.pstmt.setDate(2, info.getExecuteDate());
		this.pstmt.setTimestamp(3, info.getOrderTime());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public boolean update(CronInfo info) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CronInfo delete(Integer number) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CronInfo getByID(Integer number) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CronInfo> getByLessonNumber(Integer number) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CronInfo> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
