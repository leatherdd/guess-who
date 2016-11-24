import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
	private HashMap<String, Person> community;
	
	public static void main(String args0) {
		
	}
	
	public void initCommunity() {
		community = new HashMap<String, Person>();
		List<String> glassesAccessories = new ArrayList<String>();
		glassesAccessories.add("glasses");
		List<String> sunglassesAccessories = new ArrayList<String>();
		sunglassesAccessories.add("sunglasses");
		List<String> hatAccessories = new ArrayList<String>();
		hatAccessories.add("hat");
		List<String> tieAccessories = new ArrayList<String>();
		tieAccessories.add("tie");
		List<String> necklaceAccessories = new ArrayList<String>();
		necklaceAccessories.add("necklace");
		List<String> bowtieAccessories = new ArrayList<String>();
		bowtieAccessories.add("bow tie");
		List<String> queenAccessories = new ArrayList<String>();
		queenAccessories.add("necklace");
		queenAccessories.add("crown");
		
		//TODO Add beard values - get information from images
		community.put("Scarlet", new Person("female", "short", "blonde", "white", null, "brown", null, "Scarlet"));
		community.put("Louis", new Person("male", "short", "brown", "white", glassesAccessories, "brown", null, "Louis"));
		community.put("Beyonce", new Person("female", "curly", "brown", "black", null, "brown", null, "Beyonce"));
		community.put("Russell", new Person("male", "long", "brown", "white", sunglassesAccessories, "brown", null, "Russell"));
		community.put("Ron", new Person("male", "short", "ginger", "white", null, "blue", null, "Ron"));
		community.put("Donald", new Person("male", "short", "blonde", "white", hatAccessories, "brown", null, "Donald"));
		community.put("Ed", new Person("male", "short", "ginger", "white", null, "blue", null, "Ed"));
		community.put("Harry", new Person("male", "long", "brown", "white", null, "brown", null, "Harry"));
		community.put("Mike", new Person("male", "bald", "no", "black", null, "brown", null, "Mike"));
		community.put("Ross", new Person("male", "bald", "no", "white", tieAccessories, "blue", null, "Ross"));
		community.put("Usain", new Person("male", "bald", "brown", "black", null, "brown", null, "Usain"));
		community.put("Judy", new Person("female", "short", "blonde", "white", necklaceAccessories, "blue", null, "Judy"));
		community.put("Sean", new Person("male", "bald", "grey", "white", bowtieAccessories, "brown", null, "Sean"));
		community.put("Michelle", new Person("female", "straight", "brown", "black", necklaceAccessories, "brown", null, "Michelle"));
		community.put("Connor", new Person("male", "short", "ginger", "white", tieAccessories, "blue", null, "Connor"));
		community.put("George", new Person("male", "short", "grey", "white", bowtieAccessories, "brown", null, "George"));
		community.put("Kim", new Person("female", "straight", "brown", "white", null, "brown", null, "Kim"));
		community.put("Johnny", new Person("male", "long", "brown", "white", glassesAccessories, "brown", null, "Johnny"));
		community.put("Jennifer", new Person("female", "short", "blonde", "white", necklaceAccessories, "green", null, "Jennifer"));
		community.put("Richard", new Person("male", "long", "blonde", "white", null, "blue", null, "Richard"));
		community.put("Pink", new Person("female", "short", "pink", "white", null, "brown", null, "Pink"));
		community.put("Taylor", new Person("female", "curly", "blonde", "white", null, "blue", null, "Taylor"));
		community.put("David", new Person("male", "short", "brown", "white", null, "brown", null, "David"));
		community.put("Queen", new Person("female", "curly", "grey", "white", queenAccessories, "blue", null, "Queen"));
	}

}
