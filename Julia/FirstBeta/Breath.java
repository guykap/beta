package FirstBeta;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Breath {
	static public int geckoWaitTime = 15;
	static private boolean useSleep = true;
	static private boolean logStateFull = true;
	static private boolean longNaps;
	static private int silentCounter=0;
	static private Logging ourLogging;
	
	
	Breath() {
		 
	}

	Breath(boolean currUseSleep, boolean currLogStateFull) {
		 
	}
	
	static private int getSilentCounter() {
		return silentCounter;
	};

	static private void setSilentCounter(int count) {
		silentCounter = count;
	};

	static public void makeZeroSilentCounter(){
		setSilentCounter(0);
	}
	
	
	static public void init(Logging usedLogging){
		ourLogging = usedLogging;
	}
	static public void breath() throws InterruptedException {
		// sleeps for the configured time + impro
		int sleepTime = randInt(6, 8);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
			if (logStateFull) {
				
				ourLogging.log(".");

			}
		}
	}

	static public void deepBreath() throws InterruptedException {

		for (int i = 0; i < 3; i++) {
			breath();
		}
	}

	static public void nap() throws InterruptedException {

		if (longNaps) {
			ourLogging.log("Zzzzzzzzzz");
			int sleepTime = randInt(180, 300);
			TimeUnit.SECONDS.sleep(sleepTime);
		} else {
			// short naps
			ourLogging.log("Zzz");
			if (useSleep) {
				TimeUnit.SECONDS.sleep(60);
			}
		}
	}

	static public void breathToMissleadThem() throws InterruptedException {
		int sleepTime = randInt(30, 60);
		ourLogging.log((new String("Ha Ha ")).concat(String.valueOf(sleepTime)));
		TimeUnit.SECONDS.sleep(sleepTime);
	}

	static public void silentCount() throws InterruptedException {
		silentCounter++;
		if (silentCounter > 100) {
			ourLogging.log("Shshshshsh we are trying to sleep here");
			// extend the nap time
			longNaps = true;
		} else {
			longNaps = false;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
