package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

//here we are creating a class with all the coding for the different steps
public class StepDefinition extends Utils {
	
	//definning some global variables for use in the whole class or outside
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	
	//creating object of testdatabuild class for sent the body data
	TestDataBuild data = new TestDataBuild();
	
	//here we are implememting the steps defined in the feature files
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		//here we are hitting endpoint for create a plase
		// we are using pojo classes for create the body, that is why we are setting as an input body and object of the main pojo class with all the information
		//using the specspecification in the spec method 
		
		//Here as an example im separating the keyword given and storing into a variable
		res= given().spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
	    
		//here we are calling the enum class created by using valueof
		//using valueof will indicate which enum we want to get by passing the resource string with the value of the enum we want
		// this will return as an object of enum class with the enum we want to execute, so we are saving it in to a variable
		APIResources resourceAPI= APIResources.valueOf(resource);
		
		//then we will get the value of this object, in this case is the value of the enum
		//we are storing it into a variable for use as the path that needs to be hit
		String pathResource=resourceAPI.getResource();
		
		//using the variable generated in the keyword given for complete the endpoint call
		// creating object of responsespectbuilder
		//we need to finish with build in order the requestscepbuilder be builded and created
		resspec= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		//creating a condition for sent the correct request method depending the value received form the testcase
		if(httpMethod.equalsIgnoreCase("POST")) {
		
		response =res.when().post(pathResource);
		}
		else if(httpMethod.equalsIgnoreCase("GET")){
			response =res.when().get(pathResource);
		}
		else if(httpMethod.equalsIgnoreCase("DELETE")){
			response =res.when().delete(pathResource);
		}
		
	}
	
	@Then("the API response is success with status code {int}")
	public void the_api_response_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   //here we are appliying an assert in junit for check the correct status code
		assertEquals(response.getStatusCode(),200);
		
	}
	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    
		//here we are validatinf the correct value or a field in the response
	     assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}
	
	@And("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resource) throws IOException {
		
		//here we are hitting endpoint for get a place
        //using the specspecification in the spec method 
		
		//getting the palce id from the addplace call
		place_id= getJsonPath(response, "place_id");
		
		//Here as an example im separating the keyword given and storing into a variable
		res= given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		//reusing the method for make an http call
		user_calls_with_http_request(resource, "GET");
		//getting the name of the palce that was creating and then with assertion validating is correct
		String actualname= getJsonPath(response, "name");
		assertEquals(actualname,expectedname);
	}
	
	@Given("DeletePlace Payload")
	public void DeletePlace_Payload() throws IOException{
		res= given().spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
		
		
	}

}
