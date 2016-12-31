package FirstBeta;

public class XpathBuilder {
	static public String xpRedcheckboxLocation(int rowNum){
		if (rowNum == 0) {
			return (new String(".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[2]/td[1][@class='submitted']/img[@src='/gui/check.gif']"));
			}
		
		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
		String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(rowNum)).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}
	
	static public String tabCharNameAndDetails(int row) {
		// IMPORTANT :  FOR the top character NAME + DETAILS below ( ROW ==0 ) you must parse the tokens  and TAKE THE TOP TWO TOKENS   //[|//>|//<
		// /html/body/div[2]/table[2]/tbody/tr/td/p[ (2 * (X)) ]/a
		int twiceRow = row *2;
		if (row == 0) {
		  		Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td");
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td"));
		}
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		Logging.slog(xPath);
				return (xPath);
		 
	}
	
	
	static public String xpBetaCharacterName(int row) {
		if (row == 0) {
			Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']");
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
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath= new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	static public String xpInternalAAhref(int row) {
		if (row == 0) {
			//return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@href"));
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		//String rightPart = "]/a[@class='breakdown-open-add-role']/@href";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		String xPath= new String(((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart)));
		Logging.slog(xPath);
		return (xPath);
		 
	}
	
	
	static public String tabAAclass(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a"));
		}
		
		//The second character would have the class attribute at:
		//    .//*[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a";
		String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
		Logging.slog(xPath);
		return (xPath);
	}
	
	static public String tabProductionInRow(int row) {
	//    	ROW+2
		int rowPlusTwo = row + 2;
		String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
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
			String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
			String rightPart = "]/td[1][@class='submitted']/img[@src='/gui/check.gif']";
			Logging.slog((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));		 
			String xPath= new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
			return (xPath);
		}
	
	static public String xpLinkCharactersInProduction(int row) {
		//    	.//*[@id='mainContent']/div[5]/table/tbody/tr[2]/td[3]/a[starts-with(@href,'/projects/')]
		//      .//*[@id='mainContent']/table[2]/tbody/tr/td/a[starts-with(@href, 'javascript:')]	
		
		if (row ==0){
//			return new String(".//*[@id='mainContent']/div[3]/table/tbody/tr/td[3]/a[starts-with(@href,'/projects/')]");
		}
		int rowPlusTwo = row + 2;
			//String leftPart = ".//*[@id='mainContent']/div[5]/table/tbody/tr[";
			String leftPart = ".//*[@id='mainContent']/div[@class='list']/table/tbody/tr[";
			String rightPart = "]/td[3]/a[starts-with(@href,'/projects/')]";
			 
			String xPath= new String((new String(leftPart)).concat(String.valueOf(rowPlusTwo)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath); 
		}
	
	
	
	
	static public String xpChooseMySmilePhoto() {
		//String xPath=  ".//*[@id='photo_5002739']/table/tbody/tr/td/span[1]";
	 
		String xPath=  ".//*[@id='photo_5002739']/table/tbody/tr/td/a[starts-with(@href,'javascript: highlightPhoto(500')][2]";
	//	String xPath=  " //a[contains(text(),'Use This Photo')])[4]";
		
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpChooseBookstoreVideo1() {
	//	String xPath=  "//tbody/tr/td[1]/input[@value='2629412']";
		Logging.slog("don't hve it ye");
		return (""); 
	}
	
	static public String xpChooseCommercialVideo2() {
		// video num 2584865
		String xPath=  "//input[@name='video_to_use']";
		//String xPath=  "//tbody/tr/td[1]/input[@value='2584865']";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpIncludeSizes() {
		String xPath="//input[@id='include_sc_checkbox_id']";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpTalentNotesAA() {
		String xPath="html/body/form/div[6]/textarea";
		Logging.slog(xPath);
		return (xPath); 
	}
	

	static public String xpAddToCartAA() {
		String xPath=	"//a[@id='add_to_cart']";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpCartLogo() {
		String xPath="//ul[@id='greeting']/li[3]/a";
		Logging.slog(xPath);
		return (xPath); 
	}
	static public String xpSubmitCart() {
		String xPath=	"//a[@id='cartsubmit']";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	
	
	static public String xpProdDetailsLeftWithTimeRoleAdded() {
 
			String xPath=  "//div[@id='mainContent']/table/tbody/tr/td[1]";
			Logging.slog(xPath);
			return (xPath); 
		}
	
	static public String xpProdDetialsRight() {
		 
		String xPath=  "//div[@id='mainContent']/table/tbody/tr/td[3]/p";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpLogout() {
		 
		String xPath=  "//div[@id='mainContent']/table/tbody/tr/td[3]/p";
		Logging.slog(xPath);
		return (xPath); 
	}
	
	static public String xpCharacterLinkInCharactersPage(int row) {
			if (row == 0) {
				Logging.slog(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[starts-with(@href, 'javascript:')]");
				return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[starts-with(@href, 'javascript:')]"));
			}
			//The second character would have the class attribute at:
			//    .//*[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a
			int twiceRow = row *2;
			String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
			String rightPart = "]/a[starts-with(@href, 'javascript:')]";
			String xPath= new String((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
			Logging.slog(xPath);
			return (xPath);
		}

	public String clickCharacterName(String charInternalNameRole, String bid, int regionNum) {
		/*
		 * THIS IS THE JAVASCRIPT VERSION to create:
		 * http://www.actorsaccess.com/projects/?view=selectphoto&from=
		 * breakdowns&region=3&iid=3328530&bid=531436 function selectPhoto(iid,
		 * bid, el) { var editcart = ""; var winl = (screen.width - 800) / 2;
		 * var wint = (screen.height - 600) / 2; winprops =
		 * 'top='+wint+',left='+winl; if (typeof el !== 'undefined' &&
		 * el.tagName == 'A' && el.text.indexOf('CHANGE PHOTO') > -1){ editcart
		 * = "&editcart=1"; }
		 * window.open('/projects/?view=selectphoto&from=breakdowns&region=3&iid
		 * =' + iid + '&bid=' + bid + editcart, 'select_photo',
		 * 'scrollbars,resizable,width=800,height=600,' + winprops); }
		 */

		String url = "";
		url += new String("/projects/?view=selectphoto&from=breakdowns&region=");
		url += new String(Beta.intToRegion(regionNum));
		url += new String("&iid=");
		url += new String(charInternalNameRole);
		url += new String("&bid=");
		url += new String(bid);
		url += new String(", 'select_photo', 'scrollbars,resizable,width=800,height=600,' + winprops");
		return new String(url);

	}

	
	
}
//      .//*[@id='mainContent']/table[1]/tbody/tr[1]/td[1]