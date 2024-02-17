package com.project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AmazonAutomation {

	WebDriver driver;

	@BeforeClass

	public void Launch()

	{

		System.setProperty("webdriver.chrome.driver",
				"D:\\deni_software testing\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://amazon.in");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test(priority = 1)

	public void verifyTitle()

	{

		String actualTitle = driver.getTitle();

		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

		Assert.assertEquals(actualTitle, expectedTitle);

	}

	@Test(priority = 2)

	public void verifyLogo()

	{

		boolean Lg = driver.findElement(By.xpath("//a[@id='nav-logo-sprites']")).isDisplayed();

		Assert.assertTrue(Lg);

	}

	@Test(priority = 3)

	public void searchbox() {

		WebElement sb = driver.findElement(By.id("twotabsearchtextbox"));
		sb.sendKeys("iphone 15 pro max");

	}

	@Test(priority = 4)

	public void searchbutton() {

		WebElement SB = driver.findElement(By.id("nav-search-submit-button"));
		SB.click();

	}

	@Test(priority = 5, dependsOnMethods = "searchbutton")

	public void Screenst() throws IOException {
		TakesScreenshot t = (TakesScreenshot) driver;
		File scr = t.getScreenshotAs(OutputType.FILE);
		File sht = new File("C:\\Users\\Denison\\Screenshots\\automate.jpeg");
		FileHandler.copy(scr, sht);

	}

	@Test(priority = 6)

	public void product() {

		WebElement pdt = driver
				.findElement(By.xpath("//img[@alt='Sponsored Ad - Apple iPhone 15 Pro Max (256 GB) - Blue Titanium']"));

		pdt.click();

	}

	@Test(priority = 7)

	public void clear() {

		WebElement cl = driver.findElement(By.id("twotabsearchtextbox"));
		cl.clear();

	}

	@Test(priority = 8)

	public void newproduct() {

		WebElement np = driver.findElement(By.id("twotabsearchtextbox"));
		np.sendKeys("iphone 14");

		WebElement en = driver.findElement(By.id("nav-search-submit-button"));
		en.click();
	}

	@Test(priority = 9)

	public void Switch() {
		String parentWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(parentWindowHandle);

	}

	@Test(priority = 10)

	public void scroll() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900);");
		Thread.sleep(5000);

	}

	@Test(priority = 11)

	public void select() {
		WebElement st = driver.findElement(By.xpath("//img[@alt='Apple iPhone 14 (512 GB) - Starlight']"));
		st.click();
	}

	@Test(priority = 12)
	public void location() throws InterruptedException {

		WebElement lc = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		Point v = lc.getLocation();
		int a = v.getX();
		int b = v.getY();
		System.out.println(a);
		System.out.println(b);
		Thread.sleep(5000);

		lc.click();

		String childWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(childWindowHandle);

	}

	@Test(priority = 13)

	public void Email() {
		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys("arodenison1999@gmail.com");

		WebElement con = driver.findElement(By.id("continue"));
		con.click();
	}

	@Parameters({ "Password" })
	@Test(priority = 14)

	public void Security(String Passw) {

		WebElement Pass = driver.findElement(By.id("ap_password"));
		Pass.sendKeys(Passw);

		WebElement sign = driver.findElement(By.id("signInSubmit"));
		sign.click();

	}

	@Test(priority = 15)

	public void Assert() {

		String title = driver.getTitle();

		String ActualTitle = "Authentication required";
		String ExpectedTitle = title;

		Assert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println(title);
	}

	@Test(priority = 16)

	public void Captcha() {

		WebElement cap = driver.findElement(By.xpath("//input[@type='text']"));
		cap.sendKeys("pdwhsx");

		WebElement con = driver.findElement(By.xpath("//input[@name='cvf_captcha_captcha_action']"));
		con.click();

	}

}
