package StepDef;

import org.openqa.selenium.WebDriver;


import Pages.salesBasePage;
import common.Browserhelper;
import common.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepsFolder {
	WebDriver driver=null;

	
	@Given("User is on sales page")
	public void user_is_on_sales_page() throws InterruptedException {
		salesBasePage.verifPageTitle(Browserhelper.driver, "Accueil | Salesforce");
	}

	@When("User create a new folder.")
	public void user_create_a_new_folder() throws InterruptedException {
		salesBasePage.createNewFolder(Browserhelper.driver);   
	}

	@When("User fill the folder informations")
	public void user_fill_the_folder_informations() throws InterruptedException {
	    salesBasePage.fullFolder(Browserhelper.driver);
	}

	@Then("a new folder for saving is created.")
	public void a_new_folder_for_saving_is_created() {
	    
	}

	
	
}