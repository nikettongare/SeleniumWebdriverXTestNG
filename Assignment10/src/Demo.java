import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.io.*;
public class Demo {
public static void main(String[] args) throws Exception {
	File f = new File(System.getProperty("user.dir") + "\\src\\resources\\dummy.xlsx");
	FileInputStream fis = new FileInputStream(f);		
	XSSFWorkbook workbook_obj = new XSSFWorkbook(fis);
	XSSFSheet sheet_obj = workbook_obj.getSheet("login");
	
	int totalRow  = sheet_obj.getLastRowNum();
	System.out.println(totalRow);
	XSSFRow firestRow = sheet_obj.getRow(0);
	int totalCells = firestRow.getLastCellNum();
	System.out.println(totalCells);
	
	DataFormatter format = new DataFormatter();
	String testData[][] = new String[totalRow][totalCells];
	
	for(int i = 1; i <= totalRow ; i++) {
		for(int j = 0; j < totalCells; j++) {
			testData[i-1][j] = format.formatCellValue(sheet_obj.getRow(i).getCell(j));
		}
	}

	workbook_obj.close();
	
	System.out.println(testData);
	
	
}
}
