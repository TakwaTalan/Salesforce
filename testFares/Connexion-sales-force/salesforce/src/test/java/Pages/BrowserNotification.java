package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
public class BrowserNotification{
   public static void main(String[] args) {
     // System.setProperty("webdriver.chrome.driver", "C:\Users\ghs6kor\Desktop\Java\chromedriver.exe");
      //ChromeOptions object
      ChromeOptions op = new ChromeOptions();
      //disable notification parameter
      op.addArguments("--disable-notifications");
      // configure options parameter to Chrome driver
      WebDriver driver = new ChromeDriver(op);
      driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
      //driver.get("https://www.redbus.in/");
      driver.quit();
   }
}