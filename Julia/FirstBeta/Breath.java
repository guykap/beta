package FirstBeta;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Breath {
	static private boolean useSleep = true;
	static private boolean logStateFull = true;
	static private boolean longNaps;
	static private int silentCounter=0;

	Breath() {
		this.useSleep = true;;
		this.logStateFull = true;
	}

	Breath(boolean currUseSleep, boolean currLogStateFull) {
		this.useSleep = currUseSleep;
		this.logStateFull = currLogStateFull;
	}

	static public void breath() throws InterruptedException {
		// sleeps for the configured time + impro
		int sleepTime = randInt(4, 5);
		if (useSleep) {
			TimeUnit.SECONDS.sleep(sleepTime);
			if (logStateFull) {
				Beta.log(".");

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
			Beta.log("Zzzzzzzzzz");
			int sleepTime = randInt(180, 300);
			TimeUnit.SECONDS.sleep(sleepTime);
		} else {
			// short naps
			Beta.log("Zzz");
			if (useSleep) {
				TimeUnit.SECONDS.sleep(60);
			}
		}
	}

	static public void breathToMissleadThem() throws InterruptedException {
		int sleepTime = randInt(30, 60);
		Beta.log((new String("Ha Ha ")).concat(String.valueOf(sleepTime)));
		TimeUnit.SECONDS.sleep(sleepTime);
	}

	static public void staySilent() throws InterruptedException {
		// log("Silent counter : " + silentCounter);
		silentCounter++;
		if (silentCounter > 100) {
			Beta.log("Shshshshsh we are trying to sleep here");
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
