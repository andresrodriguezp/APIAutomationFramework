package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

//this clase is created for set preconditions before run a testcase
public class Hooks {
	
	//here with the before tag we are indicating this should be reun before a testcase
	//inside brackets we indicate scenario which require some precondition be set
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//here we codify the preconditions
		//in this case is are the methods for add a place
		StepDefinition m = new StepDefinition();
		
		//here we are validating if the previous add testcase was run or not, in order to run this code or not
		if(StepDefinition.place_id==null) {
		m.add_place_payload_with("Andres", "French", "Colombia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Andres", "GetPlaceAPI");
		
		}
	}

}
