import java.util.EventObject;

public class FormEvent extends EventObject {

	private String name;
	private String occupation;
	private int ageCategory;
	private String empCat;
	private String taxID;
	private boolean usCitizen;
	private String gender;
	
	//this is the Form Panel's data layer
	public FormEvent(Object source, String name, String occupation, int ageCategory, String empCat,
			String taxID, boolean usCitizen, String gender) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.setEmpCat(empCat);
		this.setTaxID(taxID);
		this.setUsCitizen(usCitizen);
		this.setGender(gender);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(int ageCategory) {
		this.ageCategory = ageCategory;
	}

	public String getEmpCat() {
		return empCat;
	}

	public void setEmpCat(String empCat) {
		this.empCat = empCat;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
}
