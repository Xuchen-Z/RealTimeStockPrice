//Xuchen Zheng
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StockInfoGetter {

	public static void main(String[] args) throws InterruptedException {
		//require path for input & output files
		Scanner input = new Scanner(System.in);
		System.out.println("Please input the Excel file path for stock codes.");
		String codePath = input.nextLine();
		System.out.println("Please input the path for output.");
		String outputPath = input.nextLine();
		
		while(true) {
			ExcelReader newGetter = new ExcelReader();
			newGetter.readIn(codePath, outputPath);
			//delay
			TimeUnit.SECONDS.sleep(10);
		}

	}

}
