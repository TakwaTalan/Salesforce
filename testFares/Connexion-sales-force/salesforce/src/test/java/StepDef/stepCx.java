package StepDef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.salesBasePage;
import common.Browserhelper;



public class stepCx {
	WebDriver driver=null;
	//BrowserNotification notif;
	

		@Given("User navigate to salesforce account")
		public void browser_is_open() throws Exception {
		Browserhelper.driver=Browserhelper.initializeDriver(Browserhelper.driver);
		salesBasePage.loginsalesforce(Browserhelper.driver);

	}
	   @When("I navigate to salesforce sectin sales")
	   public void i_navigate_to_salesforce_sectin_sales() throws InterruptedException {
		   salesBasePage.launchAppSales(Browserhelper.driver);
		  
	       
	   }
	   @Then("I click on Sales section")
	   public void i_click_on_sales_section() {
	     System.out.println("You are on sales Page");
	     
	   }

		


}