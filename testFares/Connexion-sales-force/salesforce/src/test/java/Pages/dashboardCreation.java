package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Browserhelper;
import junit.framework.Assert;

public class dashboardCreation {
	WebDriver driver = null;

    public dashboardCreation(WebDriver driver) {
	this.driver=null;
    }
     public void verifonSalespage() throws Exception {
	//driver.findElement(By.xpath("//span[text()='Tableaux de bord']")).click();
    	
	Browserhelper.initializeDriver(driver);
    WebElement salesTitle;
	salesTitle = driver.findElement(By.xpath("//span[@class='slds-truncate'][@title='Ventes']"));
	String st= String.valueOf(salesTitle);
	String researchtitle = "Ventes";
	Assert.assertEquals(st, researchtitle);
	 
}
}
