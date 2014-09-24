import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Test {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		Workbook rwb = Workbook.getWorkbook(new File("D:\\tomcat7.0\\webapps\\attendanceV3\\upload\\2010学生名单.xls"));
	}

}
