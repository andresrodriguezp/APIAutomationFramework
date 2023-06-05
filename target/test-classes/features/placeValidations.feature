#here we are giving the name to the whole scenario
Feature: Validating Place API's

#here we are creating the different scenarios
#when we wanna use parametrization we use scenario outline for sent the values
#the values are defined as "<valuename>"
#using tags for scenarios for this we use @tagname
@AddPlace @Regression
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API response is success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"

#here we are using examples keyword for sent some values into our testcase
Examples: 
	|name 		| language 	|address			|
	|AAhouse1	| English	|AAhouse address1	|
	|AAhouse2	| Spanish	|AAhouse address2	|
	
	
#writting a new scenario
@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given DeletePlace Payload
When user calls "DeletePlaceAPI" with "Post" http request
Then the API response is success with status code 200
And "status" in response body is "OK"