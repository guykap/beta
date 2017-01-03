package FirstBeta;

public class Actor {

	private String actorId;
	private String cnUsername;
	private String cnPassword;
	private String aaUsername;
	private String aaPassword;
	private Logging actorsLog;

	public boolean isNightShift;
	private String firstName = "";
	private String LastName = "";
	public String dateOfBirth = "";
	public int minActingAge = 0;
	public int maxActingAge = 0;
	public boolean hasCar = false;
	public String carModel = "";
	public String carYear = "";
	public String standardTalentNote = "";
	private char ethnicity;
	private char unionStatus;
	public int paymentMin = 0;
	private boolean genderIsMale;
	private boolean confirmedBilling;
	private String humansSizes = "";
	private String humansDefaultTalentNote = "";
	private int humansMinActingAge = 0;
	private int humansMaxActingAge = 0;

	public Actor(String actorId, String cnUsername, String cnPassword, String aaUserName, String aaPassword,
			boolean isNightShift, boolean humanIsMale, String humanEthnicity,String unionStatus ,String inputhumanSizes,
			String inputhumanNotes, int inputhumansMinActingAge, int inputhumansMaxActingAge) {
		this.actorId = actorId;
		this.setCnUsername(new String(cnUsername));
		this.setCnPassword(new String(cnPassword));
		this.setAaUsername(new String(aaUserName));
		this.setAaPassword(new String(aaPassword));
		this.setIsNightShift(isNightShift);
		this.genderIsMale = humanIsMale;
		this.setEthinicity(humanEthnicity);
		this.setUnionStatus(unionStatus);
		this.humansSizes = new String(inputhumanSizes);
		this.humansDefaultTalentNote = new String(inputhumanNotes);
		this.humansMinActingAge = inputhumansMinActingAge;
		this.humansMaxActingAge = inputhumansMaxActingAge;

	}

	public Actor(Actor realActor) {
		// this copy C'tor is used to create a nightShift actor with the same
		// profile as the read actor
		this.isNightShift = true;
		this.dateOfBirth = new String(realActor.dateOfBirth);
		this.minActingAge = realActor.minActingAge;
		this.maxActingAge = realActor.maxActingAge;
		this.hasCar = realActor.hasCar;
		this.carModel = new String(realActor.carModel);
		this.carYear = new String(realActor.carYear);
		this.standardTalentNote = new String(realActor.standardTalentNote);
		this.ethnicity = realActor.ethnicity;
		this.paymentMin = realActor.paymentMin;
		this.genderIsMale = realActor.genderIsMale;
		this.confirmedBilling = realActor.confirmedBilling;
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

	public String getDefaultTalentNote() {
		return aaPassword;
	}

	public void setDefaultTalentNote(String data) {
		this.humansDefaultTalentNote = new String(data);
	}

	public String getAaPassword() {
		return aaPassword;
	}

	public void setAaPassword(String aaPassword) {
		this.aaPassword = aaPassword;
	}

	public String getHumansSizes() {
		return humansSizes;
	}

	public void getHumansSizes(String size) {
		this.humansSizes = new String(size);
	}

	public int getHumansMinActingAge() {
		return humansMinActingAge;
	}

	public void setHumansMinActingAge(int data) {
		this.humansMinActingAge = data;
	}

	public int getHumansMaxActingAge() {
		return humansMaxActingAge;
	}

	public void setHumansMaxActingAge(int data) {
		this.humansMaxActingAge = data;
	}

	public boolean getGenderIsMale() {
		return genderIsMale;
	}

	public void setGenderIsMale(boolean bitData) {
		this.genderIsMale = bitData;
	}

	public String getEthinicity() {
		switch (this.ethnicity) {
		case 'a':
			return new String("african american");
		case 'c':
			return new String("caucasian");
		case 's':
			return new String("asian");
		case 'i':
			return new String("indian");

		case 'l':
			return new String("latin");
		case 'm':
			return new String("middle eastern");
		case 'z':
			return new String("all ethnicities");
		}
		return "";
	}

	public char getEthinicityChar() {
		switch (this.ethnicity) {
		case 'a':
			return this.ethnicity;
		case 'c':
			return this.ethnicity;
		case 's':
			return this.ethnicity;
		case 'i':
			return this.ethnicity;

		case 'l':
			return this.ethnicity;
		case 'm':
			return this.ethnicity;
		case 'z':
			return this.ethnicity;
		}
		return '_';
	}
	public void setEthinicity(String humanEthnicity) {

		if (humanEthnicity.equals(new String("african american"))) {
			this.ethnicity = 'a';
		}
		if (humanEthnicity.equals(new String("caucasian"))) {
			this.ethnicity = 'c';
		}
		if (humanEthnicity.equals(new String("asian"))) {
			this.ethnicity = 's';
		}
		if (humanEthnicity.equals(new String("indian"))) {
			this.ethnicity = 'i';
		}
		if (humanEthnicity.equals(new String("latin"))) {
			this.ethnicity = 'l';
		}
		if (humanEthnicity.equals(new String("middle eastern"))) {
			this.ethnicity = 'm';
		}
		if (humanEthnicity.equals(new String("all ethnicities"))) {
			this.ethnicity = 'z';
		}
	}
	
	public String getUnionStatusString() {
		switch (this.unionStatus) {
		case 'u':
			return new String("union");
		case 'n':
			return new String("non-union");
		case 'b':
			return new String("both");
		 
		}
		return "";
	}
	
	public char getUnionStatusChar() {
		switch (this.unionStatus) {
		case 'u':
			return (this.unionStatus);
		case 'n':
			return (this.unionStatus);
		case 'b':
			return (this.unionStatus);
		 
		}
		return ' ';
	}

	public void setUnionStatus(String unionStatus) {

		if (unionStatus.equals(new String("union"))) {
			this.unionStatus = 'u';
		}
		if (unionStatus.equals(new String("non-union"))) {
			this.unionStatus = 'n';
		}
		if (unionStatus.equals(new String("both"))) {
			this.unionStatus = 'b';
		}
	}

	public boolean getIsNightShift() {
		return isNightShift;
	}

	public void setIsNightShift(boolean bitData) {
		this.isNightShift = bitData;
	}
}
