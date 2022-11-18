package StepDef;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.salesBasePage;
import common.Browserhelper;

public class stepsModification {
	WebDriver driver=null;

@Given("User is on Dashboard section.")
public void user_is_on_dashboard_section() throws InterruptedException {
   salesBasePage.dashboardPage(Browserhelper.driver); 
}

@When("User choose an exsiting Dashboard to modify.")
public void user_choose_an_exsiting_dashboard_to_modify() throws InterruptedException {
    salesBasePage.chooseDB(Browserhelper.driver);
}

@And("User choose a compountand add it to the existing dashboard.")
public void user_choose_a_compountand_add_it_to_the_existing_dashboard() {
   
}

@Then("User save the modification.")
public void user_save_the_modification() {
    
}
}