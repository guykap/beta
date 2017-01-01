package FirstBeta;

public class Actor {

	private String actorId;
	private String cnUsername;
	private String cnPassword;
	private String aaUsername;
	private String aaPassword;
	private Logging actorsLog;
	
	public boolean isNightShift;
	private String firstName="";
	private String LastName="";
	public String dateOfBirth="";
	public int minActingAge=0;
	public int maxActingAge=0;
	public boolean hasCar=false;
	public String carModel="";
	public String carYear="";
	public String standardTalentNote="";
	public String ethnicity="";
	public int paymentMin=0;
	public boolean genderIsMale;
	public boolean confirmedBilling;

	
	public Actor(String actorId,String cnUsername, String cnPassword, String aaUserName, String aaPassword,boolean isNightShift, boolean humanIsMale){
		this.actorId = actorId;
		this.setCnUsername(cnUsername);
		this.setCnPassword(cnPassword);
		this.setAaUsername(aaUserName);
		this.setAaPassword(aaPassword);
		this.setIsNightShift(isNightShift);
		this.genderIsMale =humanIsMale;
		 }

	public Actor(Actor realActor){
		//this copy C'tor is used to create a nightShift actor with the same profile as the read actor
		this.isNightShift = true;
		this.dateOfBirth = realActor.dateOfBirth;
		this.minActingAge = realActor.minActingAge;
		this.maxActingAge = realActor.maxActingAge;
		this.hasCar = realActor.hasCar;
		this.carModel = realActor.carModel;
		this.carYear = realActor.carYear;
		this.standardTalentNote = realActor.standardTalentNote;
		this.ethnicity  = realActor.ethnicity;
		this.paymentMin = realActor.paymentMin;
		this.genderIsMale  = realActor.genderIsMale;
		this.confirmedBilling  = realActor.confirmedBilling;
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

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String id) {
		this.actorId = id;
	}
	
	public String getAaPassword() {
		return aaPassword;
	}

	public void setAaPassword(String aaPassword) {
		this.aaPassword = aaPassword;
	}

	public boolean getIsNightShift() {
		return isNightShift;
	}

	public void setIsNightShift(boolean bitData) {
		this.isNightShift = bitData;
	}
}
