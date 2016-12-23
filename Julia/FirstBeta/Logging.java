package FirstBeta;

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
	

	 public void printDecisionMakingVars(Job offer) {
		// this would print to log why the decision went down as it did
		this.log("Decision: " + offer.getHasBeenSubmitted() + "|isMale: " + offer.getIsMale() + "|isCar: " + offer.getIsCar()
				+ "|isEthnicity: " + offer.getIsEthnicity() + "|isAge: " + offer.getIsAge()
				+ "|hasBeenSubmitted Before: " + offer.getHasBeenSubmitted());

	}


}
