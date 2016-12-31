package FirstBeta;

import org.openqa.selenium.By;

public class Scapper {
	// This class scaps data via the driver

	static public void parseRowOfferAA(Job offer, int rowNum) {

		String leftPart = (new String("//div[@id='mainContent']/div[@class='list']/table/tbody/tr["))
				.concat(String.valueOf(rowNum + 2));
		String path;
		try {
			path = (new String(leftPart)).concat("]/td[2]");
			Logging.slog(path);
			offer.setOfferPostedTime(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferPostedTime(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[3]/a[starts-with(@href,'/projects/')]");
			Logging.slog(path);
			offer.setOfferProjectName(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferProjectName(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[4]");
			Logging.slog(path);
			offer.setOfferTypeProject(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferTypeProject(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[5]");
			offer.setOfferCastingDirector(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferCastingDirector(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[6]");
			offer.setOfferShootDate(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferShootDate(new String(""));
		}

		try {
			path = (new String(leftPart)).concat("]/td[7]");
			offer.setOfferUnionStatus(new String(Beta.driver.findElement(By.xpath(path)).getText()));
		} catch (Exception e) {
			offer.setOfferUnionStatus(new String(""));
		}
	}

	static public void handleBackgroundWorkOffer(Job offer, boolean isBackgroundWork, int row) {
		offer.setIsBackgroundWork(isBackgroundWork);
		// the EXTRA table has the shooting date .
		// the PRINCIPLE table does not

		String leftPart = (new String("//tr[")).concat(String.valueOf(row));
		try {

			try {

				String path = new String(leftPart.concat("]/td/a"));
				offer.setOfferRole((new String(Beta.driver.findElement(By.xpath(path)).getText())).toLowerCase());
			} catch (Exception e) {
				offer.setOfferRole(new String(""));
			}

			if (isBackgroundWork) {
				// BACKGROUND WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName(
							(new String(Beta.driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferShootDate(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferShootDate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOfferTypeProject(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOffertRate(new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferPaying(new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferUnionStatus(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[8]/a"));
					offer.setOfferPostedTime(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			} else {
				// PRINCIPLE WORK
				try {
					String path = new String(leftPart.concat("]/td[2]/a"));
					offer.setOfferProjectName(
							(new String(Beta.driver.findElement(By.xpath(path)).getText())).toLowerCase());
				} catch (Exception e) {
					offer.setOfferProjectName(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[3]/a"));
					offer.setOfferTypeProject(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferTypeProject(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[4]/a"));
					offer.setOffertRate(new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOffertRate(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[5]/a"));
					offer.setOfferPaying(new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPaying(new String(""));
				}
				try {
					String path = new String(leftPart.concat("]/td[6]/a"));
					offer.setOfferUnionStatus(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferUnionStatus(new String(""));
				}

				try {
					String path = new String(leftPart.concat("]/td[7]/a"));
					offer.setOfferPostedTime(
							new String(Beta.driver.findElement(By.xpath(path)).getText()).toLowerCase());
				} catch (Exception e) {
					offer.setOfferPostedTime(new String(""));
				}

			}
			try {
				String pathOfferListing = new String(
						((new String("//tr[")).concat(String.valueOf(row + 1))).concat("]/td"));
				offer.setOfferListing(
						new String(Beta.driver.findElement(By.xpath(pathOfferListing)).getText()).toLowerCase());
			} catch (Exception e) {
				offer.setOfferListing(new String(""));
			}
			return;

		} catch (Exception e) {
			Logging.slog("Error parsing the current offer data into the Strings");
			// go back to login page
		}
	}

	static public String scrapTextAtXpath(String xpath) {
		// returns the text at the xPath tab
		String foundText = new String("");
		try {
			foundText = new String(new String(Beta.driver.findElement(By.xpath(xpath)).getText()));
		} catch (Exception e) {
			Logging.slog((new String("No text found at: ")).concat(xpath));

		}

		return foundText;
	}

	static public String scrapAttributeAtXpath(String xpath, String attribute) {
		if ((xpath.length() < 1) || (attribute.length() < 1)) {
			Logging.slog((new String("Wrong values passed to scrapper: ")).concat(xpath).concat(attribute));

			return new String("");
		}
		// returns the text at the xPath tab
		String foundText = new String("");
		try {
			foundText = Beta.driver.findElement(By.xpath(xpath)).getAttribute(attribute);
			// foundText = new String(new
			// String(Beta.driver.findElement(By.xpath(xpath)).get));
		} catch (Exception e) {
			Logging.slog((new String("No attribute found at: ")).concat(xpath));

		}

		return foundText;
	}

}
