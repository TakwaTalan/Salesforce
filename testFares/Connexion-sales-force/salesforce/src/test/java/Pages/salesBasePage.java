package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.pagefactory.ByChained;

import common.*;


public class salesBasePage {
	
	public static WebDriver driver;
	
	static void sleepFunction (long sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime);
		
	
	}
	/*************************** lOGIN TO salesforce account*********************************************/
	
	public static void loginsalesforce(WebDriver driver) throws Exception {
		Browserhelper.openBrowserInMaxWindow(driver);
		Browserhelper.navigate(driver, Utils.getProperty("Web_Portal_Link"));
		//Browserhelper.waitForPageLoading(driver);*/
		WebElement loginField = driver.findElement(By.xpath("//input[contains(@id,'username')]"));
		loginField.sendKeys(Utils.getProperty("user_name"));
		WebElement passwordField = driver.findElement(By.xpath("//input[contains(@id,'password')]"));
		passwordField.sendKeys(Utils.getProperty("user_pass"));
		WebElement button = driver.findElement(By.xpath("//input[@id='Login']"));
		Browserhelper.clickOnElement(driver, button);	
		//Browserhelper.checkAuthentication(driver);	
		//driver.findElement(By.xpath("//a[contains(text(),'Remind Me Later')]")).click();;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	}
	
public void cliqueRappelU(){
	
	driver.findElement(By.xpath("//a[contains(text(),'Remind Me Later')]")).click();;
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	//driver.findElement(By.xpath("//a[contains(text(),'Me le rappeler ultérieurement')]")).click(); 

}	

/***************************************SALES SECTION
 * @return ***************************************/

public static void launchAppSales(WebDriver driver) throws InterruptedException{
	sleepFunction(5000);
	driver.findElement(By.xpath("//*[contains(@class,'appLauncher')]//button")).click(); 
	sleepFunction(10000);
	driver.findElement(By.xpath("//input[contains(@placeholder,'Recherchez des applications et des éléments...')]")).sendKeys("Ventes");
	sleepFunction(5000);
	driver.findElement(By.xpath("(//*[@class='slds-truncate']/b)[1]")).click(); 
	Thread.sleep(5000);
}

/*public void verifonSalespage() throws Exception {
	//driver.findElement(By.xpath("//span[text()='Tableaux de bord']")).click();
  	
	//Browserhelper.initializeDriver(driver);
  /*WebElement salesTitle;
	salesTitle = driver.findElement(By.xpath("//span[@class='slds-truncate'][@title='Ventes']"));
	String st= String.valueOf(salesTitle);
	String researchtitle = "Ventes";
	Assert.assertEquals(st, researchtitle);

}
}*/
 /******************************Creation a Dashboard**********************************************************/

public static void clickDB(WebDriver driver) throws InterruptedException {
	
	
	driver.manage().window().maximize();
	sleepFunction(10000);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Menu Tableaux de bord'])[1]/preceding::span[1]")));
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*/text()[normalize-space(.)='Nouveau tableau de bord']/parent::*")).click();
}
public static void SwitchFrame(WebDriver driver, String framePath) {

  
    WebElement frame= driver.findElement(By.xpath(framePath));

    driver.switchTo().frame(frame);
    
}

public static void fullDB(WebDriver driver) throws InterruptedException {
	
	sleepFunction(6000);
	SwitchFrame(driver, "//*[@id=\"brandBand_2\"]/div/div/div[3]/div/div[2]/iframe");
	WebElement DBname;
	DBname = driver.findElement(By.cssSelector("#dashboardNameInput"));
	sleepFunction(2000);
	DBname.click();
	DBname.sendKeys("DBtest1");
	sleepFunction(2000);
	driver.findElement(By.xpath("//*[@id=\"dashboardDescriptionInput\"]")).sendKeys("un nouveau tableau de bord");
	sleepFunction(2000);
	driver.findElement(By.xpath("//*[@id=\"submitBtn\"]")).click();
}

public static void verifPageTitle (WebDriver driver, String expectedTitle ) throws InterruptedException {
	
	String PageTitle;
	PageTitle = driver.getTitle();
	sleepFunction(2000);
	System.out.println(PageTitle);
	boolean status;
	status= false;
	if (PageTitle == expectedTitle) {
		status = true;
	}
	}
/**************************** Create a folder********************************************/
public static void createNewFolder ( WebDriver driver) throws InterruptedException {
	
	driver.manage().window().maximize();
	sleepFunction(10000);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Menu Tableaux de bord'])[1]/preceding::span[1]")));
    sleepFunction(2000);
    driver.findElement(By.xpath("//*/text()[normalize-space(.)='Nouveau dossier']/parent::*")).click();
	
}
public static void fullFolder( WebDriver driver) throws InterruptedException {
	sleepFunction(2000);
	WebElement folder;
	folder = driver.findElement(By.xpath("//input[@class='inputText input']"));
	folder.click();
	sleepFunction(1000);
	folder.sendKeys("TestDossier02");
	sleepFunction(5000);
	driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div[2]/div/div/input")).click();
	sleepFunction(3000);
	driver.findElement(By.xpath("//*/text()[normalize-space(.)='Enregistrer']/parent::*")).click();
	sleepFunction(3000);
}
 /*************************Modify a Dashboard***************************************/
public static void dashboardPage(WebDriver driver ) throws InterruptedException {

	driver.manage().window().maximize();
	sleepFunction(10000);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();",driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Menu Tableaux de bord'])[1]/preceding::span[1]")));
	sleepFunction(2000);
}
public static void chooseDB(WebDriver driver) throws InterruptedException {
	sleepFunction(2000);
	driver.findElement(By.xpath("//*/text()[normalize-space(.)='DBtest1']/parent::*")).click();
}
}

