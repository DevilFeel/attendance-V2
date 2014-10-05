import hyit.app.factory.DAOFactory;
import hyit.app.model.CronInfo;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CronInfo info = new CronInfo();
		info.setExecuteTime(new Time(System.currentTimeMillis()));
		info.setStatus(1);
		boolean f = DAOFactory.getICronInfoDAOInstance().update(info);
		System.err.println(f);
	}

}
