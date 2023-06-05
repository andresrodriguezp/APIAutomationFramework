package resources;

import java.util.ArrayList;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	//this method is created for create the body used in the request
	public addPlace addPlacePayload(String name, String language, String address) {
		
	    // Write code here that turns the phrase above into concrete actions
		//here we are creating an array list for set the values into the arrays list types
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		
		addPlace ad= new addPlace(); //creating object of the main pojo class
		Location l= new Location(); //creating object of the sub pojo class
		
		//here we are setting the values for construct the body
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ad.setLocation(l);
		ad.setAccuracy(50);
		ad.setName(name);
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setAddress(address);
		ad.setTypes(myList);
		ad.setWebsite("http://google.com.co");
		ad.setLanguage(language);
		
		return ad;
	}
	
	public String deletePlacePayload(String placeid) {
		
		return("{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}");
	}

}
