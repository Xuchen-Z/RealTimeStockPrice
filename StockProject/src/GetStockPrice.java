//Xuchen Zheng
import java.net.*;
import java.io.*;


public class GetStockPrice {
	private String inputLine;
	private String stockName;
	private String stockPrice;
	private String stockTime;
	
	public GetStockPrice(String StockURL) throws Exception {
		URL stockInfo = new URL(StockURL); // URL of the stock price
		
		//read text from the HTML page and split the text into parts
		BufferedReader in = new BufferedReader(new InputStreamReader(stockInfo.openStream()));
		inputLine = in.readLine();
		if (inputLine != null) {
			String StockString[] = inputLine.split("~", 48);
			stockName = StockString[1];
			stockPrice = StockString[3];
			stockTime = StockString[30];
		}
		in.close();
	}
	
	public String getStockName(){
		return stockName;

	}
	public String getStockPrice(){
		return stockPrice;

	}
	public String getStockTime(){
		return stockTime;

	}
	public String timeSplit(String unsplit){
		String timeSplitI[] = unsplit.split("(?<=\\G.{4})");				//obtain year & second while others needs further split
		String timeSplitII[] = timeSplitI[1].split("(?<=\\G.{2})");		//obtain month & date
		String timeSplitIII[] = timeSplitI[2].split("(?<=\\G.{2})");		//obtain hour & minute
		return timeSplitI[0] + "-" + timeSplitII[0] + "-" + timeSplitII[1] + "-" + timeSplitIII[0] + ":" + timeSplitIII[1] + ":" + timeSplitI[3];

	}
}
