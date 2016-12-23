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
	public static boolean isCastingNetworks = true;
	private static final String DEFAULT_OUTPUT_FILE_WINDOWS = "C:\\Users\\Administrator\\workspace\\here\\logs\\log_";
	private static final String DEFAULT_OUTPUT_FILE_LINUX = "";
	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\Administrator\\workspace\\here\\Julia\\gecko_driver\\";
	static public boolean seekBackgroundWork;
	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;
	static Breath takeBreath;
	static Logging bestLog;
	static Actor sam;

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
			// initialize Actor Sam - Just here as a debug
			sam = new Actor(1, bestLog, "guykapulnik", "cPassword", "guykapulnik", "aPassword");
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

	public void testBetaAA() throws Throwable {
		bestLog.log("Actors Access");
		testBetaB();
	}

	@Test
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
		log('a');
		driver.get(aaBaseUrl + "/");
		Breath.deepBreath();

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(sam.getAaUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(sam.getAaPassword());
		driver.findElement(By.id("login-btn")).click();

		Breath.deepBreath();
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
		bestLog.log((new String("Region  ")).concat(intToRegion(region)));
		String tag = new String(driver.findElement(By.xpath("//p[@id='breadcrumb']")).getText());

		if (!verifyLocation("//p[@id='breadcrumb']",
				(new String("home / breakdowns / ").concat(intToRegion(region))))) {
			bestLog.log("Can't find region ");
			throw new Exception();
		}
		Breath.deepBreath();
		for (int rowNum = 0; rowNum < 3; rowNum++) {
			log('j');
			bestLog.log("Checking for green star at row number: " + rowNum);
			int trCheckRow = (2 + rowNum);

			String checkPos = ((new String("//div[@id='mainContent']/div[3]/table/tbody/tr["))
					.concat(String.valueOf(trCheckRow))).concat("]/td/img");
			String srcOfImg = "";
			try {
				srcOfImg = new String(driver.findElement(By.xpath(checkPos)).getAttribute("src"));
				if (srcOfImg.contains("/gui/check.gif")) {
					bestLog.log("Check at " + rowNum + " from top.");
					continue;
				}
				bestLog.log("Error.");
				continue;
			} catch (Error e) {
				bestLog.log("offer not submitted - lets try it.");
			}
			offer = new Job();
			handleAAOffer(rowNum);
			if (offerHasBeenConsideredBefore(offer)) {
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
		driver.findElement(By.id("login")).sendKeys(sam.getCnUsername());
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(sam.getCnPassword());
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
				offer = new Job();
				handleBackgroundWorkOffer(seekBackgroundWork, (trStarRow - 1));
				if (offerHasBeenConsideredBefore(offer)) {
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

		String leftPart = (new String("//div[@id='mainContent']/div[5]/table/tbody/tr["))
				.concat(String.valueOf(rowNum + 1));

		try {
			String path = (new String(leftPart)).concat("]/td[2]");
			offer.setOfferPostedTime(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));
		}

		try {
			String path = (new String(leftPart)).concat("]/td[3]/a");
			offer.setOfferProjectName(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferProjectName(new String(""));
		}

		try {
			String path = (new String(leftPart)).concat("]/td[4]");
			offer.setOfferTypeProject(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferTypeProject(new String(""));
		}

		try {
			String path = (new String(leftPart)).concat("]/td[5]");
			offer.setOfferCastingDirector(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferCastingDirector(new String(""));
		}

		try {
			String path = (new String(leftPart)).concat("]/td[6]");
			offer.setOfferShootDate(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferShootDate(new String(""));
		}

		try {
			String path = (new String(leftPart)).concat("]/td[7]");
			offer.setOfferUnionStatus(new String(driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferUnionStatus(new String(""));
		}
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
				bestLog.log("J: Making sure there is no GREEN STAR");
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
			bestLog.log("Verify text " + verifyText + " Does NOT appear");
			return false;
		}

		return false;
	}

	static public boolean networkWorking() {
		// returns true if there is a network connection

		return true;
	}

	static public boolean offerHasBeenConsideredBefore(Job consideredOffer) {
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

	static public String intToRegion(int intRegion) {
		switch ((char) intRegion) {
		case '1':
			return "los angeles";
		case '2':
			return "new york";
		case '3':
			return "vancouver";
		case '4':
			return "chicago";
		case '5':
			return "florida";
		case '6':
			return "toronto";
		case '7':
			return "texas";
		case '8':
			return "hawaii";
		case '9':
			return "southeast";
		default:
			return "";
		}
	}
}
