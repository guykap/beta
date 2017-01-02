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
	private char ethnicity;
	public int paymentMin=0;
	private boolean genderIsMale;
	private boolean confirmedBilling;

	
	public Actor(String actorId,String cnUsername, String cnPassword, String aaUserName, String aaPassword,boolean isNightShift, boolean humanIsMale,char humanEthnicity){
		this.actorId = actorId;
		this.setCnUsername( new String(cnUsername));
		this.setCnPassword( new String(cnPassword));
		this.setAaUsername( new String(aaUserName));
		this.setAaPassword( new String(aaPassword));
		this.setIsNightShift(isNightShift);
		this.genderIsMale =humanIsMale;
		this.ethnicity = humanEthnicity;
		 }

	public Actor(Actor realActor){
		//this copy C'tor is used to create a nightShift actor with the same profile as the read actor
		this.isNightShift = true;
		this.dateOfBirth =  new String(realActor.dateOfBirth);
		this.minActingAge = realActor.minActingAge;
		this.maxActingAge = realActor.maxActingAge;
		this.hasCar = realActor.hasCar;
		this.carModel =  new String(realActor.carModel);
		this.carYear =  new String(realActor.carYear);
		this.standardTalentNote =  new String(realActor.standardTalentNote);
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

	public boolean getGenderIsMale() {
		return genderIsMale;
	}

	public void setGenderIsMale(boolean bitData) {
		this.genderIsMale = bitData;
	}

	
	
	 

	public String getEthinicity() {
		switch(this.ethnicity){
		case 'a': return new String("african american");
		case 'c': return new String("caucasian");
		case 's': return new String("asian");
		case 'i': return new String("indian");
		
		case 'l': return new String("latin");
		case 'm': return new String("middle eastern");
		case 'z': return new String("all ethnicities");
		}
		return "";
	}
	public void setEthinicity(String humanEthnicity) {
		
		 if(humanEthnicity.equals(new String("african american"))){
			 this.ethnicity = 'a';
		 }
		 if(humanEthnicity.equals(new String("caucasian"))){
			 this.ethnicity = 'c';
		 }
		 if(humanEthnicity.equals(new String("asian"))){
			 this.ethnicity = 's';
		 }
		 if(humanEthnicity.equals(new String("indian"))){
			 this.ethnicity = 'i';
		 }
		 if(humanEthnicity.equals(new String("latin"))){
			 this.ethnicity = 'l';
		 }
		 if(humanEthnicity.equals(new String("middle eastern"))){
			 this.ethnicity = 'm';
		 }
		 if(humanEthnicity.equals(new String("all ethnicities"))){
			 this.ethnicity = 'z';
		 }
	}

	public boolean getIsNightShift() {
		return isNightShift;
	}

	public void setIsNightShift(boolean bitData) {
		this.isNightShift = bitData;
	}
}
