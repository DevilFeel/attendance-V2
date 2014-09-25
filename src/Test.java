import java.io.File;
import java.io.IOException;
import java.sql.Date;

import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Test {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		Date d = new Date(System.currentTimeMillis());
		d = new Date(d.getTime()+7*86400000);
		System.err.println(d);
	}

}
