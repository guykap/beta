package FirstBeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Esl {

	static public void readNoticeAA(Actor human, Job offer) {
		// this reads the notice and sets all the Job params accordingly.
		String allData = new String(offer.offerProductionDetails);
		String allDataLowerCase = new String(allData).toLowerCase();
		String allCharacterData = new String(offer.offerCharacterDetails);
		String allCharacterDataLowerCase = new String(allCharacterData).toLowerCase();

		// SAG
		if ((offer.getOfferUnionStatus()).contains("SAG") || (offer.getOfferUnionStatus()).contains("sag")
				|| (offer.getOfferUnionStatus()).startsWith("UNION")
				|| (offer.getOfferUnionStatus()).startsWith("union")) {
			offer.setIsSag(true);
		}

		if ((allDataLowerCase.contains("\tsag")) || (allDataLowerCase.contains(" sag "))
				|| (allDataLowerCase.startsWith("union")) || (allDataLowerCase.contains("\tunion"))
				|| (allDataLowerCase.contains(" union")) || (allDataLowerCase.startsWith("union"))) {
			offer.setIsSag(true);
		}

		// ETHNICITY
		// if the notice says a specific ethicity that isn't the actor's then
		// mark FALSE. otherwise mark TRUE
		// offer.setSeekingEthnicities(allDataLowerCase);
		Esl.calcEthnicity(offer, allCharacterDataLowerCase);

		// AGE

		Esl.understandingAgeRange(offer, allCharacterDataLowerCase, human);

		// GENDER

		// HINT FOR BOTH MALE OR FEMALE CHARACTER IN DESCRIPTION

		if ((allCharacterDataLowerCase.contains("male or female"))
				|| (allCharacterDataLowerCase.contains("female or male"))
				|| (allCharacterDataLowerCase.toLowerCase().contains("both genders"))) {
			offer.setCharacterGender('b');
			return;
		}

		if (((allCharacterDataLowerCase.contains(" he ")) || (allCharacterDataLowerCase.startsWith("he")))
				&& ((allCharacterDataLowerCase.contains(" she ")) || (allCharacterDataLowerCase.startsWith("she")))) {
			// this notice cointains also He and she so this is confusing. Lets
			// check gender only by name
			checkGenderOfName(offer);
			return;
		}

		// HINT FOR A MALE CHARACTER IN DESCRIPTION

		if ((allCharacterDataLowerCase.contains(" male")) || (allCharacterDataLowerCase.startsWith("male"))
				|| (allCharacterDataLowerCase.contains(" men")) || (allCharacterDataLowerCase.contains(" man "))
				|| (allCharacterDataLowerCase.contains(" male")) || (allCharacterDataLowerCase.startsWith("men"))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" he "))) {
			offer.setCharacterGender('m');
			return;
		}

		// HINT FOR A FEMALE CHARACTER IN DESCRIPTION
		if ((allCharacterDataLowerCase.contains(" female")) || (allCharacterDataLowerCase.contains(" women"))
				|| (allCharacterDataLowerCase.startsWith("female")) || (allCharacterDataLowerCase.contains(" woman "))
				|| (allCharacterDataLowerCase.contains("actress ")) || (allCharacterDataLowerCase.startsWith("women"))
				|| (allCharacterDataLowerCase.startsWith(" she "))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" she "))) {
			offer.setCharacterGender('f');
			return;
		}

		if ((offer.getCharacterGender() == 'm') || (offer.getCharacterGender() == 'f')) {
			// found the hint for male or female. and determined the gender m or
			// f
			// Unknown
			return;
		} else {
			// Hint not found so lets use the API using the character name
			checkGenderOfName(offer);
		}

	}

	static public void parseProdDetailsLeftWithTimeRoleAdded(Job char_offer, String data) {
		Logging.slog("parse it");
		char_offer.addToProductionDetails(data);
		String delims = "['\n']";
		String[] tokens = data.split(delims);
		char_offer.setOfferTimeRoleAdded(tokens[0]);
		char_offer.setOfferTypeProject(tokens[2]);
		char_offer.setOfferUnionStatus(tokens[3]);
		// String details = tokens[1];
	}

	static public void parseProdDetialsRight(Job char_offer, String data) {
		// ALL parsing should be done with REGEX , but right now only store in
		// the DB all the production info as one long String
		Logging.slog("parse it");
		char_offer.addToProductionDetails(data);
	}

	static public void parseNameOfCharacterAndDetailsUnder(Job char_offer, String data) {
		Logging.slog("parse it");
		String delims = "\\[|\\]";
		String[] tokens = data.split(delims);
		String name = new String(tokens[1]);
		char_offer.setOfferCharacterName(name.trim());
		String details = new String(tokens[2]);
		char_offer.setOfferCharacterDetails(details.trim());
	}

	static public String parseAgeRange(String data) {
		String firstNum = "";
		String secondNum = "";
		String mydata = new String(data);
		if (mydata.length() < 0) {
			return new String("");
		}
		Pattern pattern2 = Pattern.compile("\\b\\d{2}\\b[-. ]?[-. ]?[-. ]?\\d{2}");
		Matcher matcher = pattern2.matcher(mydata);

		if (matcher.find()) {
			String foundRegex = matcher.group(0);
			return (new String(foundRegex));
		}
		Pattern pattern1 = Pattern.compile("\\b\\d{2}\\b");

		matcher = pattern1.matcher(mydata);
		if (matcher.find()) {
			firstNum = matcher.group(0);
			if (matcher.find()) {
				secondNum = matcher.group(0);
			} else {
				return (firstNum);
			}

			return ((new String(firstNum).concat("-")).concat(secondNum));
		}

		return ("");
	}

	static private void understandingAgeRange(Job offer, String ageData, Actor human) {

		// read the AGE from data
		if (ageData.length() < 1) {
			// no age info here
			offer.setIsAge(true);
		}
		if ((ageData.contains("20 - 30")) || (ageData.contains("20-30")) || (ageData.contains("20 - 40"))
				|| (ageData.contains("20-40")) || (ageData.contains("20s to 30s")) || (ageData.contains("20s-30s"))
				|| (ageData.contains("early 40s")) || (ageData.contains("early 30s"))
				|| (ageData.contains("30 something "))) {

			offer.setIsAge(true);
		}

		// case the data has the format : " %d - %d"
		String ageMin;
		String ageMax;
		String delims = "[-,'to']";
		String[] tokens = offer.offerListingAgesHint.split(delims);
		String regexResult;
		Double maybeAgeMin = new Double(0);
		Double maybeAgeMax = new Double(0);
		Double maybeAgeAverageTwice = new Double(0);
		Double avgCharacterAgeTwice = new Double(0);

		try {
			regexResult = new String(parseAgeRange(ageData));
			if (regexResult.length() > 3) {
				tokens = regexResult.split(delims);
			}
			if ((regexResult.length() > 1) && (regexResult.length() <= 3)) {
				// Only one number found .
				checkForSpecificActor(human, offer, Double.valueOf(regexResult.trim()),
						Double.valueOf(regexResult.trim()));

			}

			ageMin = new String(tokens[0]);
			ageMax = new String(tokens[1]);

			// Weed out the agent fee percetage. usually 10%, 15%, 20% and is at
			// the end of the String

			if ((ageMin.length() < 1) && (ageMax.length() < 1)) {
				return;
			}

			try {

				maybeAgeMin = new Double(Double.parseDouble(ageMin.trim()));
				maybeAgeMax = new Double(Double.parseDouble(ageMax.trim()));
				// Weed out the agent fee percetage. usually 10%, 15%, 20% and
				// is at the end of the String

				if ((maybeAgeMin > maybeAgeMax) || (maybeAgeMax.equals(new Double(10)))
						|| (maybeAgeMax.equals(new Double(20))) || (maybeAgeMax.equals(new Double(15)))) {

					// we cannot trust the age Max so we will now return
					Logging.slog(
							"Failure in reading the age values. Might be confused with the agent fee. Should improve regex to find The percentage symbol");
					// for debug reasons at this point we will say that the age
					// is NOT appropriate
					offer.setIsAge(false);
					return;
				}

			} catch (Exception e) {
				Logging.slog(e.getMessage());
				Logging.slog("Math failure in reading the age values. Lets submit anyway. We are artists.");
				offer.setIsAge(true);

			}

			checkForSpecificActor(human, offer, maybeAgeMin, maybeAgeMax);

		} catch (Exception e) {
			// System.err.format("Age range - faliure in reading or calculating
			// age. Lets submit anyway.");
			Logging.slog(e.getMessage());
			Logging.slog("Age range - faliure in reading or calculating age. Lets submit anyway.");
			offer.setIsAge(true);
		}
	}

	static Double humanRealAge(int min, int max) {
		Double avg = new Double((min + max));
		avg = avg / 2;
		return avg;
	}

	static Double ageRange(int min, int max) {
		Double median = new Double(max - min);
		median = median / 2;
		return median;
	}

	static void checkForSpecificActor(Actor human, Job offer, Double maybeAgeMin, Double maybeAgeMax) {

		try {
			Double ageRange = new Double(ageRange(human.getHumansMinActingAge(), human.getHumansMaxActingAge()));

			Double ageLookLike = new Double(6);
			Double actorRealAge = new Double(
					humanRealAge(human.getHumansMinActingAge(), human.getHumansMaxActingAge()));

			if (maybeAgeMin.equals(new Double(maybeAgeMax))) {
				// there was probably only one age number found by the regex
				if ((Math.abs(actorRealAge - maybeAgeMin) <= ageLookLike)) {
					offer.setIsAge(true);
					return;
				}
			}
			Double maybeAgeAverageTwice = new Double(maybeAgeMin + maybeAgeMax);
			Double avgCharacterAgeTwice = new Double(human.getHumansMinActingAge() + human.getHumansMaxActingAge());

			// check if actor's age is in the range asked for:

			if ((actorRealAge >= maybeAgeMin) && (actorRealAge <= maybeAgeMax)) {
				offer.setIsAge(true);
			}
			// check if actor's age is near the average
			if ((Math.abs(maybeAgeAverageTwice - avgCharacterAgeTwice)) <= ageRange) {
				// the actor is in the age range
				offer.setIsAge(true);
			}

			if ((Math.abs(actorRealAge - maybeAgeMin) <= ageLookLike)
					|| (Math.abs(actorRealAge - maybeAgeMax) <= ageLookLike)) {
				offer.setIsAge(true);
			}

		} catch (Exception e) {
			Logging.slog(e.getMessage());
		}
	}

	static public void understandUnionStatus(Job offer) {
		String allData = (offer.getOfferRole()).concat(" ").concat(offer.offerListingNotes.toLowerCase());

		if ((offer.getOfferUnionStatus()).contains("sag") || (offer.getOfferUnionStatus()).contains("aftra")) {
			offer.setCharacterUnionDemand('u');
		}

		if ((allData.contains("\tsag")) || (allData.contains(" sag")) || (allData.startsWith("sag"))
				|| (allData.contains("\tunion")) || (allData.contains(" union")) || (allData.startsWith("union"))) {
			offer.setCharacterUnionDemand('u');
		}

		// checking whether the character is open for both union and non union
		if ((offer.getOfferUnionStatus()).contains("Union/Non-Union")
				|| (offer.getOfferUnionStatus()).contains("No Union Affiliation")) {
			offer.setCharacterUnionDemand('b');
		}

	}

	static public void understandingEthnicity(Job offer, Actor human) {
		offer.setIsEthnicityMatch(false);
		if (offer.offerListingEthnicity.contains("all ethnicities")) {
			offer.setIsEthnicityMatch(true);
			return;
		}

		switch (human.getEthinicityChar()) {
		case 'a':
			if (offer.offerListingEthnicity.contains("african american")) {
				offer.setIsEthnicityMatch(true);
				return;
			}
		case 'c':
			if (offer.offerListingEthnicity.contains("caucasian")) {
				offer.setIsEthnicityMatch(true);
				return;
			}
		case 'l':
			if (offer.offerListingEthnicity.contains("latino")) {
				offer.setIsEthnicityMatch(true);
				return;
			}

		}

	}

	static public void understandingGender(Job offer) {
		String allData = (offer.getOfferRole()).concat(" ").concat(offer.offerListingNotes.toLowerCase());
//BOTH MALE AND FEMALE
		if ((offer.offerListingSex).contains(" male or female") || (allData.startsWith("males and females"))) {
			offer.setCharacterGender('b');
		}
		
		
		
		// MALE

		if ((offer.offerListingSex).contains(" male") || (allData.startsWith("male"))) {
			offer.setCharacterGender('m');
		}
		if ((allData.contains(" male")) || (allData.startsWith("male")) || (allData.contains(" men"))
				|| (allData.contains(" man ")) || (allData.startsWith("men"))
				|| (allData.toLowerCase().contains(" male"))) {
			offer.setCharacterGender('m');
		}

		// FEMALE
		if ((offer.offerListingSex).contains(" female") || (allData.startsWith("female"))) {
			offer.setCharacterGender('f');
		}
		if ((allData.contains(" female")) || (allData.startsWith("female")) || (allData.contains(" female "))
				|| (allData.contains("actress ")) || (allData.startsWith("women"))
				|| (allData.toLowerCase().contains(" female"))) {
			offer.setCharacterGender('f');
		}

		// Here is good place to check for a male name here for the character
	}

	static public void readNotice(Actor human, Job offer) {
		// this reads the notice and sets all the Job params accordingly.
		String allData = (new String (offer.getOfferRole())).concat(" ").concat(offer.offerListingNotes.toLowerCase());

		// SAG
		understandUnionStatus(offer);

		// Gender
		understandingGender(offer);

		// AGE
		understandingAgeRange(offer, offer.offerListingAgesHint, human);

		// ETHNICITY
		understandingEthnicity(offer, human);

		// CAR

		if ((allData.contains(" car ")) || (allData.startsWith("car ")) || (allData.contains("w/cars"))
				|| (allData.contains("mercedes")) || (allData.contains("vehicle")) || (allData.contains("bmw"))
				|| (allData.startsWith("car ")) || (allData.contains("cars"))) {
			offer.setIsCar(true);
		}

		// tuxedo
		if ((allData.contains(" tuxido ")) || (allData.contains("own a tux"))) {
			offer.setNeedTuxedo(true);
		}

		if ((allData.contains("cop uniform ")) || (allData.contains("own NYPD uni"))) {
			offer.setNeedPoliceUniform(true);
		}

		// Stand-in
		if ((offer.getOfferListing().contains("stand-in")) || (allData.contains("standing"))
				|| (allData.contains("stand-in")) ||(allData.contains("stand in experience"))) {
			offer.isStandIn = true;

		}

	}

	static public void fillTalentNote(Actor human, Job offer) {
		try {
			String allData = (offer.getOfferRole()).concat(" ").concat((offer.getOfferListing()).toLowerCase());

			// last time worked
			if ((allData.contains(" note last ")) || (allData.contains("please note if you have worked"))
					|| (allData.contains("worked on the")) || (allData.contains("must not have worked on this project"))
					|| (allData.contains("last time that you worked"))
					|| (allData.contains("do not submit if you have worked on this show"))) {
				offer.addToMessage("I've never worked on the production.");
			}

			if ((allData.contains("note your sizes")) || (allData.contains("note all sizes"))
					|| (allData.contains("note neck"))) {
				offer.addToMessage(human.getHumansSizes());
			}

			if ((allData.contains(" Please note if you can provide")) || (allData.contains("must own"))
					|| (allData.contains("own the wardrobe"))) {
				offer.addToMessage("I own the wardrobe.");
			}

			// tuxedo
			if (offer.getNeedTuxedo()) {
				offer.addToMessage("I own the tuxedo.");
			}

			// improveMessage(offer);
			if (!(offer.getMessage().length() < 5)) {
				offer.addToMessage(human.getDefaultTalentNote());
			}

		} catch (Exception e) {
			Logging.slog("Failed to fill talent note");
			offer.setMessage("");

		}
	}

	static public void improveMessage(Job offer) {
		// checks the length and if message is empty, then add the basic message
		/*
		 * if (offer.getMessage().length() < 5) { offer.
		 * setMessage("I would like to be considered for this production.\nThank you,\nGuy Kapulnik"
		 * ); }
		 */
		if (!(offer.getMessage().length() < 5)) {
			offer.addToMessage("Thanks");
		}
	}

	static public boolean checkGenderOfName(Job currentOffer) {

		try {
			String characterName = currentOffer.getOfferCharacterName();
			String myKey = ""; // "insert your server key here";

			URL url = new URL("https://gender-api.com/get?key=" + myKey + "&name=" + characterName);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error: " + conn.getResponseCode());

			}

			InputStreamReader input = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(input);

			Gson gson = new Gson();
			JsonObject json = gson.fromJson(reader, JsonObject.class);
			String gender = json.get("gender").getAsString();
			if ((gender).contains("male")) {
				currentOffer.setCharacterGender('m');
			} else if ((gender).contains("female")) {
				currentOffer.setCharacterGender('f');
			} else {
				currentOffer.setCharacterGender('u');
			}

			Logging.slog(new String("Character:").concat(characterName).concat(" : gender : ").concat(gender)); // Gender:
																												// male
			conn.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	static public void calcEthnicity(Job currentOffer, String data) {
		// lighting up all the bits ethnicity that appear in the data String
		if (data.contains("african american")) {
			currentOffer.setSeekingEthnicities("african american");
		}
		if (data.contains("african-american")) {
			currentOffer.setSeekingEthnicities("african american");
		}
		if (data.contains("caucasian")) {
			currentOffer.setSeekingEthnicities("caucasian");
		}

		// GROUP EASIANS
		if (data.contains("asian")) {
			currentOffer.setSeekingEthnicities("asian");
		}
		if (data.contains("korean")) {
			currentOffer.setSeekingEthnicities("asian");
		}
		if (data.contains("latin")) {
			currentOffer.setSeekingEthnicities("latin");
		}

		// GROUP MIDDLE EASTERNS
		if (data.contains("middle eastern")) {
			currentOffer.setSeekingEthnicities("middle eastern");
		}
		if (data.contains("arab")) {
			currentOffer.setSeekingEthnicities("middle eastern");
		}

		if (data.contains("all ethnicities")) {
			currentOffer.setSeekingEthnicities("all ethnicities");
		}

		if (!currentOffer.atLeastOneEthnicityChosen()) {
			Logging.slog("No Ethinicity hint appears in the notice - so assuming they seek ALL ETHNICITIES");
			currentOffer.setSeekingEthnicities("all ethnicities");
		}

	}
}
