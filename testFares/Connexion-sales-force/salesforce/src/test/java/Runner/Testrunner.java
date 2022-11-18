package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "StepDef", tags = "@tag3", plugin = { "pretty",
		"html:report" }, monochrome = true)

public class Testrunner {

}
