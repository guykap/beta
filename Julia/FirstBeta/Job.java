package FirstBeta;
import java.io.BufferedReader;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class Job {

	static String filename = "C:\\temp\\code\\castingNetworks\\test_file1";
	static int dailyMinPay = 30;
	static int avgCharacterAge = 30;

	// PRIVATE:
	String offerId;
	boolean offerHasBeenSubmitted;
	String notice;
	String noticeLowerCase;
	String currentOffer;

	String offerRole;
	String offerProjectName;
	String offerShootDate;
	String offerTypeProject;
	String offerRate;
	String offerPaying;
	String offerCastingDirector;
	String offerUnionStatus;
	String offerPostedTime;
	String offerListing;
	String offerListingFirst;
	String offerListingSex = "";
	String offerListingEthnicity = "";
	String offerListingNotes = "";
	String offerListingAgesHint = "";
	String offerTimeRoleAdded ="";
	String offerSubmittionDateTime = "";
	String log; // this logs all the process of this specific offer from login

	boolean isSag;
	boolean isEthnicity;
	boolean isAge;
	boolean isMale;
	boolean isBackgroundWork;
	boolean isPayingEnough;
	boolean isMaleName;
	boolean isCar;
	boolean isGuard;
	boolean isStandIn;
	boolean reqSizes;
	boolean needTuxedo;
	boolean needPoliceUniform;
	int grade;
	String message;
	boolean decisionSubmit;

	public Job() {
		// String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		// this.offerId = new String (Timestamp(System.currentTimeMillis()));
		this.offerId = new String((new Long(System.currentTimeMillis())).toString());
	}

	public Job(String newNotice) {
		notice = new String(newNotice);
		// this age temp for this test version
	}

	public String getOfferId() {
		return offerId;
	};

	public void setOfferId(String newData) {
		offerId = newData;
	};

	public String getOfferRole() {
		return offerRole;
	};

	public void setOfferRole(String newData) {
		offerRole = newData;
	};

	public String getOfferProjectName() {
		return offerProjectName;
	};

	public void setOfferProjectName(String newData) {
		offerProjectName = newData;
	};

	public String getOfferShootDate() {
		return offerShootDate;
	};

	public void setOfferShootDate(String newData) {
		offerShootDate = newData;
	};

	public String getOfferTypeProject() {
		return offerTypeProject;
	};

	public void setOfferTypeProject(String newData) {
		offerTypeProject = newData;
	};

	public String getOffertRate() {
		return offerRate;
	};

	public void setOffertRate(String newData) {
		offerRate = newData;
	};

	public String getOfferPaying() {
		return offerPaying;
	};

	public void setOfferPaying(String newData) {
		offerPaying = newData;
	};
	
	public String getOfferCastingDirector() {
		return offerCastingDirector;
	};

	public void setOfferCastingDirector(String newData) {
		offerCastingDirector = newData;
	};
	
	public String getOfferUnionStatus() {
		return offerUnionStatus;
	};

	public void setOfferUnionStatus(String newData) {
		offerUnionStatus = newData;
	};

	public String getOfferPostedTime() {
		return offerPostedTime;
	};

	public void setOfferPostedTime(String newData) {
		offerPostedTime = newData;
	};

	public String getOfferListing() {
		return offerListing;
	};
	
	public void setOfferTimeRoleAdded(String newData) {
		offerTimeRoleAdded = newData;
	};

	public String getOfferTimeRoleAdded() {
		return offerTimeRoleAdded;
	};

	
	public void setOfferSubmittionDateTime(String newData) {
		offerSubmittionDateTime = newData;
	};

	public String getOfferSubmittionDateTime() {
		return offerSubmittionDateTime;
	};

		
	
	public void setOfferListing(String newData) {
		offerListing = newData;
		String delims = "[/,\n]";
		String[] tokens = newData.split(delims);
		offerListingFirst = new String(tokens[0]);
		offerListingSex = new String(tokens[1]);
		offerListingEthnicity = new String(tokens[2]);
		offerListingAgesHint = new String(tokens[3]);
		for (int i = 4; i < tokens.length; i++) {
			offerListingNotes += new String(tokens[i]);
		}
	};

	public String getNotice() {
		return notice;
	};

	public void setNotice(String newNotice) {
		notice = newNotice;
	};

	public String getLog() {
		return log;
	};

	public void setLog(String newNotice) {
		log = new String(newNotice);
	};

	public void addToLog(String newNotice) {
		// log += DateTime();
		// log += new Date();
		log += (new String(newNotice)).concat("\n");
	};

	public boolean getIsSag() {
		return isSag;
	};

	public void setIsSag(boolean newBit) {
		isSag = newBit;
	};

	public boolean getIsEthnicity() {
		return isEthnicity;
	};

	public void setIsEthnicity(boolean newBit) {
		isEthnicity = newBit;
	};

	public boolean getIsAge() {
		return isAge;
	};

	public void setIsAge(boolean newBit) {
		isAge = newBit;
	};

	public boolean getIsMale() {
		return isMale;
	};

	public void setIsMale(boolean newBit) {
		isMale = newBit;
	};

	public boolean getIsBackgroundWork() {
		return isBackgroundWork;
	};

	public void setIsBackgroundWork(boolean newBit) {
		isBackgroundWork = newBit;
	};

	public boolean getIsCar() {
		return isCar;
	};

	public void setIsCar(boolean newBit) {
		isCar = newBit;
	};

	public boolean getIsGuard() {
		return isGuard;
	};

	public void setIsGuard(boolean newBit) {
		isGuard = newBit;
	};

	
	public boolean getReqSizes() {
		return reqSizes;
	};

	public void setReqSizes(boolean newBit) {
		reqSizes = newBit;
	};

	public boolean getNeedTuxedo() {
		return needTuxedo;
	};

	public void setNeedTuxedo(boolean newBit) {
		needTuxedo = newBit;
	};

	public boolean getNeedPoiceUniform() {
		return needPoliceUniform;
	};

	public void setNeedPoliceUniform(boolean newBit) {
		needPoliceUniform = newBit;
	};

	public int getGrade() {
		return grade;
	};

	public void setGrade(int newBit) {
		grade = newBit;
	};

	public String getMessage() {
		return message;
	};

	public void setMessage(String newMessage) {
		message = newMessage;
	};

	public void addToMessage(String newMessage) {
		message += newMessage;
	};

	public boolean getDecisionSubmit() {
		return decisionSubmit;
	};

	public void setDecisionSubmit(boolean newBit) {
		decisionSubmit = newBit;
	};

	public boolean getHasBeenSubmitted() {
		return offerHasBeenSubmitted;
	};

	public void setHasBeenSubmitted(boolean newBit) {
		offerHasBeenSubmitted = newBit;
	};

	
	
	public void readNotice() {
		// this reads the notice and sets all the Job params accordingly.

		// SAG
		if((this.getOfferUnionStatus()).contains("SAG") || (this.getOfferUnionStatus()).contains("AFTRA")){
			setIsSag(true);
		}
		String noticeLowerCase = new String ((this.notice).toLowerCase());
		noticeLowerCase += new String ((this.offerListingNotes).toLowerCase());
		if ((noticeLowerCase.contains("\tsag")) || (noticeLowerCase.contains(" sag"))
				|| (noticeLowerCase.startsWith("sag")) || (noticeLowerCase.contains("\tunion"))
				|| (noticeLowerCase.contains(" union")) || (noticeLowerCase.startsWith("union"))) {
			setIsSag(true);
		}

		// MALE
		if((this.getOfferListing().contains("Male"))){
			setIsMale(true);
		}
		if ((noticeLowerCase.contains(" male")) || (noticeLowerCase.startsWith("male"))
				|| (noticeLowerCase.contains(" men")) || (noticeLowerCase.contains(" man "))
				|| (noticeLowerCase.contains("actor ")) || (noticeLowerCase.startsWith("men"))
				|| (offerListingSex.toLowerCase().contains(" male"))) {
			setIsMale(true);
		}

		// There is a male name here for the character

		
		// this is a paying job and is NOT a student project
		if (((offerPaying.toLowerCase()).contains("yes"))&&(!getOfferTypeProject().contains("Student"))) {
			isPayingEnough = true;

			// check the daily rate and make sure it is above dailyMinPay
			// find the rate by analyzing String offerRate
		}
		// setIsMaleName(true);

		// ETHNICITY
		if ((offerListingEthnicity.contains("all ethnicities")) || (offerListingEthnicity.contains("caucasian"))) {
			setIsEthnicity(true);
		}

		// CAR

		if ((noticeLowerCase.contains(" car ")) || (noticeLowerCase.startsWith("car "))
				|| (noticeLowerCase.contains("w/cars")) || (noticeLowerCase.contains("mercedes"))
				|| (noticeLowerCase.contains("vehicle")) || (noticeLowerCase.contains("bmw"))
				|| (noticeLowerCase.startsWith("car ")) || (noticeLowerCase.contains("cars"))) {
			setIsCar(true);
		}

		// AGE

		calcAgeRange(offerListingAgesHint);

		// tuxedo
		if ((noticeLowerCase.contains(" tuxido ")) || (noticeLowerCase.contains("own a tux"))) {
			setNeedTuxedo(true);
		}

		if ((noticeLowerCase.contains("cop uniform ")) || (noticeLowerCase.contains("own NYPD uni"))) {
			setNeedPoliceUniform(true);
		}
	}

	public void calcAgeRange(String ageData) {
		// read the AGE from data
		if (ageData.length() < 1) {
			// no age info here
			setIsAge(true);
		}
		if ((ageData.contains("20 - 30")) || (ageData.contains("20-30")) || (ageData.contains("20 - 40"))
				|| (ageData.contains("20-40")) || (ageData.contains("20s to 30s")) || (ageData.contains("20s-30s"))
				|| (ageData.contains("early 30s")) || (ageData.contains("30 something "))) {
			setIsAge(true);
		}

		// case the data has the format : " 20 - 30"
		String ageMin;
		String ageMax;
		String delims = "[-,'to']";
		String[] tokens = offerListingAgesHint.split(delims);
		try {
			ageMin = new String(tokens[0]);
			ageMax = new String(tokens[1]);
			Double maybeAgeMin = new Double(Double.parseDouble(ageMin.trim()));
			Double maybeAgeMax = new Double(Double.parseDouble(ageMax.trim()));
			Double maybeAgeAverageTwice = new Double(maybeAgeMin + maybeAgeMax);
			Double avgCharacterAgeTwice = new Double(avgCharacterAge * 2);
			Double ageRange = new Double(10);

			// check if actor's age is near the average
			if (Math.abs((maybeAgeAverageTwice - avgCharacterAgeTwice)) <= ageRange) {
				// the actor is in the age range
				setIsAge(true);
			} else {
				// age of actor out of range
				setIsAge(false);
			}

		} catch (Exception e) {
			System.err.format("Age range - faliure in reading or calculating age");
			setIsAge(false);
		}
	}

	public void makeDecision() {
	//DEBUGGING
		
		System.out.println("DEBUG DECISION - ALL VALUES: " +this.getOfferProjectName() + " | " + this.getOfferSubmittionDateTime() + " | "
				+ this.getOffertRate() + " | " + this.getOfferTypeProject() + " | "
				+ this.getHasBeenSubmitted() + " | " + this.getOfferListing() + "Talent Notes :" + this.getMessage()
				+ " |SAG: " + this.getIsSag()
				+ " |AGE: " + this.getIsAge()
				+ " |CAR: " + this.getIsCar()
				+ " |PAying: " + this.getOfferPaying()
				);
		
		
		// if ((isSag)&&(isAge)&&(isMale)&&(!isCar)&&(isPayingEnough)){
		 
		this.setDecisionSubmit(true);
		if ((isMale)&&(!isCar)&&(isPayingEnough)){
		this.setDecisionSubmit(true);}
		// }
	}

	public void fillTalentNote() {
		// last time worked
		if ((noticeLowerCase.contains(" note last ")) || (noticeLowerCase.contains("please note if you have worked"))
				|| (noticeLowerCase.contains("worked on the"))
				|| (noticeLowerCase.contains("must not have worked on this project"))
				|| (noticeLowerCase.contains("last time that you worked"))
				|| (noticeLowerCase.contains("do not submit if you have worked on this show"))) {
			this.addToMessage("I've never worked on the production.");
		}

		if ((noticeLowerCase.contains("note your sizes")) || (noticeLowerCase.contains("note all sizes"))
				|| (noticeLowerCase.contains("note neck"))) {
			this.addToMessage("height: 6'2\n weight:200\njacket:42\nneckXsleeve:16.5x35\nwaistXinseam:34x33\nshoe:11 ");
		}

		if ((noticeLowerCase.contains(" Please note if you can provide"))
				|| (noticeLowerCase.contains("must own"))
				|| (noticeLowerCase.contains("own the wardrobe"))) {
			this.addToMessage("I own the wardrobe.");
		}

		
		 
		// tuxedo
		if ((noticeLowerCase.contains(" tuxido ")) || (noticeLowerCase.contains("own a tux"))) {
			this.addToMessage("I own the tuxedo.");
		}

		this.improveMessage();
	}

	public void improveMessage() {
		// checks the length and if empty - then add the basic message
		if (getMessage().length() < 5) {
			setMessage("I would like to be considered for this production.\nThank you,\nGuy Kapulnik");
		}
		// add the Thanks! Guy ending
		if (!(getMessage().contains("Guy"))) {
			addToMessage("Thanks,\nGuy");
		}
	}

	public void loadNoticesFromFile() {
		// read file

		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
			return;
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
			return;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public boolean sameJob(Job otherJob) {
		// returns true if the two Jobs have the same first 15 chars notice.
		if ((this.notice).contains(otherJob.getNotice().subSequence(1, 100))) {
			return true;
		}
		return false;
	}
}
