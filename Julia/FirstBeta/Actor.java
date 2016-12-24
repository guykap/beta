package FirstBeta;

public class Actor {

	private int id;
	private String cnUsername;
	private String cnPassword;
	private String aaUsername;
	private String aaPassword;
	private Logging actorsLog;
	
	private String firstName;
	private String LastName;
	private String DateOfBirth;
	private int minActingAge;
	private int maxActingAge;
	private boolean hasCar;
	private String carModel;
	private String carYear;
	private String standardTalentNote;
	private String Ethnicity;
	private int paymentMin;
	private boolean GenderIsMale;
	private boolean confirmedBilling;

	
	public Actor(int actorId,String cnUsername, String cnPassword, String aaUserName, String aaPassword){
		id = actorId;
		this.setCnUsername(cnUsername);
		this.setCnPassword(cnPassword);
		this.setAaUsername(aaUserName);
		this.setAaPassword(aaPassword);
		 
	}


	public String getCnUsername() {
		return cnUsername;
	}

	public void setCnUsername(String cnUsername) {
		this.cnUsername = cnUsername;
	}


	public String getCnPassword() {
		return cnPassword;
	}


	public void setCnPassword(String cnPassword) {
		this.cnPassword = cnPassword;
	}

	public String getAaUsername() {
		return aaUsername;
	}


	public void setAaUsername(String aaUsername) {
		this.aaUsername = aaUsername;
	}

	public String getAaPassword() {
		return aaPassword;
	}

	public void setAaPassword(String aaPassword) {
		this.aaPassword = aaPassword;
	}
}
