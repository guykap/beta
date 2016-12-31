package FirstBeta;

public class Esl {

	static public void readNoticeAA(Job offer) {
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

		// MALE
		// check if first name of character is a male name - API using

		if ((allCharacterDataLowerCase).contains(" male") || (allCharacterDataLowerCase.startsWith("male"))) {
			offer.setIsMale(true);
		}
		if ((allCharacterDataLowerCase.contains(" male")) || (allCharacterDataLowerCase.startsWith("male"))
				|| (allCharacterDataLowerCase.contains(" men")) || (allCharacterDataLowerCase.contains(" man "))
				|| (allCharacterDataLowerCase.contains("actor ")) || (allCharacterDataLowerCase.startsWith("men"))
				|| (allCharacterDataLowerCase.toLowerCase().contains(" male"))) {
			offer.setIsMale(true);
		}

		// ETHNICITY
		if ((allCharacterDataLowerCase.contains("all ethnicities"))
				|| (allCharacterDataLowerCase.contains("caucasian"))) {
			offer.setIsEthnicity(true);
		}

		// AGE

		Esl.calcAgeRange(offer, allCharacterData);
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

	static private void calcAgeRange(Job offer, String ageData) {
		// read the AGE from data
		if (ageData.length() < 1) {
			// no age info here
			offer.setIsAge(true);
		}
		if ((ageData.contains("20 - 30")) || (ageData.contains("20-30")) || (ageData.contains("20 - 40"))
				|| (ageData.contains("20-40")) || (ageData.contains("20s to 30s")) || (ageData.contains("20s-30s"))
				|| (ageData.contains("early 30s")) || (ageData.contains("30 something "))) {
			offer.setIsAge(true);
		}

		// case the data has the format : " 20 - 30"
		String ageMin;
		String ageMax;
		String delims = "[-,'to']";
		String[] tokens = offer.offerListingAgesHint.split(delims);
		try {

			ageMin = new String(tokens[0]);
			ageMax = new String(tokens[1]);
			if ((ageMin.length() < 1) && (ageMax.length() < 1)) {
				return;
			}
			Double maybeAgeMin = new Double(Double.parseDouble(ageMin.trim()));
			Double maybeAgeMax = new Double(Double.parseDouble(ageMax.trim()));
			Double maybeAgeAverageTwice = new Double(maybeAgeMin + maybeAgeMax);
			Double avgCharacterAgeTwice = new Double(Job.avgCharacterAge * 2);
			Double ageRange = new Double(10);
			Double ageLookLike = new Double(5);
			Double actorRealAge = new Double(36);

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
			// System.err.format("Age range - faliure in reading or calculating
			// age. Lets submit anyway.");
			Logging.slog(e.getMessage());
			Logging.slog("Age range - faliure in reading or calculating age. Lets submit anyway.");
			offer.setIsAge(true);
		}
	}

	static public void readNotice(Job offer) {
		// this reads the notice and sets all the Job params accordingly.

		// String notesLowerCase = (new
		// String(offerListingNotes.toLowerCase())).concat(" ");
		// String allData = (this.getOfferRole()).concat(notesLowerCase);
		String allData = (offer.getOfferRole()).concat(" ").concat(offer.offerListingNotes.toLowerCase());

		// SAG
		if ((offer.getOfferUnionStatus()).contains("sag") || (offer.getOfferUnionStatus()).contains("aftra")) {
			offer.setIsSag(true);
		}

		if ((allData.contains("\tsag")) || (allData.contains(" sag")) || (allData.startsWith("sag"))
				|| (allData.contains("\tunion")) || (allData.contains(" union")) || (allData.startsWith("union"))) {
			offer.setIsSag(true);
		}

		// MALE

		if ((offer.offerListingSex).contains(" male") || (allData.startsWith("male"))) {
			offer.setIsMale(true);
		}
		if ((allData.contains(" male")) || (allData.startsWith("male")) || (allData.contains(" men"))
				|| (allData.contains(" man ")) || (allData.contains("actor ")) || (allData.startsWith("men"))
				|| (allData.toLowerCase().contains(" male"))) {
			offer.setIsMale(true);
		}

		// There is a male name here for the character

		// ETHNICITY
		if ((offer.offerListingEthnicity.contains("all ethnicities"))
				|| (offer.offerListingEthnicity.contains("caucasian"))) {
			offer.setIsEthnicity(true);
		}

		// CAR

		if ((allData.contains(" car ")) || (allData.startsWith("car ")) || (allData.contains("w/cars"))
				|| (allData.contains("mercedes")) || (allData.contains("vehicle")) || (allData.contains("bmw"))
				|| (allData.startsWith("car ")) || (allData.contains("cars"))) {
			offer.setIsCar(true);
		}

		// AGE

		calcAgeRange(offer, offer.offerListingAgesHint);

		// tuxedo
		if ((allData.contains(" tuxido ")) || (allData.contains("own a tux"))) {
			offer.setNeedTuxedo(true);
		}

		if ((allData.contains("cop uniform ")) || (allData.contains("own NYPD uni"))) {
			offer.setNeedPoliceUniform(true);
		}

		// Stand-in
		if ((allData.contains(" stand-in ")) || (allData.contains("standing"))
				|| (allData.contains("stand in experience"))) {
			offer.isStandIn = true;

		}

	}

	static public void fillTalentNote(Job offer) {

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
			offer.addToMessage(
					"height: 6'2\n weight:190lb\njacket:42\nneckXsleeve:16.5x35\nwaistXinseam:34x33\nshoe:11 ");
		}

		if ((allData.contains(" Please note if you can provide")) || (allData.contains("must own"))
				|| (allData.contains("own the wardrobe"))) {
			offer.addToMessage("I own the wardrobe.");
		}

		// tuxedo
		if (offer.getNeedTuxedo()) {
			offer.addToMessage("I own the tuxedo.");
		}

		improveMessage(offer);
	}

	static public void improveMessage(Job offer) {
		// checks the length and if message is empty, then add the basic message
		if (offer.getMessage().length() < 5) {
			offer.setMessage("I would like to be considered for this production.\nThank you,\nGuy Kapulnik");
		}
		// add the Thanks! Guy ending
		if (!(offer.getMessage().contains("Guy"))) {
			offer.addToMessage("Thanks,\nGuy");
		}
	}

}
