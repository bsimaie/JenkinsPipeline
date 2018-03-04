package cucumberTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "cucumberTests",
		format = { "pretty", "html:target/cucumber-test-reports"},
		dryRun = false
		)
public class Runner {

}
