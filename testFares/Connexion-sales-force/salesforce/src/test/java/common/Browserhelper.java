package common;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Utils;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class Browserhelper {

	public static WebDriver driver;

	public static void openBrowserInMaxWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static void navigate(WebDriver driver, String URl) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URl);
	}


	
	
	public static WebDriver initializeDriver(WebDriver driver) throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications"); //disable popup notification
		System.setProperty("webdriver.chrome.driver", Utils.getProperty("TestDriverPath"));
		driver =new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		return (driver);
	}
	


	public static void clickOnElement(WebDriver driver, WebElement element) throws Exception {
		try {
			new Actions(driver).moveToElement(element).perform();
			element.click();
		} catch (Exception notInScreen) {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
			} catch (Exception mouseClick) {
				try {
					if (!element.getTagName().equals("button")) {
						throw new Exception();
					}
					element.sendKeys(Keys.RETURN);
				} catch (Exception enterClick) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				}
			}
		}
		Thread.sleep(1000);
	}

	public static void doubleClickOnElement(WebDriver driver, WebElement element) throws Exception {
		new Actions(driver).doubleClick(element).perform();
	}

	public static void checkNotificationAppearance(WebDriver driver, String type, String message) throws Exception {
		String expectedType = null;
		if (type != null) {
			expectedType = type.substring(0, 1).toUpperCase() + type.substring(1);
		}
		List<WebElement> notificationBox = driver
				.findElements(By.cssSelector("div.toast-error,div.toast-success div.toast-message"));
		assertEquals("Notification displayed : true", "Notification displayed : " + (notificationBox.size() >= 1));
		String actualType = driver.findElement(By.cssSelector("div.toast div.toast-title")).getAttribute("aria-label");
		if (expectedType == null) {
			expectedType = driver.findElement(By.cssSelector("div.toast div.toast-title")).getAttribute("aria-label");
		}
		if (actualType.toUpperCase().contains(expectedType.toUpperCase()) && expectedType != null) {
			actualType = expectedType;
		}
		String actualMessage = driver.findElement(By.cssSelector("div.toast div.toast-message"))
				.getAttribute("innerHTML");
		if (!actualMessage.matches(message) || !expectedType.equals(actualType)) {
			assertEquals(expectedType + " Notification :" + message, actualType + " Notification :" + actualMessage);
		}
		Browserhelper.clickOnElement(driver, notificationBox.get(0));
	}

	public static void checkInfoNotfifcationAppearance(WebDriver driver, String type, String message) throws Exception {
		String expectedType = type.substring(0, 1).toUpperCase() + type.substring(1);
		List<WebElement> notificationBox = driver.findElements(By.cssSelector("div.toast div.toast-message"));
		assertEquals("Notification displayed : true", "Notification displayed : " + (notificationBox.size() >= 1));
		String actualType = driver.findElement(By.cssSelector("div.toast div.toast-title")).getAttribute("aria-label");
		String actualMessage = driver.findElement(By.cssSelector("div.toast div.toast-message"))
				.getAttribute("innerHTML");
		if (!actualMessage.matches(message) || !expectedType.equals(actualType)) {
			assertEquals(expectedType + " Notification :" + message, actualType + " Notification :" + actualMessage);
		}
		Browserhelper.clickOnElement(driver, notificationBox.get(0));
	}

	/*public static void sortTableByCreteriaAsc(WebDriver driver, String creteria) throws Exception {
		List<WebElement> element = driver.findElements(By.xpath("//th[contains(text(),'" + creteria + "')]//i"));
		assertEquals("Exist Creteria : true", "Exist Creteria : " + (element.size() > 0));
		Browserhelper.clickOnElement(driver,
				driver.findElement(By.xpath("//th[contains(text(),'" + creteria + "')]//i")));
		Browserhelper.waitForPageLoading(driver);
	}*/

	public static void checkTableSortingByCreteria(WebDriver driver, String creteria) throws Exception {
		String previousValue = null, actualValue = null;
		List<WebElement> elements = driver.findElements(By
				.xpath("//td[count(//table//th[normalize-space()='" + creteria + "']/preceding-sibling::*)+1]//span"));
		if (elements.size() > 0) {
			previousValue = elements.get(0).getText();
		}
		for (int i = 1; i < elements.size(); i++) {
			actualValue = elements.get(i).getText();
			System.out.println("Comparring :" + previousValue + " And " + actualValue);
			assertEquals("Sorted : true", "Sorted : " + (actualValue.compareTo(previousValue) >= 0));
			previousValue = actualValue;
		}
	}

	public static void setTablePagination(WebDriver driver) throws Exception {
		String xPath = "//siconia-lib-data-table-card//select[1]";
		List<WebElement> selectElement = driver.findElements(By.xpath(xPath));
		if (selectElement.size() > 0) {
			Select selectInput = new Select(selectElement.get(0));
			((JavascriptExecutor) driver).executeScript(
					"let select = (document.evaluate(\"//div[@class='row mb-3']/div[@class='col-md-8 d-flex flex-row-reverse']"
							+ "/div[@class='form-inline']/div[@class='form-group']/select\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue);  "
							+ "select.innerHTML=select.innerHTML+'<OPTION value=\"100000\">All elements</OPTION>';");
			selectInput.selectByValue("100000");
			Thread.sleep(5000);
		}
	}

	public static void checkPupUpAppearance(WebDriver driver) throws Exception {
		List<WebElement> pupUpButton = driver.findElements(By.xpath("//div[@class='modal-content']"));
		assertEquals("PupUp displayed : true", "PupUp displayed : " + (pupUpButton.size() >= 1));
	}

	public static void clickOnPupupButton(WebDriver driver, String button) throws Exception {
		if (driver.findElements(By.xpath("//div[@class='modal-content']//button[contains(text(),'" + button + "')]"))
				.size() > 0) {
			WebElement pupUpButton = driver
					.findElement(By.xpath("//div[@class='modal-content']//button[contains(text(),'" + button + "')]"));
			Browserhelper.clickOnElement(driver, pupUpButton);
		} else {
			assertEquals("Exist Pupup Button : true", "Exist Pupup Button : false");
		}
	}

	public static Boolean existPupUpButton(WebDriver driver, String button) throws Exception {
		if (driver.findElements(By.xpath("//div[@class='modal-content']//button[contains(text(),'" + button + "')]"))
				.size() > 0) {
			return (true);
		} else {
			return (false);
		}
	}

	/*public static void waitForNotification(WebDriver driver, String type, Integer timeOut) throws Exception {
		try {
			WebElement until = new WebDriverWait(driver, 100).until(
					(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast  div.toast-message"))));
		} catch (Exception e) {
		}
	}

	public static void waitForPageLoading(WebDriver driver) throws Exception {
		WebElement until = new WebDriverWait(driver, 60)
				.until((ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ngx-spinner"))));
	}

	public static void waitForPageLoading(WebDriver driver, Integer waitTime) throws Exception {
		new WebDriverWait(driver, 60)
				.until((ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ngx-spinner"))));
		Thread.sleep(waitTime);
	}*/

	public static void authenticateInQa(WebDriver driver) throws Exception {
		Browserhelper.openBrowserInMaxWindow(driver);
		Browserhelper.navigate(driver, Utils.getProperty("Qa_Fe_Home_Page"));
		WebElement loginField = driver.findElement(By.id("username"));
		loginField.sendKeys(Utils.getProperty("TestUserName"));
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(Utils.getProperty("TestPassword"));
		WebElement button = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		Browserhelper.clickOnElement(driver, button);
		Browserhelper.checkAuthentication(driver);
	}

	

	public static void authenticateInOdm(WebDriver driver) throws Exception {
		Browserhelper.openBrowserInMaxWindow(driver);
		Browserhelper.navigate(driver, Utils.getProperty("Odm_Fe_Home_Page"));
		//Browserhelper.waitForPageLoading(driver);
		WebElement loginField = driver.findElement(By.id("username"));
		loginField.sendKeys(Utils.getProperty("TestUserName"));
		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys(Utils.getProperty("TestPassword"));
		WebElement button = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		Browserhelper.clickOnElement(driver, button);
		Browserhelper.checkAuthentication(driver);
	}

	public static List<Hashtable<String, String>> getTableValues(WebElement table) {
		Integer rowCount = table.findElements(By.xpath("//tbody//tr//td[2]")).size();
		List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
		List<Hashtable<String, String>> result = new ArrayList<Hashtable<String, String>>();
		if (rowCount > 0) {
			for (int i = 0; i < rows.size(); i++) {
				Hashtable<String, String> hashRow = new Hashtable<String, String>();
				List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
				for (int k = 0; k < columns.size(); k++) {
					hashRow.put(table.findElement(By.xpath("//th[" + (k + 1) + "]")).getText(),
							columns.get(k).getText().trim());
				}
				result.add(hashRow);
			}
		}
		return (result);
	}

	/* ToRemove - Temporary Function */
	public static void resolveTagNameError(WebDriver driver) {
		List<WebElement> tagNames = driver.findElements(By.xpath("//*[@name='tagName' or @id='tabName']"));
		for (int i = 0; i < tagNames.size(); i++) {
			if (tagNames.get(i).getAttribute("id").toUpperCase().equals("TAGNAME")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('id', 'Modified')",
						tagNames.get(i));
			}
			if (tagNames.get(i).getAttribute("name").toUpperCase().equals("TAGNAME")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('name', 'Modified')",
						tagNames.get(i));
			}
		}
	}

	public static void checkAuthentication(WebDriver driver) {
		String currentPath = driver.getCurrentUrl();
		assertEquals("Logged in : true", "Logged in : " + !currentPath.contains("cas/login"));
	}

}
