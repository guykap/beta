package FirstBeta;

import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.varia.NullAppender;

public class Logging {
	static private boolean logStateFull;
	public static Appender fh = null;
	public static Logger logger = Logger.getLogger("MyLog");

	public Logging(String fileOut) {
		try {
			logStateFull = true;
			org.apache.log4j.BasicConfigurator.configure(new NullAppender());
			fh = new FileAppender(new SimpleLayout(), fileOut);
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Stopping. BAD LOGGING");
		}
	}

	public boolean getLogState() {
		return logStateFull;
	};

	public void setLogState(boolean newBit) {
		logStateFull = newBit;
	};

	public void log(String newLog) {
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
	
	static public void slog(String newLog) {
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

	 public void printDecisionMakingVars(Job offer) {
		// this would print to log why the decision went down as it did
		this.log("Decision: " + offer.getHasBeenSubmitted() + "|isMale: " + offer.getIsMale() + "|isCar: " + offer.getIsCar()
				+ "|isEthnicity: " + offer.getIsEthnicity() + "|isAge: " + offer.getIsAge()
				+ "|hasBeenSubmitted Before: " + offer.getHasBeenSubmitted());

	}

		public void printSubmittions(List<Job> Jobs) {
			this.log("********ALL SUBMITTIONS********");
			for (Job offer : Jobs) {
				if (offer.getHasBeenSubmitted()) {
					this.printOffer(offer);
				}
			}
			this.log("********END LIST OF SUBMITTIONS********");
		}


		 public void printOffer(Job offer) {
			if (offer == null)
				return;
			this.log("Offer " + offer.getOfferId() + "|Background:" + offer.getIsBackgroundWork() + "|Role added:"
					+ offer.getOfferTimeRoleAdded() + "|Submittion time:" + offer.getOfferSubmittionDateTime()
					+ "|Shoot date:" + offer.getOfferShootDate() + "|age:" + offer.getIsAge() + "|car:" + offer.getIsCar()
					+ "|Eth:" + offer.getIsEthnicity() + "|Male:" + offer.getIsMale() + "|SAG:" + offer.getIsSag()
					+ "|Guard:" + offer.getIsGuard() + "|Tux:" + offer.getNeedTuxedo() + "|Uni:"
					+ offer.getNeedPoiceUniform() + "|Type:" + offer.getOfferTypeProject() + "|ReqSizes:"
					+ offer.getReqSizes() + "|Paying:" + offer.getOfferPaying() + "|Rate:" + offer.getOffertRate()
					+ "|Name:" + offer.getOfferProjectName() + "|Role:" + offer.getOfferRole() + "|Offer Listing:"
					+ offer.getOfferListing() + " |  Talent Notes filled with:" + offer.getMessage());

		}

}
