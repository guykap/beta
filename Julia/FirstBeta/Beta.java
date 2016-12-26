package FirstBeta;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Beta {
	// THIS IS BETA2.0a
	private static WebDriver driver;
	static public String cnBaseUrl;
	static public String aaBaseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;

	String parentWindowHandler;
	String newWindowHandler;
	Iterator<String> windowHandlesIterator;
	Set<String> handles;
	public static boolean isCastingNetworks = false;
	private static final String DEFAULT_OUTPUT_FILE_WINDOWS = "C:\\Users\\Administrator\\workspace\\here\\logs\\log_";
	private static final String DEFAULT_OUTPUT_FILE_LINUX = "";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\Administrator\\workspace\\here\\Julia\\gecko_driver\\";
	static public boolean seekBackgroundWork;
	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;
	static Breath takeBreath;
	static Logging bestLog;
	static Actor dan;

	public static void main(String[] args) {

		seekBackgroundWork = true;
		JUnitCore jCore;
		// SETUP LOG
		String fileOut;
		if (args.length > 0) {
			fileOut = new String(args[0]);
		} else {
			fileOut = new String(DEFAULT_OUTPUT_FILE_WINDOWS);
		}
		String appendixFileName = (new String((new Long(System.currentTimeMillis())).toString())).concat(".txt");

		try {
			// initialize log
			bestLog = new Logging(new String(fileOut).concat(appendixFileName));
			Breath.init(bestLog);
  
		} catch (Exception e) {

			bestLog.log("File Appender error. Farewell");
			return;
		}

		try {
			// initialize Actor Sam - Just here as a debug. Actor ID = "10001"
		//	sam = new Actor("10001", "guykapulnik", "cPassword", "guykapulnik", "aPassword");
			dan = new Actor("10002", "daniellevi", "qvzbchsm", "daniellevi", "password");
		//	mara = new Actor("10003", "mara", "abcd", "mara", "password");
			 
		} catch (Exception e) {

		}

		// SETUP GECKO DRIVER
		if (args.length > 1) {
			gecko_driver_path = (new String(args[1]).concat("\\"));
		} else {
			gecko_driver_path = new String(DEFAULT_GECKO_DRIVER_LIBRARY);
		}
		System.setProperty("webdriver.gecko.driver", (new String(gecko_driver_path)).concat("geckodriver.exe"));

		// START
		jCore = new JUnitCore();
		jCore.run(Beta.class);
		bestLog.log("Program ENDED - THANK YOU!");

	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testBetaAA() throws Throwable {
		bestLog.log("Actors Access");
		testBetaB();
	}

	public void testBetaCN() throws Throwable {
		bestLog.log("Casting Networks");
		testBetaB();
	}

	public void testBetaB() throws Throwable {

		try {
			driver = new FirefoxDriver();
		} catch (Exception e) {
			bestLog.log("Error. Fire Fox driver not found.");
			bestLog.log(e.getMessage());
			return;
		}
		loginCounter = 1;
		while (networkWorking()) {
			bestLog.log("Login number " + loginCounter);
			if (loginCounter > 10) {
				bestLog.log("THIS IS 10TH LOGIN - stopping ");
				return;
			}
			if ((loginCounter % 3) == 0) {
				bestLog.log("THIS IS a 3rd LOGIN - THEN CLOSE WINDOW and start new Driver ");
				killFirefoxAndOpenNew();
			}

			try {
				if (isCastingNetworks) {
					loginCN();
					seekBackgroundWork = true;
				} else {
					// Actors access
					loginAA();
				}
			} catch (Exception e) {
				bestLog.log(e.getMessage());
				bestLog.log("Something went during login -> So lets login again");
				loginCounter++;
				continue;
			}

			try {
				if (isCastingNetworks) {
					coreCastingNetworks();
				} else {
					coreActorsAccess();
				}
			} catch (Exception e) {
				bestLog.log(e.getMessage());
				bestLog.log("Something went wrong -> Back to Login");
				loginCounter++;
			}
		}
	}

	public void loginAA() throws Throwable {
		aaBaseUrl = "http://actorsaccess.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		bestLog.log("LOGIN-AA");
		Breath.makeZeroSilentCounter();
//		bestLog.log('a');
		bestLog.log(new String("Logining in username: ").concat(dan.getAaUsername()));
		driver.get(aaBaseUrl + "/");
		Breath.deepBreath();
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(dan.getAaUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(dan.getAaPassword());
		driver.findElement(By.id("login-btn")).click();

		// Breath.deepBreath();
		Breath.breath();
		if (!verifyLocation("//p[@id='breadcrumb']", "breakdown services, ltd")) {
			bestLog.log("Can't login.");
			throw new Exception();
		}
		log('c');
	}

	public void handleRegion(int region) throws Throwable {
		String regionUrl = (new String("http://www.actorsaccess.com/projects/?view=breakdowns&region="))
				.concat(String.valueOf(region));
		driver.get(regionUrl);
		bestLog.log((new String("Region ").concat(intToRegion(region))));
		String tag = new String(driver.findElement(By.xpath("//p[@id='breadcrumb']")).getText());

		if (!verifyLocation("//p[@id='breadcrumb']",
				(new String("home / breakdowns / ").concat(intToRegion(region))))) {
			bestLog.log("Can't find region ");
			throw new Exception();
		}
		Breath.breath();
		for (int rowNum = 8; rowNum < 10; rowNum++) {
			String srcOfImg = "";
			log('j');
			bestLog.log("Checking for red check at row number: " + rowNum);
			int trCheckRow = (2 + rowNum);

			String checkPos = ((new String("//div[@id='mainContent']/div[3]/table/tbody/tr["))
					.concat(String.valueOf(trCheckRow))).concat("]/td/img");

			// verify that there is no red check on left of offer row
			try {
				assertFalse(isElementPresent(By.xpath("//div[@id='mainContent']/div[3]/table/tbody/tr[3]/td/img")));
				//assertFalse(isElementPresent(By.xpath(checkPos)));
				// true if the element is present, false otherwise
				bestLog.log("Check at " + rowNum + " from top.");
				continue;
			} catch (Error e) {
				bestLog.log("offer not submitted - lets try it.");
			}

			offer = new Job(dan.getActorId());
			handleAAOffer(trCheckRow);
			if (offerHasBeenConsideredBeforeAA(offer)) {
				continue;
			}

			// in this production there might be several roles ( offers) - so we
			// will open the page and read those character roles
			// click the link
			String leftPart = (new String("//div[@id='mainContent']/div[3]/table/tbody/tr["))
					.concat(String.valueOf(trCheckRow));
			String path = (new String(leftPart)).concat("]/td[3]/a");

			try {
				driver.findElement(By.xpath(path)).click();
				// offer.setOfferProjectName(new
				// String(driver.findElement(By.xpath(path)).getText()));
			} catch (Exception e) {
				// link hasn't opened.
				continue;
			}

			windowStatus();
			driver.switchTo().window(getSonWindowHandler());
			windowStatus();
			// verify that the characters in production page opened
			if (!verifyLocation("//table/tbody/tr/td/p", offer.getOfferProjectName())) {
				bestLog.log("Cannot find the production name on the new page");
				killSubWindowAndMoveToParentWindow();
				continue;
			}

			totalOffersInThisProd(offer);
			bestLog.log((new String("Number of Characters found in this production"))
					.concat(String.valueOf(offer.getNumberOfCharactersOnThisProduction())));

			// debug
			Breath.silentCount();
		}
	}

	public void coreActorsAccess() throws Throwable {
		// go over the chosen regions and submit to each region
		while (true) {
			for (int regionNum = 8; regionNum < 10; regionNum++) {
				handleRegion(regionNum);
				Breath.nap();
			}
		}
	}

	public void loginCN() throws Throwable {
		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		Breath.makeZeroSilentCounter();
		bestLog.log("LOGIN-CN");
		seekBackgroundWork = true;
		log('a');
		driver.get(cnBaseUrl + "/");
		Breath.deepBreath();
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(dan.getCnUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(dan.getCnPassword());
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Breath.breath();
		driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
		// check for welcome window:
		if (!verifyLocation("//div[@id='maininfo']/h2", "Welcome")) {
			bestLog.log("Can't find Welcome ");
			throw new Exception();
		}
		log('c');
		Breath.breath();
	}

	public void coreCastingNetworks() throws Throwable {
		bestLog.log("coreCastingNetworks");
		// first time in coreLoop - always begin with Extra chart
		driver.findElement(By.xpath("//a[@id='_ctl0_cphBody_lnkExtrasRoles']")).click();
		if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
			bestLog.log("Can't find Extras chart");
			throw new Exception();
		}
		bestLog.log("In Extras chart");
		while (true) {
			heartLoop();
			seekBackgroundWork ^= true;
			if (seekBackgroundWork) {
				bestLog.log("ALTERNATE to BACKGROUND work");
			} else {
				bestLog.log("ALTERNATE to PRINCIPLE work");
			}
			Breath.nap();
		}
	}

	public void heartLoop() throws Throwable {
		Breath.breath();
		if (seekBackgroundWork) {
			if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				Breath.deepBreath();
				if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
					bestLog.log("Can't find Extras chart");
					throw new Exception();
				}
			}
		} else {
			// We want to be in principle chart
			if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Principals")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				Breath.deepBreath();
				if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Principals")) {
					bestLog.log("Can't find principle chart");
					throw new Exception();
				}

			}
		}
		new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
		Breath.deepBreath();
		for (int rowNum = 0; rowNum < 10; rowNum++) {
			log('j');
			bestLog.log("Checking for green star at row number: " + rowNum);
			int trStarRow = (3 * rowNum);
			trStarRow += 4;
			String starPos = ((new String("//div[@id='DirectCastMainDiv']/table/tbody/tr["))
					.concat(String.valueOf(trStarRow))).concat("]/td/span/img");
			String srcOfImg = "";
			try {
				srcOfImg = new String(driver.findElement(By.xpath(starPos)).getAttribute("src"));
			} catch (Error e) {
				bestLog.log(e.getMessage());
				verificationErrors.append(e.toString());
			}

			if (srcOfImg.contains("spacer.gif")) {
				bestLog.log("No star on offer " + rowNum + " from top.  Let's try submitting.");
				offer = new Job(dan.getActorId());
				handleBackgroundWorkOffer(seekBackgroundWork, (trStarRow - 1));
				if (offerHasBeenConsideredBeforeCN(offer)) {
					continue;
				}
				Jobs.add(offer);
				// debug
				Breath.silentCount();

				offer.readNotice();
				offer.makeDecision();

				if ((offer.getHasBeenSubmitted()) || (!offer.getDecisionSubmit())) {
					bestLog.printDecisionMakingVars(offer);
					continue;
				}

				log('h');
				int trLinkToOfferRow = -1;
				trLinkToOfferRow = trStarRow - 1;
				String linkOfferPos = ((new String("//tr[")).concat(String.valueOf(trLinkToOfferRow))).concat("]/td/a");
				driver.findElement(By.xpath(linkOfferPos)).click();
				Breath.deepBreath();
				driver.switchTo().window(getSonWindowHandler());
				windowStatus();
				// add time of apperance to offer
				try {
					Breath.breath();
					offer.setOfferTimeRoleAdded(
							new String(driver.findElement(By.xpath("//table[5]/tbody/tr[3]/td")).getText()));
				} catch (Exception e) {
					offer.setOfferTimeRoleAdded(new String(""));
				}

				driver.findElement(By.xpath("//a[contains(text(),'submit')]")).click();
				Breath.breathToMissleadThem();
				if (!verifyLocation("//span", "Customize your submission")) {
					bestLog.log("Error: You are on wrong window");
					windowStatus();
					throw new Exception();
				}
				log('l');
				driver.findElement(By.id("TALENTNOTE")).clear();
				choosePhoto();

				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				Breath.deepBreath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				Breath.deepBreath();
				if (!verifyLocation("//span", "Submission Successful")) {
					bestLog.log("Did NOT recieve final submittion successful");
					windowStatus();
					throw new Exception();
				}
				if (!killSubWindowAndMoveToParentWindow()) {
					bestLog.log("Memory leak error: failed killing child window");
					throw new Exception();
				}
				offer.setHasBeenSubmitted(true);
				Breath.makeZeroSilentCounter();
				log('m');
				bestLog.printSubmittions(Jobs);
			} else {
				bestLog.log("Found star on the offer " + rowNum + " from top");
				Breath.silentCount();
			}
		}
	}

	public void choosePhoto() {
		if (offer.getNeedTuxedo()) {
			// choose tuxedo photo
		} else if (offer.getIsGuard()) {
			// choose guard photo
		} else {
			// choose the default photo
		}
	}

	public void killFirefoxAndOpenNew() {
		WebDriver tempDriver = driver;
		driver = new FirefoxDriver();
		tempDriver.quit();
	}

	public void aaDecideToSubmit() {

	}

	private void handleAAOffer(int rowNum) {

		String leftPart = (new String("//div[@id='mainContent']/div[3]/table/tbody/tr["))
				.concat(String.valueOf(rowNum));
		String path;
		try {
			path = (new String(leftPart)).concat("]/td[2]");
			offer.setOfferPostedTime(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[3]/a");
			offer.setOfferProjectName(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferProjectName(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[4]");
			offer.setOfferTypeProject(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferTypeProject(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[5]");
			offer.setOfferCastingDirector(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferCastingDirector(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[6]");
			offer.setOfferShootDate(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferShootDate(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[7]");
			offer.setOfferUnionStatus(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferUnionStatus(new String(""));
		}
	}

	private int totalOffersInThisProd(Job parentOffer) {
		// returns the number of offers created and added to Jobs list from the
		// found characters on the production
		bestLog.log("Entered character breakdown");
		// for each character - we open a new offer
		// CHARACTER #1
		// timeDate

		String nameOfCharacterAndDetailsUnder;
		String detailsOfCharacter;
		int totalNumOfOffersInProduction = 1;

		try {
			String prodDetailsLeftWithTimeRoleAdded = new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/table/tbody/tr/td")).getText());
			bestLog.log((new String("prodDetailsLeftWithTimeRoleAdded=")).concat(prodDetailsLeftWithTimeRoleAdded));
			parseProdDetailsLeftWithTimeRoleAdded(parentOffer, prodDetailsLeftWithTimeRoleAdded);
		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return totalNumOfOffersInProduction;
		}

		try {
			String prodDetialsRight = new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/table/tbody/tr/td[3]/p")).getText());
			parseProdDetialsRight(parentOffer, prodDetialsRight);

			bestLog.log((new String("prodDetailsRight = ")).concat(prodDetialsRight));

		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return totalNumOfOffersInProduction;
		}

		// begin adding the characters
		Job currentOffer = parentOffer;
		int rowNum = 0;
		boolean moreCharsAvil = true;

		while (moreCharsAvil) {

			try {
				
				String internalAAname= tabAAname(rowNum);
				String internalAAhref=tabAAhref(rowNum);
				String internalAAclass=tabAAclass(rowNum);
				tabLocation(rowNum);
				
				String nameOfCharacter = new String(driver.findElement(By.xpath(tabLocation(rowNum))).getText());
				parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacter);
				bestLog.log((new String("NameOfCharacterAndDetailsUnder = ")).concat(nameOfCharacter));
				offer.readNoticeAA();
				offer.makeDecision();
				if ((offer.getHasBeenSubmitted()) || (!offer.getDecisionSubmit())) {
					bestLog.printDecisionMakingVars(offer);
					continue;
				}

				bestLog.log("lets submit!");
String clickPath = tabLocation(rowNum);
  //*[@id="mainContent"]/table[2]/tbody/tr/td/a
  //div[@id='mainContent']/table[2]/tbody/tr/td/a
				driver.findElement(By.xpath("//div[@id='mainContent']/table[2]/tbody/tr/td/a")).click();
			//	driver.findElement(By.xpath(tabLocation(rowNum))).click();
				Breath.deepBreath();
				driver.switchTo().window(getSonWindowHandler());
				windowStatus();
				// choose photo

				// chose videos

				// write talent notes with currentOffer.getMessage()

				// HIT SUBMIT

				// check if there is another character in this production
				if (verifyLocation(tabLocation(rowNum), "")) {
					rowNum++;
					moreCharsAvil = true;
					totalNumOfOffersInProduction++;
					// create another offer with the that will only differ in
					// the name of character and character details.
					Jobs.add(currentOffer);
					Job oldOffer = currentOffer;
					// calling the Copy C'or of Job
					currentOffer = new Job(oldOffer);

				} else {
					moreCharsAvil = false;
				}

			} catch (Exception e) {
				bestLog.log("Failed to submit it.");
				break;
			}
		}
		return totalNumOfOffersInProduction;
	}

	private String tabCharNameAndDetails(int row) {
		// /html/body/div[2]/table[2]/tbody/tr/td/p[ (2 * (X)) ]/a
		int twiceRow = row *2;
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td"));
		}
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]";
		return ((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
	}

	
	private String betaCharacterName(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']";
		return ((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
	}
	
	private String tabAAname(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@name"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']/@name";
		return ((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
	}
	
	private String internalAAhref(int row) {
		if (row == 0) {
			return (new String(".//*[@id='mainContent']/table[2]/tbody/tr/td/a[@class='breakdown-open-add-role']/@href"));
		}
		int twiceRow = row *2;
		String leftPart = ".//*[@id='mainContent']/table[2]/tbody/tr/td/p[";
		String rightPart = "]/a[@class='breakdown-open-add-role']/@name";
		return ((new String(leftPart)).concat(String.valueOf(twiceRow)).concat(rightPart));
	}
	
	
	  
	String internalAAclass=tabAAclass(rowNum);
	tabLocation(rowNum);
	
	
	/*
	 * try { String tag11 = new String(
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a"))
	 * .getAttribute("class")); bestLog.log("11 - breakdown-open-add-role:");
	 * bestLog.log(tag11); } catch (Exception e) { } try { String tag12 = new
	 * String( driver.findElement(By.xpath(
	 * "//div[@id='mainContent']/table[2]/tbody/tr/td/p[2]/a"))
	 * .getAttribute("name")); bestLog.log("12 - AA_ROLE_NUMBER:");
	 * bestLog.log(tag12); } catch (Exception e) { }
	 */

	private void parseProdDetailsLeftWithTimeRoleAdded(Job char_offer, String data) {
		bestLog.log("parse it");
		char_offer.addToProductionDetails(data);
		String delims = "['\n']";
		String[] tokens = data.split(delims);
		char_offer.setOfferTimeRoleAdded(tokens[0]);
		char_offer.setOfferTypeProject(tokens[2]);
		char_offer.setOfferUnionStatus(tokens[3]);
		// String details = tokens[1];
	}

	private void parseProdDetialsRight(Job char_offer, String data) {
		// ALL parsing should be done with REGEX , but right now only store in
		// the DB all the production info as one long String
		bestLog.log("parse it");
		char_offer.addToProductionDetails(data);
		/*
		 * String delims = "[[,],\n]"; String[] tokens = data.split(delims);
		 * String name= tokens[0]; String details = tokens[1]; String startDate
		 * = String location = tokens[5]; offer.setProductionDetails(data);
		 */
	}

	/*
	 * private void parseprodDetailsLeft(Job char_offer, String data) {
	 * bestLog.log("parse it"); char_offer.addToProductionDetails(data);
	 */
	// Here we should parse the director name, casting dir, assistant and so on
	/*
	 * 
	 * String delims = "[[,],\n]"; String[] tokens = data.split(delims); String
	 * name = tokens[0]; String details = tokens[1]; }
	 */

	private void nameOfCharacter() {
	}

	private void parseNameOfCharacterAndDetailsUnder(Job char_offer, String data) {
		bestLog.log("parse it");
		String delims = "\\[|\\]";
		String[] tokens = data.split(delims);
		String name = new String(tokens[1]);
		char_offer.setOfferCharacterName(name.trim());
		String details = new String(tokens[2]);
		char_offer.setOfferCharacterDetails(details.trim());
	}

	private void handleBackgroundWorkOffer(boolean isBackgroundWork, int row) {

		offer.setIsBackgroundWork(isBackgroundWork);
		// the EXTRA table has the shooting date .
		// the PRINCIPLE table does not

		String leftPart = (new String("//tr[")).concat(String.valueOf(row));
		try {

			try {
				String path = new String(leftPart.concat("]/td/a"));
				offer.setOfferRole((new String(driver.findElement(By.xpath(path)).getText())).toLowerCase());
			} catch (Exception e) {
				offer.setOfferRole(new String(""));
			}

			if (isBackgroundWork) {
				// BACKGROUND WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName((new String(driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferShootDate(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferShootDate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOfferTypeProject(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOffertRate(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferPaying(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferUnionStatus(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[8]/a"));
					offer.setOfferPostedTime(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			} else {
				// PRINCIPLE WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName((new String(driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferTypeProject(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOffertRate(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOfferPaying(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}
				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferUnionStatus(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferPostedTime(new String(driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			}
			try {
				String pathOfferListing = new String(
						((new String("//tr[")).concat(String.valueOf(row + 1))).concat("]/td"));
				offer.setOfferListing(
						new String(driver.findElement(By.xpath(pathOfferListing)).getText()).toLowerCase());
			} catch (Exception e) {
				offer.setOfferListing(new String(""));
			}
			return;

		} catch (Exception e) {
			bestLog.log("Error parsing the current offer data into the Strings");
			// go back to login page
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public void log(char stage) {
		// state - Full log - outputs the whole string
		// state - min log - outputs only the letter representing the stage
		if (!(bestLog.getLogState())) {
			bestLog.log(Character.toString(stage));
		} else {
			switch (stage) {
			case 'a':
				bestLog.log("A: Window handle Parent " + parentWindowHandler);
				break;
			case 'c':
				bestLog.log("C: Location->Home Page");
				break;
			case 'e':
				bestLog.log("E: Location->Casting Billboard");
				break;
			case 'f':
				bestLog.log("F: Succ opening Casing Billboards and Extras link");
				break;
			case 'h':
				bestLog.log("H: Succ adding offer to Jobs list");
				break;
			case 'i':
				bestLog.log(
						"I: Begin submittion for top offer id " + offer.getOfferId() + " : " + offer.getOfferRole());
				break;
			case 'j':
				bestLog.log("J: Making sure there is no GREEN STAR/red check");
				break;

			case 'l':
				bestLog.log("L: Succ on openning window to choose photo and fill talent notes.");
				break;
			case 'm':
				bestLog.log("*******SUBMITTED:");
				bestLog.log("M: Succ Submitted: " + offer.getOfferProjectName() + " | "
						+ offer.getOfferSubmittionDateTime() + " | " + offer.getOffertRate() + " | "
						+ offer.getOfferTypeProject() + " | " + offer.getHasBeenSubmitted() + " | "
						+ offer.getOfferListing() + "Talent Notes :" + offer.getMessage());
				break;
			case 'y':
				bestLog.log("Parent: " + getParentWindowHandler() + " Son: " + getSonWindowHandler());
				break;
			case 'z':
				bestLog.log("Z: Stopping");
				break;
			}
		}
	}

	private boolean moveToOtherWindow() {
		windowStatus();
		String currentWindowHandler = driver.getWindowHandle();
		handles = driver.getWindowHandles(); // get all window handles
		if (handles.size() < 2) {
			bestLog.log("Error: there is only one window : " + currentWindowHandler);
			return false;
		}
		windowHandlesIterator = handles.iterator();
		if (windowHandlesIterator.hasNext()) {
			newWindowHandler = windowHandlesIterator.next();
			if (!newWindowHandler.equals(currentWindowHandler)) {
				driver.switchTo().window(newWindowHandler); // switch to popup
															// window
				windowStatus();
				return true;
			} else {
				// fell on the same window - so move again
				if (windowHandlesIterator.hasNext()) {
					newWindowHandler = windowHandlesIterator.next();
					if (!newWindowHandler.equals(currentWindowHandler)) {
						driver.switchTo().window(newWindowHandler); // switching
																	// to
																	// popup
																	// window
						windowStatus();
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean killSubWindowAndMoveToParentWindow() {
		// returns true onlyon a succesfull kill the sub window and return back
		// to parent window.
		driver.close();
		driver.switchTo().window(parentWindowHandler);
		String newWindowHandler = driver.getWindowHandle();
		bestLog.log("killed window and returned to  " + newWindowHandler);
		windowStatus();
		return true;
	}

	private void windowStatus() {
		String currentWindowHandler = driver.getWindowHandle();
		String sonWindow = getSonWindowHandler();
		String pointing;
		if (getParentWindowHandler().equals(currentWindowHandler)) {
			log('y');
			bestLog.log("Now on PARENT");
		} else {
			log('y');
			bestLog.log("Now on SON");
		}
		driver.getWindowHandle();
		log('y');
		return;
	}

	private void windowStatus2() {
		handles = driver.getWindowHandles(); // get all window handles
		// String allHandles = new String(Arrays.toString(handles));

		StringBuilder builder = new StringBuilder();
		for (String s : handles) {
			builder.append(s + ",");
		}
		String allHandles = new String("[");
		allHandles += new String(builder.toString());
		allHandles += new String("] ");
		String currentWindowHandler = driver.getWindowHandle();
		bestLog.log(allHandles + " on: " + currentWindowHandler);
	}

	private String getParentWindowHandler() {
		if (parentWindowHandler.length() > 1) {
			return parentWindowHandler;
		}
		bestLog.log("Error finding Parent holder");
		return ("");
	}

	private String getSonWindowHandler() {
		String currentWindowHandler = driver.getWindowHandle();
		handles = driver.getWindowHandles(); // get all window handles
		windowHandlesIterator = handles.iterator();

		switch (handles.size()) {
		case 1:
			windowStatus2();
			return ("");
		case 2: {
			if (!currentWindowHandler.equals(getParentWindowHandler())) {
				return currentWindowHandler;
			} else {
				// finding out what the other window handler is
				if (windowHandlesIterator.hasNext()) {
					newWindowHandler = windowHandlesIterator.next();
					if (!newWindowHandler.equals(getParentWindowHandler())) {
						return newWindowHandler;
					} else {
						//
						if (windowHandlesIterator.hasNext()) {
							newWindowHandler = windowHandlesIterator.next();
							if (!newWindowHandler.equals(currentWindowHandler)) {
								return newWindowHandler;
							}
						}
					}
				}
			}
		}
		case 3:
			bestLog.log("Error there are 3 windows!");
			windowStatus2();
			return ("");
		}

		bestLog.log("Error finding SON");
		return ("");
	}

	private boolean verifyLocation(String xpathTab, String verifyText) {
		// returns true only if the location of the xpath contains the
		// verifyText
		try {
			String locationTest1 = new String(driver.findElement(By.xpath(xpathTab)).getText());
			if ((locationTest1.contains(verifyText))) {
				return true;
			}
		} catch (Exception e) {
			bestLog.log("Verify text " + verifyText + " Does NOT appear.");
			return false;
		}
		return false;
	}

	static public boolean networkWorking() {
		// returns true if there is a network connection

		return true;
	}

	static public boolean offerHasBeenConsideredBeforeCN(Job consideredOffer) {
		// checkcing in the list of Jobs for another offer with the same ROLE
		// and same PROJECT NAME values.
		for (Job offer : Jobs) {
			if (((consideredOffer.getOfferProjectName()).equals(offer.getOfferProjectName()))
					&& ((consideredOffer.getOfferRole()).equals(offer.getOfferRole()))
					&& (!offer.getHasBeenSubmitted())) {
				bestLog.log(
						"Found that this Project and role has already been considered and decided NOT to submit. This is Why: ");
				bestLog.printDecisionMakingVars(offer);
				return true;
			}
		}
		return false;
	}

	static public boolean offerHasBeenConsideredBeforeAA(Job consideredOffer) {
		// checkcing in the list of Jobs for another offer with the same ROLE
		// and same PROJECT NAME values.
		for (Job offer : Jobs) {
			if (((consideredOffer.getOfferProjectName()).equals(offer.getOfferProjectName()))
					&& (!offer.getHasBeenSubmitted())) {
				bestLog.log(
						"Found that this Project and role has already been considered and decided NOT to submit. This is Why: ");
				bestLog.printDecisionMakingVars(offer);
				return true;
			}
		}
		return false;
	}

	static public String intToRegion(int intRegion) {

		if (intRegion == 1)
			return new String("los angeles");
		if (intRegion == 2)
			return new String("new york");
		if (intRegion == 3)
			return new String("vancouver");
		if (intRegion == 4)
			return new String("chicago");
		if (intRegion == 5)
			return new String("florida");
		if (intRegion == 6)
			return new String("toronto");
		if (intRegion == 7)
			return new String("texas");
		if (intRegion == 8)
			return new String("hawaii");
		if (intRegion == 9)
			return new String("southeast");
		// default
		return new String("");
	}
}
