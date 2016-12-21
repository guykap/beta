package FirstBeta;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.varia.NullAppender;
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
	// THIS IS BETA1.7
	private static WebDriver driver;
	static public String cnBaseUrl;
	static public String aaBaseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	static private List<Job> Jobs = new ArrayList<Job>();
	static Iterator<Job> jobIterator = Jobs.iterator();
	private Job offer;
	private String BetaLog;
	int[] passedOnOptionArray = new int[] { -1, -1 };
	int[] currentOnOptionArray = new int[] { 0, 0 };
	static boolean useSleep;
	int leftNumOfLoginWhileLoopsChances = 0;
	int leftNumOfSubmittionWhileLoopsChances = 0;
	public static int silentCounter = 0;
	String parentWindowHandler;
	String newWindowHandler;
	Iterator<String> windowHandlesIterator;
	Set<String> handles;
	private static final String DEFAULT_OUTPUT_FILE_WINDOWS = "C:\\Users\\Administrator\\workspace\\here\\logs\\log_";
	private static final String DEFAULT_OUTPUT_FILE_LINUX = "";

	private static final String DEFAULT_GECKO_DRIVER_LIBRARY = "C:\\Users\\Administrator\\workspace\\here\\Julia\\gecko_driver\\";

	static private boolean logStateFull;
	public static Appender fh = null;
	public static Logger logger = Logger.getLogger("MyLog");
	static public boolean seekBackgroundWork;
	static public boolean longNaps = false;
	static String gecko_driver_path;
	static int loginCounter;

	public static void main(String[] args) {
		// SETUP BASIC
		seekBackgroundWork = true;
		useSleep = true;
		JUnitCore jCore;
		// SETUP LOG
		String fileOut;
		if (args.length > 0) {
			fileOut = new String(args[0]);
		} else {
			fileOut = new String(DEFAULT_OUTPUT_FILE_WINDOWS);
		}
		String appendixFileName = (new String((new Long(System.currentTimeMillis())).toString())).concat(".txt");
		logStateFull = true;
		try {
			org.apache.log4j.BasicConfigurator.configure(new NullAppender());
			fh = new FileAppender(new SimpleLayout(), (new String(fileOut).concat(appendixFileName)));
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
		} catch (Exception e) {
			log("File Appender error. Farewell");
			return;
		}
		// SETUP GECKO DRIVER
		if (args.length > 1) {
			gecko_driver_path = (new String(args[1])).concat("\\\\");
		} else {
			gecko_driver_path = new String(DEFAULT_GECKO_DRIVER_LIBRARY);
		}
		System.setProperty("webdriver.gecko.driver", (new String(gecko_driver_path)).concat("geckodriver.exe"));

		// START
		jCore = new JUnitCore();
		jCore.run(Beta.class);
		log("Program ENDED - THANK YOU!");

	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testBetaB() throws Throwable {
		log("beta better");
		driver = new FirefoxDriver();
		loginCounter = 1;
		while (networkWorking()) {
			log("Login number " + loginCounter);
			if (loginCounter > 10) {
				log("THIS IS 10TH LOGIN - stopping Beta ");
				return;
			}
			if ((loginCounter % 3) == 0) {
				log("THIS IS a 3rd LOGIN - THEN CLOSE WINDOW and start new Driver ");
				killFirefoxAndOpenNew();
			}
			try {
				loginCN();
				seekBackgroundWork = true;
			} catch (Exception e) {
				log("Something went during login -> So lets login again");
				loginCounter++;
				continue;
			}

			try {
				core();
			} catch (Exception e) {
				log("Something went wrong -> Back to Login");
				loginCounter++;
			}
		}
	}

	public void loginCN() throws Throwable {
		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		silentCounter = 0;
		log("LOGIN-CN");
		seekBackgroundWork = true;
		log('a');
		driver.get(cnBaseUrl + "/");
		breath();
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("guykapulnik");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("cGuy1234567");
		driver.findElement(By.xpath("//input[@id='submit']")).click();
		breath();
		driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
		// check for welcome window:
		if (!verifyLocation("//div[@id='maininfo']/h2", "Welcome")) {
			log("Can't find Welcome ");
			throw new Exception();
		}
		log('c');
		breath();
	}

	public void core() throws Throwable {
		log("Core");
		// first time in coreLoop - always begin with Extra chart
		driver.findElement(By.xpath("//a[@id='_ctl0_cphBody_lnkExtrasRoles']")).click();
		if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
			log("Can't find Extras chart");
			throw new Exception();
		}
		log("In Extras chart");
		while (true) {
			heartLoop();
			seekBackgroundWork ^= true;
			if (seekBackgroundWork) {
				log("ALTERNATE to BACKGROUND work");
			} else {
				log("ALTERNATE to PRINCIPLE work");
			}
			nap();
		}
	}

	public void heartLoop() throws Throwable {
		breath();
		if (seekBackgroundWork) {
			if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				deepBreath();
				if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
					log("Can't find Extras chart");
					throw new Exception();
				}
			}
		} else {
			// We want to be in principle chart
			if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Principals")) {
				driver.findElement(By.xpath("//div[@id='DirectCastMainDiv']/table/tbody/tr[2]/td/table/tbody/tr/td/a"))
						.click();
				// debug
				deepBreath();
				if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Principals")) {
					log("Can't find principle chart");
					throw new Exception();
				}

			}
		}
		new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
		deepBreath();
		for (int rowNum = 0; rowNum < 10; rowNum++) {
			log('j');
			log("Checking for green star at row number: " + rowNum);
			int trStarRow = (3 * rowNum);
			trStarRow += 4;
			String starPos = ((new String("//div[@id='DirectCastMainDiv']/table/tbody/tr["))
					.concat(String.valueOf(trStarRow))).concat("]/td/span/img");
			String srcOfImg = "";
			try {
				srcOfImg = new String(driver.findElement(By.xpath(starPos)).getAttribute("src"));
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}

			if (srcOfImg.contains("spacer.gif")) {
				log("No star on offer " + rowNum + " from top.  So going to submit it.");
				offer = new Job();
				handleBackgroundWorkOffer(seekBackgroundWork, (trStarRow - 1));
				if (offerHasBeenConsideredBefore(offer)) {
					continue;
				}
				Jobs.add(offer);
				// debug
				staySilent();

				log('h');
				int trLinkToOfferRow = -1;
				trLinkToOfferRow = trStarRow - 1;
				String linkOfferPos = ((new String("//tr[")).concat(String.valueOf(trLinkToOfferRow))).concat("]/td/a");
				driver.findElement(By.xpath(linkOfferPos)).click();
				deepBreath();
				driver.switchTo().window(getSonWindowHandler());
				windowStatus();
				// add time of apperance to offer
				try {
					breath();
					offer.setOfferTimeRoleAdded(
							new String(driver.findElement(By.xpath("//table[5]/tbody/tr[3]/td")).getText()));
				} catch (Exception e) {
					offer.setOfferTimeRoleAdded(new String(""));
				}
				offer.readNotice();
				offer.makeDecision();
				// log("Decision: " + offer.getDecisionSubmit());
				if ((offer.getHasBeenSubmitted()) || (!offer.getDecisionSubmit())) {
					// DO NOT SUBMIT THIS OFFER
					continue;
				}
				driver.findElement(By.xpath("//a[contains(text(),'submit')]")).click();
				// deepBreath();
				breathToMissleadThem();
				if (!verifyLocation("//span", "Customize your submission")) {
					log("Error: You are on wrong window");
					windowStatus();
					throw new Exception();
				}
				log('l');
				driver.findElement(By.id("TALENTNOTE")).clear();
				choosePhoto();

				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				// log("filled talent notes with : " + offer.getMessage());
				deepBreath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				deepBreath();
				if (!verifyLocation("//span", "Submission Successful")) {
					log("Did NOT recieve final submittion successful");
					windowStatus();
					throw new Exception();
				}
				if (!killSubWindowAndMoveToParentWindow()) {
					log("Memory leak error: failed killing child window");
					throw new Exception();
				}
				offer.setHasBeenSubmitted(true);
				silentCounter = 0;
				offer.setLog("");
				log('m');
				printSubmittions();
			} else {
				log("Found star on the offer " + rowNum + " from top");

				// debug
				staySilent();
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

	@Test
	public void testBeta() throws Exception {

		// SETUP

		cnBaseUrl = "http://home.castingnetworks.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		parentWindowHandler = driver.getWindowHandle();
		BetaLog = new String("New Beta");

		// REAL Beta
		leftNumOfSubmittionWhileLoopsChances = 0;
		leftNumOfLoginWhileLoopsChances = 0;
		log('a');
		leftNumOfLoginWhileLoopsChances = 0;
		while ((leftNumOfLoginWhileLoopsChances++) < 3) {
			log('b');
			driver.get(cnBaseUrl + "/");
			breath();
			driver.findElement(By.id("login")).click();
			driver.findElement(By.id("login")).clear();
			driver.findElement(By.id("login")).sendKeys("guykapulnik");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("cGuy1234567");
			driver.findElement(By.xpath("//input[@id='submit']")).click();
			breath();
			driver.findElement(By.id("_ctl0_cphBody_rptProfiles__ctl1_lnkViewProfile2")).click();
			// check for welcome window:
			if (!verifyLocation("//div[@id='maininfo']/h2", "Welcome")) {
				continue;
			}

			log('c');
			breath();

			try {
				if (seekBackgroundWork) {

					if (!assertiveClicking(0,
							new String[] { "//a[@id='_ctl0_cphBody_lnkExtrasRoles']",
									"//a[contains(text(),'new Extras roles')]",
									"//li[@id='_ctl0_cphBody_liDirectCastExtras']/a",
									"//a[contains(@href, '../DirectCast/Roles.aspx?rt=xc1')]",
									"//div[2]/div/div/div/ul/li[3]/a" })) {
						break;
					}

					log('d');

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2", "Casting Billboard")) {
						log('e');
					}

					if (!verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
						continue;
					}
				} else {// PRINCIPLE ROLES
					if (!assertiveClicking(0,
							new String[] { "//a[@id='_ctl0_cphBody_lnkPrincipalsRoles']",
									"//a[contains(text(),'new Principals roles')]",
									"//li[@id='_ctl0_cphBody_liDirectCastPrincipals']/a",
									"//div[2]/div/div/div/ul/li[2]/a" })) {
						break;
					}

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h2", "Casting Billboard")) {
						log('e');
					}

					if (verifyLocation("//div[@id='DirectCastMainDiv']/table/tbody/tr/td/h3", "Extras")) {
						continue;
					}

				}
			} catch (Exception e) {
				log("Didn't work");
				// go back to login page
				continue;
			}

			log('f');
			// end login while loop
			break;
		}
		while (leftNumOfSubmittionWhileLoopsChances++ < 3) {
			log('g');
			// Choose from drop down list 'all roles':
			try {
				log("Refresh page");
				try {
					driver.navigate().refresh();
				} catch (Exception e) {
				}
				offer = new Job();
				// offer.setIsBackgroundWork(seekBackgroundWork);
				new Select(driver.findElement(By.name("viewfilter"))).selectByVisibleText("All Roles");
				deepBreath();
				// handleBackgroundWorkOffer(seekBackgroundWork);
				Jobs.add(offer);
				log('h');
				// offer.readNotice();
				offer.makeDecision();
				log("Decision: " + offer.getDecisionSubmit());
				if ((!offer.getDecisionSubmit()) || (offer.getHasBeenSubmitted())) {
					// DO NOT SUBMIT THIS OFFER
					continue;
				}
				log('i');
				deepBreath();
				driver.findElement(By.xpath("//tr[3]/td/a")).click();
				deepBreath();
				windowStatus();
				driver.switchTo().window(getSonWindowHandler());
				windowStatus();
				/*
				 * if (!moveToOtherWindow()) { // restart continue; }
				 */

			} catch (Exception e) {
				log("Didn't work");

				// move back to parent and go back to G point
				killSubWindowAndMoveToParentWindow();
				continue;
			}
			try {
				log('j');

				deepBreath();
				try {
					if (seekBackgroundWork) {
						assertTrue(isElementPresent(By.xpath("//table[6]/tbody/tr/td/img")));
					} else {
						// seeking Principle
						assertTrue(isElementPresent(By.xpath("//table[5]/tbody/tr/td/img")));
					}
					// the green button is there SO :
					log("This project saved as  " + offer.getOfferId() + " has been submitted before.");
					offer = null;
					if (!killSubWindowAndMoveToParentWindow()) {
						log("Memory leak error: failed killing child window");
						break;
					}
					// refresh page to allow new offers to be displayed
					nap();
					log("REFRESH PAGE");
					driver.navigate().refresh();

					continue;
				} catch (Error e) {
					verificationErrors.append(e.toString());
					// no green button
				}

				deepBreath();
				if (!assertiveClicking(1,
						new String[] { "//a[contains(text(),'submit')]", "//table[6]/tbody/tr/td/a" })) {
					break;
				}

				log('k');
				windowStatus();
				// succece opening to photos page
				deepBreath();
				if (!verifyLocation("//span", "Customize your submission")) {
					log("Error: You are on wrong window");
					windowStatus();
					continue;
				}

				log('l');
				deepBreath();
				driver.findElement(By.id("TALENTNOTE")).clear();
				driver.findElement(By.id("TALENTNOTE")).sendKeys(offer.getMessage());
				log("filled talent notes with : " + offer.getMessage());
				deepBreath();
				driver.findElement(By.cssSelector("div > table > tbody > tr > td > a > img")).click();
				deepBreath();
				// verify that the confirmation window opened
				windowStatus();
				windowStatus2();
				if (!verifyLocation("//span", "Submission Successful")) {
					log("Did NOT recieve final submittion successful");
					windowStatus();
					continue;
				}
				if (!killSubWindowAndMoveToParentWindow()) {
					log("Memory leak error: failed killing child window");
					break;
				}
				offer.setHasBeenSubmitted(true);
				offer.setLog(BetaLog);
				log('m');

			} catch (Exception e) {
				log("Clicking submit failed on triel");
			}
		}
		log('z');

	}

	private void handleAAWorkOffer() {

		String currentOffer;

		String currentOfferPostedTime;
		String currentOfferPostedHour;
		String currentOfferProjectName;
		String currentOfferTypeProject;
		String currentOfferCastingDirector;
		String currentOfferStartDate;
		String currentOfferUnionStatus;
		String currentOfferListing;
		/******/
		String currentOfferRole;
		String currentOfferShootDate;
		String currentOffertRate;
		String currentOfferPaying;

		try {
			offer.setOfferPostedTime(new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[2]")).getText()));
		} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));
		}

		try {
			offer.setOfferProjectName(new String(driver
					.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[3]/a")).getText()));
		} catch (Exception e) {
			offer.setOfferProjectName(new String(""));
		}

		try {
			offer.setOfferTypeProject(new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[4]")).getText()));
		} catch (Exception e) {
			offer.setOfferTypeProject(new String(""));
		}

		try {
			offer.setOfferCastingDirector(new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[5]")).getText()));
		} catch (Exception e) {
			offer.setOfferCastingDirector(new String(""));
		}
		try {
			offer.setOfferShootDate(new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[6]")).getText()));
		} catch (Exception e) {
			offer.setOfferShootDate(new String(""));
		}
		try {
			offer.setOfferUnionStatus(new String(
					driver.findElement(By.xpath("//div[@id='mainContent']/div[5]/table/tbody/tr[2]/td[7]")).getText()));
		} catch (Exception e) {
			offer.setOfferUnionStatus(new String(""));
		}

		// findout if there is a star on the left

	}

	private void handleBackgroundWorkOffer(boolean isBackgroundWork, int row) {

		offer.setIsBackgroundWork(isBackgroundWork);
		// the EXTRA table has the shooting date .
		// the PRINCIPLE table does not

		String currentOffer;
		String currentOfferRole;
		String currentOfferProjectName;
		String currentOfferShootDate;
		String currentOfferTypeProject;
		String currentOffertRate;
		String currentOfferPaying;
		String currentOfferUnionStatus;
		String currentOfferPostedDate;
		String currentOfferListing;
		String leftPart = (new String("//tr[")).concat(String.valueOf(row));
		try {

			try {
				// String localRow = String.valueOf(row);
				String path = new String(leftPart.concat("]/td/a"));
				currentOffer = new String(driver.findElement(By.xpath(path)).getText());
			} catch (Exception e) {
				currentOffer = new String("");
			}

			currentOfferRole = new String(currentOffer);

			if (isBackgroundWork) {
				// BACKGROUND WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					currentOfferProjectName = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferProjectName = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					currentOfferShootDate = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferShootDate = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					currentOfferTypeProject = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferTypeProject = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					currentOffertRate = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOffertRate = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					currentOfferPaying = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferPaying = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					currentOfferUnionStatus = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferUnionStatus = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[8]/a"));
					currentOfferPostedDate = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferPostedDate = new String("");
				}

			} else {
				// PRINCIPLE WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					currentOfferProjectName = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferProjectName = new String("");
				}

				try {
					currentOfferShootDate = "";
				} catch (Exception e) {
					currentOfferShootDate = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					currentOfferTypeProject = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferTypeProject = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					currentOffertRate = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOffertRate = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					currentOfferPaying = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferPaying = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					currentOfferUnionStatus = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferUnionStatus = new String("");
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					currentOfferPostedDate = new String(driver.findElement(By.xpath(path)).getText());
				} catch (Exception e) {
					currentOfferPostedDate = new String("");
				}

			}
			try {
				// ((new String("//tr[")).concat(String.valueOf(row+1)))
				String pathOfferListing = new String(
						((new String("//tr[")).concat(String.valueOf(row + 1))).concat("]/td"));
				currentOfferListing = new String(driver.findElement(By.xpath(pathOfferListing)).getText());
			} catch (Exception e) {
				currentOfferListing = new String("");
			}

			// enter into JOB class

			offer.setOfferRole(currentOffer.toLowerCase());
			offer.setOfferProjectName(currentOfferProjectName.toLowerCase());
			offer.setOfferShootDate(currentOfferShootDate.toLowerCase());
			offer.setOfferTypeProject(currentOfferTypeProject.toLowerCase());
			offer.setOffertRate(currentOffertRate.toLowerCase());
			offer.setOfferPaying(currentOfferPaying.toLowerCase());
			offer.setOfferUnionStatus(currentOfferUnionStatus.toLowerCase());
			offer.setOfferPostedTime(currentOfferPostedDate.toLowerCase());
			offer.setOfferListing(currentOfferListing.toLowerCase());
			return;

		} catch (Exception e) {
			log("Error grabbing the current offer data into the Strings");
			// go back to login page

		}

		// enter into MySQL

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

	static public void log(String newLog) {
		if (newLog.length() < 1) {
			return;
		}
		System.out.println(newLog);
		try {

			logger.info(newLog);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public void log(char stage) {
		// each char is assigned a stage in the process - so the log will write
		// the whole string out
		// state - Full log - outputs the whole string
		// state - min log - outputs only the letter representing the stage
		if (logStateFull) {
			switch (stage) {
			case 'a':
				Beta.log("A: Window handle Parent " + parentWindowHandler);
				break;
			case 'b':
				Beta.log("B: Start Login num " + leftNumOfLoginWhileLoopsChances);
				break;
			case 'c':
				Beta.log("C: Location->Home Page");
				break;
			case 'd':
				if (seekBackgroundWork) {
					Beta.log("D: First BG triel worked on " + passedOnOptionArray[0]);
				} else {
					Beta.log("D: First PRINCIPLE triel worked on " + passedOnOptionArray[0]);
				}
				break;
			case 'e':
				Beta.log("E: Location->Casting Billboard");
				break;
			case 'f':
				Beta.log("F: Succ opening Casing Billboards and Extras link");
				break;
			case 'g':
				if (seekBackgroundWork) {
					Beta.log("G: BACKGROUND work Start submittion while loop num "
							+ leftNumOfSubmittionWhileLoopsChances);
				} else {
					Beta.log("G: PRINCIPLE work Start submittion while loop num "
							+ leftNumOfSubmittionWhileLoopsChances);
				}

				break;
			case 'h':
				Beta.log("H: Succ adding offer to Jobs list");
				break;
			case 'i':
				Beta.log("I: Begin submittion for top offer id " + offer.getOfferId() + " : " + offer.getOfferRole());
				break;
			case 'j':
				Beta.log("J: Making sure there is no GREEN STAR");
				break;
			case 'k':
				Beta.log("K: Second triel worked on " + passedOnOptionArray[1]);
				break;
			case 'l':
				Beta.log("L: Succ on openning window to choose photo and fill talent notes.");
				break;
			case 'm':
				log("*******SUBMITTED:");
				Beta.log("M: Succ Submitted: " + offer.getOfferProjectName() + " | "
						+ offer.getOfferSubmittionDateTime() + " | " + offer.getOffertRate() + " | "
						+ offer.getOfferTypeProject() + " | " + offer.getHasBeenSubmitted() + " | "
						+ offer.getOfferListing() + "Talent Notes :" + offer.getMessage());
				break;
			case 'y':
				Beta.log("Parent: " + getParentWindowHandler() + " Son: " + getSonWindowHandler());
				break;
			case 'z':
				Beta.log("Z: Stopping");
				break;
			}
		} else {
			Beta.log(Character.toString(stage));
		}
	}

	private boolean moveToOtherWindow() {
		windowStatus();
		String currentWindowHandler = driver.getWindowHandle();
		handles = driver.getWindowHandles(); // get all window handles
		if (handles.size() < 2) {
			log("Error: there is only one window : " + currentWindowHandler);
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
		log("killed window and returned to  " + newWindowHandler);
		windowStatus();
		return true;
	}

	private void windowStatus() {
		String currentWindowHandler = driver.getWindowHandle();
		String sonWindow = getSonWindowHandler();
		String pointing;
		if (getParentWindowHandler().equals(currentWindowHandler)) {
			log('y');
			log("Now on PARENT");
		} else {
			log('y');
			log("Now on SON");
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
		log(allHandles + " on: " + currentWindowHandler);
	}

	private String getParentWindowHandler() {
		if (parentWindowHandler.length() > 1) {
			return parentWindowHandler;
		}
		log("Error finding Parent holder");
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
			log("Error there are 3 windows!");
			windowStatus2();
			return ("");
		}

		log("Error finding SON");
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
			log("Verify text " + verifyText + " Does NOT appear");
			return false;
		}

		return false;
	}

	private boolean assertiveClicking(int numOfTriel, String[] optionStrings) {
		{// works only with xPath links - sorry!
			if (optionStrings.length < 1) {
				log("No option strings");
				return false;
			}

			if (currentOnOptionArray[numOfTriel] > optionStrings.length) {
				log("Fatal Error: Tried all the options! We might be on the wrong page");
				windowStatus();
				windowStatus2();
				return false;
			}

			try {
				if (passedOnOptionArray[numOfTriel] == (-1)) {
					// trying an option

					// must chekc there is no green star
					driver.findElement(By.xpath(optionStrings[currentOnOptionArray[numOfTriel]])).click();
					passedOnOptionArray[numOfTriel] = currentOnOptionArray[numOfTriel];
				} else {
					// used the option that passed
					driver.findElement(By.xpath(optionStrings[passedOnOptionArray[numOfTriel]])).click();
				}
				return true;
			} catch (Exception e) {
				log("Option " + optionStrings[numOfTriel] + " didn't work.");
				currentOnOptionArray[numOfTriel] = currentOnOptionArray[numOfTriel] + 1;
				log("Lets try option num " + currentOnOptionArray[numOfTriel]);
				return false;
			}
		}

	}

	public void breath() throws InterruptedException {
		// sleeps for the configured time + impro
		int sleepTime = randInt(4, 5);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
			if (logStateFull) {
				log(".");

			}
		}
	}

	public void deepBreath() throws InterruptedException {

		for (int i = 0; i < 3; i++) {
			breath();
		}
	}

	public void nap() throws InterruptedException {

		if (longNaps) {
			log("Zzzzzzzzzz");
			int sleepTime = randInt(180, 300);
			TimeUnit.SECONDS.sleep(sleepTime);
		} else {
			// short naps
			log("Zzz");
			if (useSleep) {
				TimeUnit.SECONDS.sleep(60);
			}
		}
	}

	public void breathToMissleadThem() throws InterruptedException {
		int sleepTime = randInt(30, 60);
		log((new String("Ha Ha ")).concat(String.valueOf(sleepTime)));
		TimeUnit.SECONDS.sleep(sleepTime);
	}

	private void staySilent() throws InterruptedException {
		log("Silent counter : " + silentCounter);
		silentCounter++;
		if (silentCounter > 100) {
			log("Shshshshsh we are trying to sleep here");
			// extend the nap time
			longNaps = true;
		} else {
			longNaps = false;
		}
	}

	static public boolean networkWorking() {
		// returns true if there is a network connection

		return true;
	}

	static public void printOffer(Job offer) {
		if (offer == null)
			return;
		log("Offer " + offer.getOfferId() + "|Background:" + offer.getIsBackgroundWork() + "|Role added:"
				+ offer.getOfferTimeRoleAdded() + "|Submittion time:" + offer.getOfferSubmittionDateTime()
				+ "|Shoot date:" + offer.getOfferShootDate() + "|age:" + offer.getIsAge() + "|car:" + offer.getIsCar()
				+ "|Eth:" + offer.getIsEthnicity() + "|Male:" + offer.getIsMale() + "|SAG:" + offer.getIsSag()
				+ "|Guard:" + offer.getIsGuard() + "|Tux:" + offer.getNeedTuxedo() + "|Uni:"
				+ offer.getNeedPoiceUniform() + "|Type:" + offer.getOfferTypeProject() + "|ReqSizes:"
				+ offer.getReqSizes() + "|Paying:" + offer.getOfferPaying() + "|Rate:" + offer.getOffertRate()
				+ "|Name:" + offer.getOfferProjectName() + "|Role:" + offer.getOfferRole() + "|Offer Listing:"
				+ offer.getOfferListing());

	}

	static public void printSubmittions() {
		log("********ALL SUBMITTIONS********");
		for (Job offer : Jobs) {
			if (offer.getHasBeenSubmitted()) {
				printOffer(offer);
			}
		}
		log("********END LIST OF SUBMITTIONS********");
	}

	static public boolean offerHasBeenConsideredBefore(Job consideredOffer) {
		// checkcing in the list of Jobs for another offer with the same ROLE
		// and same PROJECT NAME values.
		for (Job offer : Jobs) {
			if ((consideredOffer.getOfferProjectName()).equals(offer.getOfferProjectName())) {
				log("same project name: " + (consideredOffer.getOfferProjectName()));
			}
			if ((consideredOffer.getOfferRole()).equals(offer.getOfferRole())) {
				log("same role:" + consideredOffer.getOfferRole());
			}
			if (((consideredOffer.getOfferProjectName()).equals(offer.getOfferProjectName()))
					&& ((consideredOffer.getOfferRole()).equals(offer.getOfferRole()))
					&& (!offer.getHasBeenSubmitted())) {
				log("Found that this Project and role has already been considered and decided NOT to submit. This is Why: ");
				printDecisionMakingVars(offer);
				return true;
			}
		}
		return false;
	}

	static public void printDecisionMakingVars(Job offer) {
		// this would print to log why the decision went down as it did
		log("Decision: " + offer.getHasBeenSubmitted() + "|isMale: " + offer.getIsMale() + "|isCar: " + offer.getIsCar()
				+ "|isEthnicity: " + offer.getIsEthnicity() + "|isAge: " + offer.getIsAge()+ "|hasBeenSubmitted Before: " + offer.getHasBeenSubmitted());

	}

}
