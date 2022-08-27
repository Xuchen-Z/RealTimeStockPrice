//Xuchen Zheng
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelWriter {
	
	public void writeTo(ArrayList<String> codeArr, ArrayList<String> buyPriceArr, String addressW) throws Exception{
		//build tree map with stock data
		XSSFWorkbook stockWorkbook = new XSSFWorkbook();
		XSSFSheet stockSpreadsheet = stockWorkbook.createSheet("Stock Info");
		XSSFRow row;
		Map<String, Object[]> stockData = new TreeMap<String, Object[]>();
		int arraySize = codeArr.size();
		stockData.put("1", new Object[] {"Code", "Name", "Current Price (Yuan)", "Time", "Bought Price (Yuan)"});	//tile of each column
		for (int i = 0; i < arraySize; i++) {
			GetStockPrice newStock = new GetStockPrice("http://qt.gtimg.cn/q=" + codeArr.get(i));		//common starting of the URL + stock code
			String rowNum = Integer.toString(i+2);
			stockData.put(rowNum, new Object[] {codeArr.get(i), newStock.getStockName(), newStock.getStockPrice(), newStock.timeSplit(newStock.getStockTime()), buyPriceArr.get(i)});
		}
		
		Set<String> keyid = stockData.keySet();
		int rowid = 0;
		
		//write to Excel
		for (String key : keyid) {
			row = stockSpreadsheet.createRow(rowid++);
			Object[] objectArray = stockData.get(key);
			int cellid = 0;
			
			for (Object obj : objectArray) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
			}
		}
		
		//output
		FileOutputStream out = new FileOutputStream(new File(addressW));
		
		stockWorkbook.write(out);
		out.close();
		
		//obtain current time		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date currentTime = new Date(System.currentTimeMillis());
		System.out.print("Excel File Successfully Generated, current time: ");
		System.out.println(formatter.format(currentTime));
	}

}
