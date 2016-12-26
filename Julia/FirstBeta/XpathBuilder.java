package FirstBeta;

public class XpathBuilder {
	static public String redcheckboxLocation(int rowNum){
		if (rowNum == 0) {
			return (new String(".//*[@id='mainContent']/div[3]/table/tbody/tr[2]/td[1][@class='submitted']/img[@src='/gui/check.gif']"));
		
	//		return (new String(".//*[@id='mainContent']/div[5]/table/tbody/tr[2]/td[1][@class='submitted']/img[@src='/gui/check.gif']"));
			}
		
//		.//*[@id='mainContent']/div[5]/table/tbody/tr[3]/td[1][@class='submitted']/img[@src='/gui/check.gif']
		String leftPart = ".//*[@id='mainContent']/div[3]/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(rowNum)).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
		
		
		/*
	((new String("//div[@id='mainContent']/div[3]/table/tbody/tr["))
	.concat(String.valueOf(trCheckRow))).concat("]/td/img");*/
	}
	
	static public String tabCharNameAndDetails(int row) {
		// IMPORTANT :  FOR the top character NAME + DETAILS below ( ROW ==0 ) you must parse the tokens  and TAKE THE TOP TWO TOKENS   //[|//>|//<
		// /html/body/div[2]/table[2]/tbody/tr/td/p[ (2 * (X)) ]/a
		int twiceRow = row *2;
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td"));
		}
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		Logging.slog(xPath);
				return (xPath);
		 
	}

	
	static public String betaCharacterName(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String tabAAname(int row) {
	//returns the internal int key value assigned to the role in Actors Access.	
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@name"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']/@name";
		String xPath= new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String internalAAhref(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@href"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']/@href";
		String xPath= new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	
	static public String tabAAclass(int row) {
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a/@class";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}
	
	static public String tabProductionInRow(int row) {
	//    	ROW+2
		int rowPlusTwo = row + 2;
		String leftPart = ".//*[@id='mainContent']/div[3]/table/tbody/tr[";
		// 	String leftPart = ".//*[@id='mainContent']/div[5]/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']";
		String xPath= new String( ((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String tabRedCheckBoxPos(int row) {
		//    	ROW+2
			int rowPlusTwo = row + 2;
			//String leftPart = ".//*[@id='mainContent']/div[5]/table/tbody/tr[";
			String leftPart = ".//*[@id='mainContent']/div[3]/table/tbody/tr[";
			String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
			Logging.slog((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));		 
			String xPath= new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
			return (xPath);
		}
	
	static public String linkCharactersInProduction(int row) {
		//    	.//*[@id='mainContent']/div[5]/table/tbody/tr[2]/td[3]/a[starts-with(@href,'/projects/')]
			int rowPlusTwo = row + 2;
			//String leftPart = ".//*[@id='mainContent']/div[5]/table/tbody/tr[";
			String leftPart = ".//*[@id='mainContent']/div[3]/table/tbody/tr[";
			String rightPart = "]/td[3]/a[starts-with(@href,'/projects/')]";
			 
			String xPath= new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath); 
		}
}
