package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//we are setting also the req variable as static in order it be attached to the class and not the the object
	//this is done for ensure the value of the variable be the same in all execution and dont be null always an object be created, only the first time will be null
	public static RequestSpecification req;
	
	/*
	//creating method for RequestSpecification
	public RequestSpecification requestSpecification() throws IOException {
		
		//here im creating an object for log all from the request
		//for do this we use PrinStream class where input is the file i wanna create
		//for create a file we make use of the Fileoutputstream class
		//as an input we set the name of the file to be created and another parameter for indicate the logs creatted be appended and the file will continue growing
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt",true));
		
		//here we are using spec builders classes for set some commons steps
		
		// creating object of requestspectbuilder
		//we need to finish with build in order the requestscepbuilder be builded and created
		//we can also log all details from the request by adding a filter using add filter-requestloggingfilter and writing it into a output stream file
		//we can also log all details from the response by adding a filter using add filter-responseloggingfilter and writing it into a output stream file
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
		return req;

	}*/
	

  // another way to rewrite the file but here the file wont continue growing when the code is executed by first time	
	//creating method for RequestSpecification
	public RequestSpecification requestSpecification() throws IOException {
		
		//here we are creating an if condition in order the file for log the api call and response, dont be overriting
		if(req==null) {
		
		//here im creating an object for log all from the request
		//for do this we use PrinStream class where input is the file i wanna create
		//for create a file we make use of the Fileoutputstream class
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		//here we are using spec builders classes for set some commons steps
		
		// creating object of requestspectbuilder
		//we need to finish with build in order the requestscepbuilder be builded and created
		//we can also log all details from the request by adding a filter using add filter-requestloggingfilter and writing it into a output stream file
		//we can also log all details from the response by adding a filter using add filter-responseloggingfilter and writing it into a output stream file
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
	}
	
	
	//creaing method for get values from a properties file, where we enter as an input the key of the value we want to get
	public static String getGlobalValue(String key) throws IOException {
		
		//we can create a properties file with some global variables to be used in every testecase
		//for do it we will make use of the properties class and fileinputstream class
		// first we will convert our file with the global variables into inputstream file
		
		//here we are creating an object of the class, and as and input we should indicate the location of the properties file
		//additionally in order to share the framework and that it be used by many people, we can't hardoce our local path, we nned to make it global
		//using system.getproperty, we can set that. it will automatically set the user default project location
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		
		// now we will create object for the properties class
		Properties properties = new Properties();
		// here we ill load our inputstreamfile as an input for the properties
		properties.load(fis);
		
		//here we are getting the value of the baseurl from the properties file
		return properties.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		
		String resp= response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);
	}

}
