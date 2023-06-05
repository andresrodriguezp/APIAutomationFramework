package resources;

//we are creating an enum class
//this is a special class which has collection of constants and methods
public enum APIResources {
		
	//here we will define collection of enums with its values separating them by , and finishing the collection with ;
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	//creating a global variable
	private String resource;
	
	//creating a constructor for the enum and the value we want to get for that enum
	//where the input will be the value of the enum we want to get
	APIResources(String resource){
		
		//here we are assigning the value of the enum to a global variable
			this.resource=resource;
	}
	
	//creating a method for return the value of the choosed enum
	public String getResource() {
		
	   return resource;
	}

}
