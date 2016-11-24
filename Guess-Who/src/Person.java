import java.util.List;

public class Person {
	private String gender;
	private String hairType;
	private String hairColour;
	private String ethnicity;
	private List<String> accessories;
	private String eyeColour;
	private boolean beard;
	private String name;

	public Person(String gender, String hairType, String hairColour,
			String ethnicity, List<String> accessories, String eyeColour, boolean beard, String name) {
		this.gender = gender;
		this.hairType = hairType;
		this.hairColour = hairColour;
		this.ethnicity = ethnicity;
		this.accessories = accessories;
		this.eyeColour = eyeColour;
		this.beard = beard;
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public String getHairColour() {
		return hairColour;
	}

	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public List<String> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}

	public String getEyeColour() {
		return eyeColour;
	}

	public void setEyeColour(String eyeColour) {
		this.eyeColour = eyeColour;
	}

	public boolean isBearded() {
		return beard;
	}

	public void setBearded(boolean beard) {
		this.beard = beard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
