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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Beta {
	// THIS IS BETA2.4
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
	public static boolean isCastingNetworks = true;
	private static final String DEFAULT_OUTPUT_FILE_WINDOWS = "C:\\Users\\Administrator\\workspace\\here\\Julia\\outlogs\\log_";
	private static final String DEFAULT_OUTPUT_FILE_LINUX = "";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\Administrator\\workspace\\here\\Julia\\gecko_driver\\";

	static public boolean seekBackgroundWork;
	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;
	static Breath takeBreath;
	static Logging bestLog;
	static Actor[] cast;
	static Actor clientCN;

	boolean isTargetRegion[];
	static public int currentShift = 0;

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
			// debug
			// Esl.parseAgeRange(" 45 to 55 Indigenous - Calvin's twin sister.
			// Usually a bit erratic, going off the deep end since the death of
			// their Grandfather.");
			// Esl.isStatisticallyMaleName("MARIEA");

			// cast = new Actor[2];
			// cast[0] = new Actor("10002", "daniellevi", "qvzbchsm",
			// "daniellevi", "password", true, true, 'c');
			// cast[1] = new Actor("10001", "guykapulnik", "cPassword",
			// "guykapulnik", "aPassword", false, true, 'c');
			// initialize Actor Sam - Just here as a debug. Actor ID = "10001"
			// danCN = new Actor("10001", "guykapulnik", "cPassword",
			// "guykapulnik", "aPassword", false, true, 'c',"height: 6'2\n
			// weight:190lb\njacket:42\nneckXsleeve:16.5x35\nwaistXinseam:34x33\nshoe:11
			// ","Thanks!",30,40);
			clientCN = new Actor("10003", "maramccann", "black2act", "", "", false, false, "caucasian", "non-union",
					"Size 6", "Thanks!", 35, 45);

			// dan = new Actor("10002", "daniellevi", "qvzbchsm", "daniellevi",
			// "password");

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
			if (isCastingNetworks) {
				test.testBetaCN();
			} else {
				test.testBetaAA();
			}

		} catch (Exception e) {
			Logging.slog(e.getMessage());
			Logging.slog("Error in program");
		}
		bestLog.log("Program ENDED - THANK YOU!");

	}

	@Before
	public void setUp() throws Exception {

	}

	public void testBetaAA() throws Throwable {
		bestLog.log("Actors Access");
		ManageDriver.logMyIP();
		testBetaB();
	}

	@Test
	public void testBetaCN() throws Throwable {
		bestLog.log("Casting Networks");
		ManageDriver.logMyIP();
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
					loginCN();
				} else {
					// Actors access
					loginAA(currentShift);
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
					coreActorsAccess(currentShift);
					logutAA(currentShift);
				}
			} catch (Exception e) {
				bestLog.log(e.getMessage());
				bestLog.log("Something went wrong -> Back to Login");
				loginCounter++;
			}
		}
	}

	public void logutAA(int shift) throws Throwable {

		Logging.slog((new String("Logging out shift: ")).concat(String.valueOf(shift)));
		return;
	}

	public void loginAA(int shift) throws Throwable {
		aaBaseUrl = "http://actorsaccess.com";
		driver.manage().timeouts().implicitlyWait(Breath.geckoWaitTime, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		bestLog.log("LOGIN-AA");
		Breath.makeZeroSilentCounter();
		// bestLog.Logging.log('a');
		Logging.slog("Window handle Parent " + parentWindowHandler);
		bestLog.log(new String("Logining in username: ").concat(cast[shift].getAaUsername()));
		driver.get(aaBaseUrl + "/");
		Breath.deepBreath();

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(cast[shift].getAaUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(cast[shift].getAaPassword());
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

	public void handleRegion(int region, int shift) throws Throwable {
		String regionUrl = (new String(XpathBuilder.urlAABreakdownAndRegion())).concat(String.valueOf(region));
		driver.get(regionUrl);
		String tag = new String(driver.findElement(By.xpath("//p[@id='breadcrumb']")).getText());
		if (!verifyLocation("//p[@id='breadcrumb']",
				(new String("home / breakdowns / ").concat(intToRegion(region))))) {
			bestLog.log("Can't find region ");
			return;
		}
		bestLog.log((new String("Region ").concat(intToRegion(region))));

		Breath.breath();
		int productionRow = 0;
		boolean nextRowHasAnotherProd = true;

		// we only consider here the first page of productions. So in the future
		// add an option to nagivate to page 2 and 3
		while (nextRowHasAnotherProd) {
			bestLog.log("Checking for red check at row number: " + productionRow);
			try {
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabProductionInRow(productionRow)))) {
					// assertTrue(isElementPresent(By.xpath(XpathBuilder.tabProductionInRow(productionRow))));
					bestLog.log((new String("Found a production at row. So looking for red check on row: ")
							.concat(String.valueOf(productionRow))));
				} else {
					bestLog.log((new String("No production on row: ").concat(String.valueOf(productionRow))));
					nextRowHasAnotherProd = false;
					break;
				}
			} catch (Exception e) {
				bestLog.log((new String("No production on row: ").concat(String.valueOf(productionRow))));
				nextRowHasAnotherProd = false;
				bestLog.log("Error. shouldnlt reach this line. DEBUG");
				break;
			}

			try {
				Breath.breath();
				// make sure that there is another production at productionRow
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabRedCheckBoxPos(productionRow)))) {
					bestLog.log((new String("Red check found on row:").concat(String.valueOf(productionRow))));
					productionRow++;
					continue;
				}
				// assertFalse(isElementPresent(By.xpath(XpathBuilder.tabRedCheckBoxPos(productionRow))));
			} catch (NoSuchElementException e) {
				bestLog.log((new String("Red check found.").concat(String.valueOf(productionRow))));
				productionRow++;
				continue;
			} catch (Exception e) {
				bestLog.log((new String("Element not found.This is wrong ").concat(String.valueOf(productionRow))));
				break;
			}
			bestLog.log((new String("Lets submit. Cause NO red check at row: ").concat(String.valueOf(productionRow))));
			offer = new Job(cast[shift].getActorId());
			// some offers appear in several different regions but reffer to the
			// same role
			offer.setRegion(region);
			Scapper.parseRowOfferAA(offer, productionRow);
			if (offer.offerHasBeenConsideredBeforeAA(Jobs)) {
				productionRow++;
				continue;
			}

			try {
				driver.findElement(By.xpath(XpathBuilder.xpLinkCharactersInProduction(productionRow))).click();
			} catch (Exception e) {
				bestLog.log(
						(new String("Error: the link hasn't opened on row: ").concat(String.valueOf(productionRow))));
				continue;
			}
			Breath.deepBreath();

			try {
				if (ManageDriver.isElementPresent(driver, By.xpath(XpathBuilder.tabCharNameAndDetails(0)))) {
					// assertTrue(isElementPresent(By.xpath(XpathBuilder.tabCharNameAndDetails(0))));

					bestLog.log("Success. We are now in characters table.");
				} else {
					bestLog.log("Error. We are not in the characters chart now. Lets return");
					driver.navigate().back();
				}

			} catch (Exception e) {
				bestLog.log("Error. shouldnlt reach this line. DEBUG");
				driver.navigate().back();
			}
			totalOffersInThisProd(offer);

			//  move back to window with char of productions
			bestLog.log((new String("Number of Characters found in this production: "))
					.concat(String.valueOf(offer.getNumberOfCharactersOnThisProduction())));
			bestLog.log((new String("Number of characters added to the cart: ")
					.concat(String.valueOf(countOffersInCart(Jobs)))));
			Breath.silentCount();
			if (offer.getTotalAddedToCart() > 0) {
				if (submitCart()) {
					bestLog.log((new String("Submitted Cart Succesfully")));
					emptyCart(Jobs);
					// go back to production chart
				} else {
					bestLog.log((new String("Error submittion cart")));
					// go back and make in each of the offers that it was NOT
					// submitted ( although the decision was YES to submit)
					emptyCart(Jobs);
				}
			}
			productionRow++;
			driver.navigate().back();
		} // end of while loop
	}

	public void setTargetRegions() {
		isTargetRegion = new boolean[15];
		for (int i = 0; i < 15; ++i) {
			isTargetRegion[i] = false;
		}

		// take target regions from DB
		isTargetRegion[8] = true;
		isTargetRegion[9] = true;

	}

	public void coreActorsAccess(int shift) throws Throwable {
		// go over the chosen regions and submit to each region
		Breath.makeZeroSilentCounter();
		setTargetRegions();
		while (true) {
			for (int regionNum = 0; regionNum < 15; regionNum++) {
				if (isTargetRegion[regionNum]) {
					handleRegion(regionNum, shift);
					Breath.nap();
				}
			}
		}
	}

	public void loginCN() throws Throwable {
		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		Breath.makeZeroSilentCounter();
		bestLog.log("LOGIN-CN");

		// bestLog.log(new String("Logining in username:
		// ").concat(clientCN.getAaUsername()));
		bestLog.log(new String("Logining in username: ").concat(clientCN.getCnUsername()).concat(", ActorID: ")
				.concat(clientCN.getActorId()));
		seekBackgroundWork = true;
		Logging.slog("A: Window handle Parent " + parentWindowHandler);
		driver.get(cnBaseUrl + "/");
		Breath.deepBreath();
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(clientCN.getCnUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(clientCN.getCnPassword());
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		Breath.breath();
		// debug - this is just for My agent :
		// driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
		// check for welcome window:
		if (!verifyLocation(XpathBuilder.xpCNWelcomeHeader(), "Welcome")) {
			bestLog.log("Can't find Welcome Page");
			return;
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
			// for alternation between principle and BG use : seekBackgroundWork
			// ^= true;
			seekBackgroundWork = true;
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
			if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				Breath.deepBreath();
				if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Extras")) {
					bestLog.log("Can't find Extras chart");
					throw new Exception();
				}
			}
		} else {
			// We want to be in principle chart
			if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				Breath.deepBreath();
				if (!verifyLocation(XpathBuilder.xpCNVerifyProductionsPage(), "Principals")) {
					bestLog.log("Can't find principle chart");
					throw new Exception();
				}

			}
		}
		new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
		Breath.deepBreath();
		for (int rowNum = 0; rowNum < 10; rowNum++) {
			bestLog.log("Checking for green star at row number: " + rowNum);
			int trStarRow = (3 * rowNum);
			trStarRow += 4;
			String starPos = XpathBuilder.xpCNStarPositionBG(trStarRow);
			String srcOfImg = "";
			try {
				srcOfImg = new String(driver.findElement(By.xpath(starPos)).getAttribute("src"));
			} catch (Error e) {
				bestLog.log(e.getMessage());
				verificationErrors.append(e.toString());
			}
			if (srcOfImg.contains("spacer.gif")) {
				bestLog.log("No star on offer " + rowNum + " from top.  Let's try submitting.");
				offer = new Job(clientCN.getActorId());

				Scapper.handleBackgroundWorkOffer(offer, seekBackgroundWork, (trStarRow - 1));
				if (offer.offerHasBeenConsideredBeforeCN(Jobs)) {
					continue;
				}
				Jobs.add(offer);
				// debug
				Breath.silentCount();

				Esl.readNotice(clientCN, offer);
				offer.genderMatchingUpdate(clientCN);
				offer.unionMatchingUpdate(clientCN);
				offer.makeDecisionCN();

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
				driver.findElement(By.cssSelector(XpathBuilder.cssCMSubmitButton())).click();
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

	public boolean submitCart() {
		// goes to cart page and clicks submit
		// verify current page
		try {
			Breath.deepBreath();
			driver.findElement(By.xpath(XpathBuilder.xpCartLogo())).click();
			Breath.deepBreath();
			driver.findElement(By.xpath(XpathBuilder.xpSubmitCart())).click();
			// verify successful submition
			// Notify User/ DB about the submittion.
			return true;
		} catch (Exception e) {
			Logging.slog("Failed submitting cart");
			return false;
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

	private int totalOffersInThisProd(Job parentOffer) {
		// returns the number of offers created and added to Jobs list from the
		// found characters on the production
		bestLog.log("Entered character breakdown");
		// for each character - we open a new offer
		String nameOfCharacterAndDetailsUnder;
		String detailsOfCharacter;

		try {
			String prodDetailsLeftWithTimeRoleAdded = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetailsLeftWithTimeRoleAdded())).getText());
			bestLog.log((new String("prodDetailsLeftWithTimeRoleAdded=")).concat(prodDetailsLeftWithTimeRoleAdded));
			Esl.parseProdDetailsLeftWithTimeRoleAdded(parentOffer, prodDetailsLeftWithTimeRoleAdded);
		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return 0;

		}

		try {
			String prodDetialsRight = new String(
					driver.findElement(By.xpath(XpathBuilder.xpProdDetialsRight())).getText());
			Esl.parseProdDetialsRight(parentOffer, prodDetialsRight);

			bestLog.log((new String("prodDetailsRight = ")).concat(prodDetialsRight));
			Breath.silentCount();
		} catch (Exception e) {
			bestLog.log(e.getMessage());
			return 0;
		}

		// begin adding the characters
		Job currentOffer = parentOffer;
		int charNum = 0;
		boolean moreCharsAvil = true;

		while (moreCharsAvil) {

			try {
				String internalAAname = "";
				String internalAAhref = "";
				String internalAAclass = "";
				String nameOfCharacterandDetails = new String(
						driver.findElement(By.xpath(XpathBuilder.tabCharNameAndDetails(charNum))).getText());
				Esl.parseNameOfCharacterAndDetailsUnder(currentOffer, nameOfCharacterandDetails);
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
				Esl.readNoticeAA(clientCN, currentOffer);
				currentOffer.genderMatchingUpdate(cast[currentShift]);
				currentOffer.ethnicityMatchingUpdate(cast[currentShift]);
				currentOffer.makeDecisionAA();
				if ((currentOffer.getHasBeenSubmitted()) || (!currentOffer.getDecisionSubmit())) {
					bestLog.printDecisionMakingVars(currentOffer);
					charNum++;
					continue;
				}
				Esl.fillTalentNote(clientCN, currentOffer);
				ManageDriver.windowStatus2();
				bestLog.log("lets submit!");
				driver.findElement(By.xpath(XpathBuilder.xpCharacterLinkInCharactersPage(charNum))).click();
				Breath.deepBreath();
				ManageDriver.windowStatus2();

				bestLog.log(driver.getCurrentUrl());
				driver.switchTo().window(ManageDriver.getSonWindowHandler(driver.getWindowHandle()));
				bestLog.log(driver.getCurrentUrl());

				// verify
				String ChoosingPhotoUrl = driver.getCurrentUrl();
				if (!ChoosingPhotoUrl.contains(currentOffer.getInternalAAname())) {
					Logging.slog(new String(("Error: the choosing window didn't open for AA internal role number:"))
							.concat(currentOffer.getInternalAAname()));
					driver.navigate().back();
					charNum++;
					continue;
				}

				choosePhotosAndSubmit(offer);
				driver.switchTo().window(parentWindowHandler);

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
				bestLog.log(e.getMessage());
				break;
			}
		}
		return (1 + charNum);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private void assertiveClicking(String[] optionStrings) {
		// works only with xPath links - sorry!
		for (int i = 0; i < optionStrings.length; ++i) {
			try {
				driver.findElement(By.xpath(optionStrings[i])).click();
				Logging.slog("This Worked!!");
				Logging.slog(optionStrings[i]);

			} catch (Exception e) {
				Logging.slog(optionStrings[i]);
				Logging.slog(e.getMessage());

			}
		}
		driver.switchTo().parentFrame();
	}

	public void choosePhotosAndSubmit(Job currentOffer) {
		try {

			driver.switchTo().defaultContent();
			driver.switchTo().frame("main_window");
			driver.findElement(By.xpath(XpathBuilder.xpChooseMySmilePhoto())).click();
			driver.findElement(By.xpath(XpathBuilder.xpChooseCommercialVideo2())).click();
			// driver.findElement(By.xpath(XpathBuilder.xpChooseBookstoreVideo1())).click();
			driver.findElement(By.xpath(XpathBuilder.xpIncludeSizes())).click();
			if (true) {
				driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA())).clear();
				driver.findElement(By.xpath(XpathBuilder.xpTalentNotesAA())).sendKeys(currentOffer.getMessage());
			}

			driver.switchTo().defaultContent();
			driver.switchTo().frame("buttons");
			driver.findElement(By.xpath(XpathBuilder.xpAddToCartAA())).click();
			currentOffer.setPutInCart();
		} catch (Exception e) {
			bestLog.log("Failed to submit it.");
			bestLog.log(e.getMessage());
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

	public int countOffersInCart(List<Job> allJobs) {
		int inCart = 0;
		for (Job offer : allJobs) {
			if (offer.getPutInCart()) {
				inCart++;
			}
		}
		return inCart;
	}

	public void emptyCart(List<Job> allJobs) {

		for (Job offer : allJobs) {
			offer.takeOutOfCart();
		}

	}

}
