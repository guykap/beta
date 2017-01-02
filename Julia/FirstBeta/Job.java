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
	public static int SIZE_OF_ETHINICITIES_BUS = 26;

	// PRIVATE:
	String offerId;
	String actorIDSubmitted;
	boolean offerHasBeenSubmitted;
	String notice = "";
	String noticeLowerCase;
	String currentOffer;

	String offerRole;
	String offerCharacterName = "";
	String offerCharacterDetails = "";
	String offerProjectName;
	String offerShootDate;
	String offerTypeProject;
	String offerRate;
	String offerPaying;
	String offerCastingDirector;
	String offerAssociateCastingDirector;
	String offerAssistant;
	String offerProductionStartDate;
	String offerUnionStatus;
	String offerLocation;
	String offerPostedTime;
	String offerListing;
	String offerListingFirst;
	String offerListingSex = "";
	String offerListingEthnicity = "";
	String offerListingNotes = "";
	String offerListingAgesHint = "";
	String offerTimeRoleAdded = "";
	String offerSubmittionDateTime = "";
	String message = "";
	String offerSubmittionElectronically;
	String offerProductionDetails = "";
	String internalAAname;
	String internalAAhref;
	boolean[] seekingEthnicities;

	boolean isSag;
	boolean isEthnicityMatch;
	boolean isAge;
	boolean isBackgroundWork;
	boolean isPayingEnough;
	boolean isCar;
	boolean isGuard;
	boolean isStandIn;
	boolean reqSizes;
	boolean needTuxedo;
	boolean needPoliceUniform;
	boolean decisionSubmit;
	int numberOfCharactersOnThisProduction;
	int region;
	int totalAddedToCart;
	boolean putInCart;
	boolean isGenderMatch;
	private char characterGender;

	public Job() {
		// String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		// this.offerId = new String (Timestamp(System.currentTimeMillis()));
		this.offerId = new String((new Long(System.currentTimeMillis())).toString());
	}

	public Job(String actorIdSubmitted) {
		this.actorIDSubmitted = new String(actorIdSubmitted);
		this.offerId = new String((new Long(System.currentTimeMillis())).toString());
		this.setCharacterGender('u');
		this.seekingEthnicities = new boolean[SIZE_OF_ETHINICITIES_BUS]; // each
																			// bit
																			// in
																			// the
																			// alphabetical
																			// order
																			// represents
																			// an
																			// ethnicity
		// this age temp for this test version
	}

	public Job(Job sameProductionOffer) {
		// Copy Constructor . That returns a Job with the same Production
		// details left , Production Details right , and actor_Id
		this.setActorIDSubmitted(sameProductionOffer.getActorIDSubmitted());
		this.setOfferPostedTime(sameProductionOffer.getOfferPostedTime());
		this.addToProductionDetails(sameProductionOffer.getProductionDetails());
		this.setOfferUnionStatus(sameProductionOffer.getOfferUnionStatus());
		this.setOfferTypeProject(sameProductionOffer.getOfferTypeProject());
		this.setOfferTimeRoleAdded(sameProductionOffer.getOfferTimeRoleAdded());
		this.setOfferShootDate(sameProductionOffer.getOfferShootDate());
		this.setOfferTimeRoleAdded(sameProductionOffer.getOfferTimeRoleAdded());
		this.setOfferPostedTime(sameProductionOffer.getOfferPostedTime());
		this.setOfferCastingDirector(sameProductionOffer.getOfferCastingDirector());
		this.setRegion(sameProductionOffer.getRegion());

	}

	public String getOfferId() {
		return offerId;
	};

	public void setOfferId(String newData) {
		offerId = newData;
	};

	public String getActorIDSubmitted() {
		return actorIDSubmitted;
	};

	public void setActorIDSubmitted(String newData) {
		actorIDSubmitted = newData;
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

	public String getOfferCharacterName() {
		return offerCharacterName;
	};

	public void setOfferCharacterName(String data) {
		offerCharacterName = data;
	};

	public String getOfferCharacterDetails() {
		return offerCharacterDetails;
	};

	public void setOfferCharacterDetails(String data) {
		offerCharacterDetails = data;
	};

	public String getNotice() {
		return notice;
	};

	public void setNotice(String newNotice) {
		notice = newNotice;
	};

	public boolean getIsSag() {
		return isSag;
	};

	public void setIsSag(boolean newBit) {
		isSag = newBit;
	};

	public boolean getIsEthnicityMatch() {
		return isEthnicityMatch;
	};

	public void setIsEthnicityMatch(boolean newBit) {
		isEthnicityMatch = newBit;
	};

	public boolean getIsAge() {
		return isAge;
	};

	public void setIsAge(boolean newBit) {
		isAge = newBit;
	};

	public boolean getIsGenderMatch() {
		return isGenderMatch;
	};

	public void setIsGenderMatch(boolean newBit) {
		isGenderMatch = newBit;
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

	public String getMessage() {
		return message;
	};

	public void setMessage(String newMessage) {
		message = newMessage;
	};

	public String getProductionDetails() {
		return offerProductionDetails;
	};

	public void addToProductionDetails(String data) {
		offerProductionDetails += (new String(data)).concat(" ");
	}

	public String getInternalAAhref() {
		return message;
	};

	public void setInternalAAhref(String newMessage) {
		internalAAhref = newMessage;
	};

	public String getInternalAAname() {
		return internalAAname;
	};

	public void setInternalAAname(String newMessage) {
		internalAAname = newMessage;
	};

	public void addToMessage(String newMessage) {
		message += new String(message.concat(" ").concat(newMessage));
	};

	public boolean getDecisionSubmit() {
		return decisionSubmit;
	};

	public void setDecisionSubmit(boolean newBit) {
		decisionSubmit = newBit;
	};

	public boolean getPutInCart() {
		return putInCart;
	};

	public void setPutInCart() {
		putInCart = true;
	};

	public void takeOutOfCart() {
		putInCart = false;
	};

	/*
	 * public boolean getIsMaleCharacter() { return isMaleCharacter; };
	 * 
	 * public void setIsMaleCharacter(boolean newBit) { isMaleCharacter =
	 * newBit; }
	 */
	public boolean getHasBeenSubmitted() {
		return offerHasBeenSubmitted;
	};

	public void setHasBeenSubmitted(boolean newBit) {
		offerHasBeenSubmitted = newBit;
	};

	public int getNumberOfCharactersOnThisProduction() {
		return numberOfCharactersOnThisProduction;
	};

	public void setNumberOfCharactersOnThisProduction(int newBit) {
		numberOfCharactersOnThisProduction = newBit;
	};

	public int getRegion() {
		return region;
	};

	public void setRegion(int newBit) {
		region = newBit;
	};

	public int getTotalAddedToCart() {
		return totalAddedToCart;
	};

	public void setTotalAddedToCart(int newBit) {
		totalAddedToCart = newBit;
	};

	public char getCharacterGender() {
		return characterGender;
	};

	public void setCharacterGender(char gender) {
		switch (gender) {
		case 'm':
			this.characterGender = 'm';
			return;
		case 'f':
			this.characterGender = 'f';
			return;
		case 'b':
			this.characterGender = 'b';
			return;

		}
		characterGender = 'u';
	};

	public void makeDecisionAA() {
		// this.setDecisionSubmit(true);

		// DECISION PARAMS
		if ((this.getIsGenderMatch()) && (!this.getIsCar()) && (this.getIsEthnicityMatch()) && (this.getIsAge())) {
			this.setDecisionSubmit(true);
		}
	}

	public void makeDecisionCN() {
		// this.setDecisionSubmit(true);

		// DECISION PARAMS
		if ((this.getIsGenderMatch()) && (!this.getIsCar()) && (this.getIsEthnicityMatch()) && (this.getIsAge())) {
			this.setDecisionSubmit(true);
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

	public boolean isSameOfferCN(Job otherJob) {
		if (((this.getOfferProjectName()).equals(otherJob.getOfferProjectName()))
				&& ((this.getOfferRole()).equals(otherJob.getOfferRole()))) {
			return true;
		}
		return false;
	}

	public boolean isSameOfferAA(Job otherJob) {
		if (((this.getOfferPostedTime()).equals(otherJob.getOfferProjectName()))
				&& ((this.getOfferPostedTime()).equals(otherJob.getOfferRole()))) {
			return true;
		}

		return false;
	}

	public boolean offerHasBeenConsideredBeforeCN(List<Job> allJobs) {
		// checkcing in the list of Jobs for another offer with the same ROLE
		// and same PROJECT NAME values.

		for (Job offer : allJobs) {

			if (((this.getOfferProjectName()).equals(offer.getOfferProjectName()))
					&& ((this.getOfferRole()).equals(offer.getOfferRole())) && (!offer.getHasBeenSubmitted())
					&& ((this.getActorIDSubmitted()).contains(offer.getActorIDSubmitted()))) {
				Logging.slog(
						"Found that this Project and role has already been considered for this actor and decided NOT to submit. This is Why: ");
				Logging.sprintDecisionMakingVars(this);
				return true;
			}
		}
		return false;
	}

	public boolean offerHasBeenConsideredBeforeAA(List<Job> allJobs) {
		// checkcing in the list of Jobs for another offer with the PROJECT NAME
		// values and same ACTOR ID values.
		for (Job offer : allJobs) {
			if (((this.getOfferProjectName()).equals(offer.getOfferProjectName())) && (!offer.getHasBeenSubmitted())
					&& ((this.getActorIDSubmitted()).contains(offer.getActorIDSubmitted()))) {
				Logging.slog(
						"Found that this Project has already been considered and decided NOT to submit. This is Why: ");
				Logging.sprintDecisionMakingVars(offer);
				return true;
			}
		}
		return false;
	}

	public void genderMatchingUpdate(Actor human) {
		this.setIsGenderMatch(false);
		switch (this.getCharacterGender()) {
		case 'm':
			if (human.getGenderIsMale()) {
				this.setIsGenderMatch(true);
			}
			return;
		case 'f':
			if (!human.getGenderIsMale()) {
				this.setIsGenderMatch(true);
			}
			return;
		case 'b':
			this.setIsGenderMatch(true);
			return;
		case 'u':
			// HERE we must decide upon the user profile settings. Should we
			// submit anyway?
			// For debug reasons : now we will say that Unknown gender IS a
			// match
			this.setIsGenderMatch(false);
		}
	}

	public void ethnicityMatchingUpdate(Actor human) {
		
		if (this.seekingEthnicities[posOfChar('z')]) {
			Logging.slog("Welcoming all ethnicities.");
			this.setIsEthnicityMatch(true);
			return;
		}

		if (!this.atLeastOneEthnicityChosen()) {
			Logging.slog("Error - No ethnicity chosen for offer. So we will choose ALL ETHNICITIES");
			this.seekingEthnicities[posOfChar('z')] = true;
			this.setIsEthnicityMatch(true);
			return;
		}

		this.setIsEthnicityMatch(false);

		if (((human.getEthinicity()).contains("african american")) && (this.seekingEthnicities[posOfChar('a')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("caucasian")) && (this.seekingEthnicities[posOfChar('c')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).equals("asian")) && (this.seekingEthnicities[posOfChar('s')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("indian")) && (this.seekingEthnicities[posOfChar('i')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("latin")) && (this.seekingEthnicities[posOfChar('l')])) {
			this.setIsEthnicityMatch(true);
		}

		if (((human.getEthinicity()).contains("middle eastern")) && (this.seekingEthnicities[posOfChar('m')])) {
			this.setIsEthnicityMatch(true);
		}

		

	}

	public void setSeekingEthnicities(String data) {
		if (data.contains("african american")) {
			this.seekingEthnicities[posOfChar('a')] = true;
		}
		if (data.contains("caucasian")) {
			this.seekingEthnicities[posOfChar('c')] = true;
		}
		if (data.contains("asian")) {
			this.seekingEthnicities[posOfChar('s')] = true;
		}
		if (data.contains("indian")) {
			this.seekingEthnicities[posOfChar('i')] = true;
		}
		if (data.contains("latin")) {
			this.seekingEthnicities[posOfChar('l')] = true;
		}
		if (data.contains("middle eastern")) {
			this.seekingEthnicities[posOfChar('m')] = true;
		}
		if (data.contains("all ethnicities")) {
			this.seekingEthnicities[posOfChar('z')] = true;
		}
	}

	private int posOfChar(char data) {
		int bitPos= (( (int)data)-((int)'a'));
		return (bitPos);
	}

	public boolean atLeastOneEthnicityChosen() {
		boolean atLeastOneEthinicityChosen = false;
		for (int i = 0; i < SIZE_OF_ETHINICITIES_BUS; ++i) {
			if ((this.seekingEthnicities[i]) == true) {
				atLeastOneEthinicityChosen = true;
			}
		}

		return atLeastOneEthinicityChosen;

	}
}
