import org.openqa.selenium.WebDriver;

import Pages.salesBasePage;
import common.Browserhelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class stepsCreationDB {
	WebDriver driver= null;



@Given("User is on sales section.")
		public void user_is_on_sales_section() throws InterruptedException {
			salesBasePage.verifPageTitle(Browserhelper.driver, "Accueil | Salesforce");
		}
		
		@When("User create  a new Dashboard.")
		public void i_create_a_dashboard() throws InterruptedException {
		 salesBasePage.clickDB(Browserhelper.driver);
		  Thread.sleep(2000);
		  
			}
		
		
		@When("User Fill the dashboard informations.")
		public void user_fill_the_dashboard_informations() throws InterruptedException {
		  salesBasePage.fullDB(Browserhelper.driver);
		}

		@Then("Dashboard created successfully.")
		public void dashboard_created_successfully() {
			//Browserhelper.driver.close();
	}
}