package freddie;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppTest {

	private String rootDir = "C:\\HexawareTraining\\Materials\\Software\\Selenium-BDD";
	private String browserDriver = "geckodriver.exe";
	private WebDriver driver;
	private String email = "hexa_user@sdettraining.com";
	private String password = "password";


	@Before
	public void setUp() throws Exception {
		// Initialize the Driver
		System.out.println("Starting web browser");
		System.setProperty("webdriver.gecko.driver", rootDir + "\\" + browserDriver);
		driver = new FirefoxDriver();
		driver.get("http://localhost:8090/webapp/");
		Thread.sleep(1500);
	}

	@After
	public void tearDown() throws Exception {
		// Close the Driver
		driver.quit();
	}

	@Test
	public void loginTest() throws InterruptedException {
		System.out.println("Filling out form");
		driver.findElement(By.name("username")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);

		System.out.println("Page title: " + driver.getTitle());
		Assert.assertTrue("Login Page Title Checkpoint", driver.getTitle().contains("Freddie Mac Training"));
	}
}
