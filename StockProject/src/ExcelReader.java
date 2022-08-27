//Xuchen Zheng
import java.io.File;  
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public void readIn(String addressR, String addressW) {
		try {
			//generate sheet and row iterator
			FileInputStream infoFile = new FileInputStream(new File(addressR));
			XSSFWorkbook infoWorkbook = new XSSFWorkbook(infoFile);
			XSSFSheet sheet = infoWorkbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			
			//generate array lists for stock codes and bought price for each stock
			ArrayList<String> code = new ArrayList<>();
			ArrayList<String> price = new ArrayList<>();
			
			//go through each row, put codes and prices into the array lists
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator(); 
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					code.add(cell.getStringCellValue());
					cell = cellIterator.next();
					price.add(Double.toString(cell.getNumericCellValue()));
				}
			}
			// call writing method
			ExcelWriter newStock = new ExcelWriter();
			newStock.writeTo(code, price, addressW);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}
