import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Person {

	private String familyName;		//a single family name
	private String givenNames;		//one or more given names   
	private LocalDate dob;
	private int ssn;  //max value 10**9; -1 > not assigned
	private String priorName;
	private LocalDate nameChangeDate;
	private Marriage marriage;
	
	public Person() {
		familyName = "";
		givenNames = "";
		dob = null;
		ssn = -1;
		priorName = "";
		nameChangeDate = null;
		marriage = null;
	}
	
	public Person(String fN, String gNs,  int birthDay, int birthMonth, int birthYear) {
		familyName = fN;
		givenNames = gNs;
		dob = LocalDate.of(birthYear, birthMonth, birthDay);
		ssn = -1;
		nameChangeDate = null;
		priorName = "";	
	}

	public int getAgeInYears() {
		int age;
		age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
		return age;
	}
	
	public boolean isEighteenOrOver() {
		LocalDate ageEighteen;
		ageEighteen = dob.plusYears( 18 ); //Person's 18th birthday
		return !(ageEighteen.isAfter( LocalDate.now())); //age 18 is in past or today for Person
	}
	
	public String toString(){
		String ret;
		ret = "Person " + familyName.toUpperCase() + "/" + givenNames + "/" + dob + "/" + this.getAgeInYears() + "/" + this.isEighteenOrOver() + "/";
		return ret;
	}
	public boolean isMarried() {
		return this.marriage != null;
	}
	public void getMarried(Marriage m) {
		if (marriage == null) {
			this.marriage = m;
		}
	}
	public boolean canMarry() {
		return this.isEighteenOrOver() && this.marriage == null;
	}
	
	public void setName(String fN, String gNs) {
		priorName = familyName.toUpperCase() + "/" + givenNames;
		nameChangeDate = LocalDate.now();
		familyName = fN;
		givenNames = gNs;
	}
}