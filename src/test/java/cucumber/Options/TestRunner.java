package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//here we are cretating the testruner class that is in charge of run the cucumber testcases

//we are setting the testrunner indicating we will run the testcases with junit cucumber class
@RunWith(Cucumber.class)

//we are also setting the options for run cucumber, indicating the location of the feature file, the package of the step definition and to log all the steps

@CucumberOptions(features="src/test/java/features",
                 glue={"stepDefinitions"},
                 //tags= "@DeletePlace", //with the use of tags we can indicate which scenarios to run and which not
                 plugin={"pretty", "json:target/jsonReports/cucumber-report.json"}, //here im indicating the logs should provide more information with the pluggin pretty and also indicating a report with the result of the test execution should be created in json format, im indicating the location of the folder and name of the file where the result should be placed
                 monochrome=true // this is for remove unecessary thingd from the logs
                 //stepNotifications = true // this is for log the steps
                 )

public class TestRunner {

}
