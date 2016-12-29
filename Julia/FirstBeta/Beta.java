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
	public static WebDriver driver;
	static public String cnBaseUrl;
	static public String aaBaseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;

	String parentWindowHandler;
	String newWindowHandler;
	static Iterator<String> windowHandlesIterator;
	static public Set<String> handles;
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

	public static void main(String[] args) throws Throwable {

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
dan = new Actor("10001", "guykapulnik", "cPassword","guykapulnik", "aPassword");
			//dan = new Actor("10002", "daniellevi", "qvzbchsm", "daniellevi", "password");
			// mara = new Actor("10003", "mara", "abcd", "mara", "password");

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
		// jCore = new JUnitCore();
		// jCore.run(Beta.class);
		try {
			Beta test = new Beta();
			test.testBetaAA();
		} catch (Exception e) {
			Logging.slog(e.getMessage());
			Logging.slog("Error in program");
		}
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

			if ((loginCounter % 7) == 0) {
				bestLog.log("THIS IS a 7th LOGIN - kill gecko and close window as well ");
				if (ManageDriver.killGecko()) {
					System.setProperty("webdriver.gecko.driver",
							(new String(gecko_driver_path)).concat("geckodriver.exe"));
					killFirefoxAndOpenNew();
					loginCounter++;
					continue;
				}

			}
			try {
				if (isCastingNetworks) {
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
		driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		bestLog.log("LOGIN-AA");
		Breath.makeZeroSilentCounter();
		// bestLog.Logging.log('a');
		Logging.slog("Window handle Parent " + parentWindowHandler);
		bestLog.log(new String("Logining in username: ").concat(dan.getAaUsername()));
		driver.get(aaBaseUrl + "/");
		Breath.deepBreath();

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(dan.getAaUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(dan.getAaPassword());
		Breath.breath();
		driver.findElement(By.id("login-btn")).click();

		// Breath.deepBreath();
		Breath.breath();
		if (!verifyLocation("//p[@id='breadcrumb']", "breakdown services, ltd")) {
			bestLog.log("Can't login.");
			throw new Exception();
		}
		Logging.log('c');
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
		int rowNum = 6;
		boolean nextRowHasAnotherProd = true;

		while (nextRowHasAnotherProd) {

			bestLog.log("Checking for red check at row number: " + rowNum);
			// today
			try {
				assertTrue(isElementPresent(By.xpath(XpathBuilder.tabProductionInRow(rowNum))));
				bestLog.log((new String("Found a production at row: ").concat(String.valueOf(rowNum))));
				bestLog.log((new String("Check for red check on left of row: ").concat(String.valueOf(rowNum))));
			} catch (Exception e) {
				bestLog.log((new String("No production on row ").concat(String.valueOf(rowNum))));
				nextRowHasAnotherProd = false;
				break;
			}

			try {
				Breath.breath();
				assertFalse(isElementPresent(By.xpath(XpathBuilder.tabRedCheckBoxPos(rowNum))));
			} catch (Exception e) {
				bestLog.log((new String("Found a red check on left of row: ").concat(String.valueOf(rowNum))));
				continue;
			}
			bestLog.log((new String("Lets submit. Cause NO red check at row: ").concat(String.valueOf(rowNum))));
			// ***** submit
			offer = new Job(dan.getActorId());
			Scapper.handleAAOffer(offer, rowNum);
			if (offer.offerHasBeenConsideredBeforeAA(Jobs)) {
				rowNum++;
				continue;
			}

			try {
				driver.findElement(By.xpath(XpathBuilder.xpLinkCharactersInProduction(rowNum))).click();
			} catch (Exception e) {
				bestLog.log((new String("Error: the link hasn't opened on row: ").concat(String.valueOf(rowNum))));
				continue;
			}
			Breath.deepBreath();

			try {
				// debug - change this to assertTrue for the element of first
				// char
				String nameOfCharacterandDetails = new String(
						driver.findElement(By.xpath(XpathBuilder.tabCharNameAndDetails(0))).getText());
				bestLog.log("found at least one character in this production. Lets try submitting");
			} catch (Exception e) {
				bestLog.log("Error. We are not in the characters chars now. Lets return");
				driver.navigate().back();
			}
			totalOffersInThisProd(offer);
			bestLog.log((new String("Number of Characters found in this production: "))
					.concat(String.valueOf(offer.getNumberOfCharactersOnThisProduction())));
			bestLog.log((new String("Number of characters added to the cart").concat(String.valueOf(offer.getTotalAddedToCart()))));
			// debug
			Breath.silentCount();
			if(offer.getTotalAddedToCart() > 0 ){
				//go and sumit cart
				
				//***
			}
			rowNum++;
			driver.navigate().back();
		} // end of while loop
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
		Logging.slog("A: Window handle Parent " + parentWindowHandler);
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
		Logging.log('c');
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
		String originWindow = driver.getWindowHandle();

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
			Logging.log('j');
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

				Scapper.handleBackgroundWorkOffer(offer, seekBackgroundWork, (trStarRow - 1));
				if (offer.offerHasBeenConsideredBeforeCN(Jobs)) {
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

				Logging.log('h');
				int trLinkToOfferRow = -1;
				trLinkToOfferRow = trStarRow - 1;
				String linkOfferPos = ((new String("//tr[")).concat(String.valueOf(trLinkToOfferRow))).concat("]/td/a");
				driver.findElement(By.xpath(linkOfferPos)).click();
				Breath.deepBreath();
				driver.switchTo().window(ManageDriver.getSonWindowHandler(originWindow));
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
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				Logging.log('l');
				driver.findElement(By.id("TALENTNOTE")).clear();
				choosePhoto();

				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				Breath.deepBreath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				Breath.deepBreath();
				if (!verifyLocation("//span", "Submission Successful")) {
					bestLog.log("Did NOT recieve final submittion successful");
					ManageDriver.windowStatus(originWindow);
					throw new Exception();
				}
				if (!ManageDriver.killSubWindowAndMoveToParentWindow(parentWindowHandler)) {
					bestLog.log("Memory leak error: failed killing child window");
					throw new Exception();
				}
				offer.setHasBeenSubmitted(true);
				Breath.makeZeroSilentCounter();
				Logging.log('m');
				bestLog.printSubmittions(Jobs);
			} else {
				bestLog.log("Found star on the offer " + rowNum + " from top");
				Breath.silentCount();
			}
		}
	}

	public void submitCart(){
		//goes to cart page and clicks submit
		
		
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

	private int totalOffersInThisProd(Job parentOffer) {
		// returns the number of offers created and added to Jobs list from the
		// found characters on the production
		bestLog.log("Entered character breakdown");
		// for each character - we open a new offer
		String nameOfCharacterAndDetailsUnder;
		String detailsOfCharacter;
		// int totalNumOfOffersInProduction = 1;

		try {
			String prodDetailsLeftWithTimeRoleAdded = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetailsLeftWithTimeRoleAdded())).getText());
			bestLog.log((new String("prodDetailsLeftWithTimeRoleAdded=")).concat(prodDetailsLeftWithTimeRoleAdded));
			parseProdDetailsLeftWithTimeRoleAdded(parentOffer, prodDetailsLeftWithTimeRoleAdded);
		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return 0;
		}

		try {
			String prodDetialsRight = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetialsRight())).getText());
			parseProdDetialsRight(parentOffer, prodDetialsRight);

			bestLog.log((new String("prodDetailsRight = ")).concat(prodDetialsRight));

		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return 0;
		}

		// begin adding the characters
		Job currentOffer = parentOffer;
		int charNum = 0;
		boolean moreCharsAvil = true;
		// today gym
		while (moreCharsAvil) {

			try {
				String internalAAname = "";
				String internalAAhref = "";
				String internalAAclass = "";
				String nameOfCharacterandDetails = new String(
						driver.findElement(By.xpath(XpathBuilder.tabCharNameAndDetails(charNum))).getText());
				parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacterandDetails);
				// internalAAname =
				// Scapper.scrapAtXpath(XpathBuilder.tabAAname(charNum));
				internalAAname = Scapper.scrapAttributeAtXpath(XpathBuilder.tabAAname(charNum), "name");
				currentOffer.setInternalAAname(internalAAname.substring(5));
				internalAAhref = Scapper.scrapAttributeAtXpath((XpathBuilder.xpInternalAAhref(charNum)), "href");
				currentOffer.setInternalAAhref(internalAAhref);
				internalAAclass = Scapper.scrapAttributeAtXpath(XpathBuilder.tabAAclass(charNum), "class");
				if (!internalAAclass.contains("breakdown-open-add-role")) {
					bestLog.log("Some error - this role isn't open for submittion");
					charNum++;
					continue;
				}

				bestLog.log((new String("NameOfCharacterAndDetailsUnder = ")).concat(nameOfCharacterandDetails));
				offer.readNoticeAA();
				offer.makeDecision();
				if ((offer.getHasBeenSubmitted()) || (!offer.getDecisionSubmit())) {
					bestLog.printDecisionMakingVars(offer);
					continue;
				}
				// today
				bestLog.log("lets submit!");
				driver.findElement(By.xpath(XpathBuilder.xpCharacterLinkInCharactersPage(charNum))).click();
				//driver.findElement(By.xpath("//div[@id='mainContent']/table[2]/tbody/tr/td/a")).click();
				// driver.findElement(By.xpath(tabLocation(rowNum))).click();
				Breath.deepBreath();
				driver.switchTo().window(ManageDriver.getSonWindowHandler(driver.getWindowHandle()));

				//verify
				String url = driver.getCurrentUrl();
				if (!url.contains(currentOffer.getInternalAAname())){
					Logging.slog(new String (("Error: the choosing window didn't open for AA internal role number:" )).concat(currentOffer.getInternalAAname()));
				}
				// choose photo
				 driver.findElement(By.xpath("//td[@id='photo_5002739']/table/tbody/tr/td/a[2]")).click();
				 driver.findElement(By.xpath("xpath=(//input[@name='video_to_use'])[2]")).click();
			 
				 driver.findElement(By.xpath("//input[@id='include_sc_checkbox_id']")).click();

					// write talent notes with currentOffer.getMessage()

				    driver.findElement(By.xpath("//a[@id='add_to_cart']")).click();
				//    driver.findElement(By.xpath("//div[4]/input")).click();
				 
				// write talent notes with currentOffer.getMessage()

				// HIT SUBMIT

				// check if there is another character to be considered in the
				// next row
				if (verifyLocation(XpathBuilder.xpBetaCharacterName(charNum + 1), "")) {
					charNum++;
					moreCharsAvil = true;
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
		return (1 + charNum);
	}

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

	private boolean verifyLocation(String xpathTab, String verifyText) {
		// returns true only if the location of the xpath contains the
		// verifyText
		try {
			String locationTest1 = new String(driver.findElement(By.xpath(xpathTab)).getText());
			if ((locationTest1.contains(verifyText))) {
				return true;
			}
		} catch (Exception e) {
			if (verifyText.length() > 1) {
				// this is an acutal text to be found. more than ""
				bestLog.log("Verify text " + verifyText + " Does NOT appear.");
			}
			return false;
		}
		return false;
	}

	static public boolean networkWorking() {
		// returns true if there is a network connection

		return true;
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
