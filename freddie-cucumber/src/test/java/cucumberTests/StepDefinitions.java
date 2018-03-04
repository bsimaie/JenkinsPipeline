package cucumberTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDefinitions {
	WebDriver driver;
	private String rootDir = "C:\\HexawareTraining\\Materials\\Software\\Selenium-BDD";
	private String browserDriver = "chromedriver.exe";

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", rootDir + "\\" + browserDriver);
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

	@Given("^the user navigates to our home page$")
	public void the_user_navigates_to_our_home_page() throws Throwable {
		driver.get("http://localhost:8877/webapp/");
	}

	@Then("^the user should enter \"(.*)\" and \"(.*)\"$")
	public void the_user_should_see_the_message(String email, String password) throws Throwable {
			driver.findElement(By.name("username")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);
	}

}
