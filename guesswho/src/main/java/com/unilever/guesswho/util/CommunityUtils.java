package com.unilever.guesswho.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.unilever.guesswho.model.Person;

public class CommunityUtils {

	public static HashMap<String, Person> buildCommunity() {
		HashMap<String, Person> community = new HashMap<String, Person>();

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

		// TODO - second pair of eyes to review these
		community.put("Scarlet", new Person("female", "short", "blonde", "white", null, "brown", false, "Scarlet"));
		community.put("Louis",
				new Person("male", "short", "brown", "white", glassesAccessories, "brown", true, "Louis"));
		community.put("Beyonce", new Person("female", "curly", "brown", "black", null, "brown", false, "Beyonce"));
		community.put("Russell",
				new Person("male", "long", "brown", "white", sunglassesAccessories, "brown", true, "Russell"));
		community.put("Ron", new Person("male", "short", "ginger", "white", null, "blue", false, "Ron"));
		community.put("Donald",
				new Person("male", "short", "blonde", "white", hatAccessories, "brown", false, "Donald"));
		community.put("Ed", new Person("male", "short", "ginger", "white", null, "blue", true, "Ed"));
		community.put("Harry", new Person("male", "long", "brown", "white", null, "brown", false, "Harry"));
		community.put("Mike", new Person("male", "bald", "no", "black", null, "brown", false, "Mike"));
		community.put("Ross", new Person("male", "bald", "no", "white", tieAccessories, "blue", false, "Ross"));
		community.put("Usain", new Person("male", "bald", "brown", "black", null, "brown", false, "Usain"));
		community.put("Judy",
				new Person("female", "short", "blonde", "white", necklaceAccessories, "blue", false, "Judy"));
		community.put("Sean", new Person("male", "bald", "grey", "white", bowtieAccessories, "brown", true, "Sean"));
		community.put("Michelle",
				new Person("female", "straight", "brown", "black", necklaceAccessories, "brown", false, "Michelle"));
		community.put("Connor", new Person("male", "short", "ginger", "white", tieAccessories, "blue", true, "Connor"));
		community.put("George",
				new Person("male", "short", "grey", "white", bowtieAccessories, "brown", false, "George"));
		community.put("Kim", new Person("female", "straight", "brown", "white", null, "brown", false, "Kim"));
		community.put("Johnny",
				new Person("male", "long", "brown", "white", glassesAccessories, "brown", true, "Johnny"));
		community.put("Jennifer",
				new Person("female", "short", "blonde", "white", necklaceAccessories, "green", false, "Jennifer"));
		community.put("Richard", new Person("male", "long", "blonde", "white", null, "blue", true, "Richard"));
		community.put("Pink", new Person("female", "short", "pink", "white", null, "brown", false, "Pink"));
		community.put("Taylor", new Person("female", "curly", "blonde", "white", null, "blue", false, "Taylor"));
		community.put("David", new Person("male", "short", "brown", "white", null, "brown", true, "David"));
		community.put("Queen",
				new Person("female", "curly", "grey", "white", queenAccessories, "blue", false, "Queen"));
		
		return community;
	}
	
	public static Person findPersonByName(String name, HashMap<String, Person> community){
		if(community.containsKey(name)){
			return community.get(name);
		}else{
			return null;
		}
	}

}
