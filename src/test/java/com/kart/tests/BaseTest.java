package com.kart.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.kart.utilities.LogHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	// ThreadLocal ensures each parallel thread gets its own driver
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger log = LogHelper.getLogger(BaseTest.class);
	@BeforeMethod
	public void setUp() {
	    ChromeOptions co = new ChromeOptions();
	    // co.addArguments("--incognito");

	    WebDriverManager.chromedriver().setup();
	    WebDriver driverInstance = new ChromeDriver();

	    setDriver(driverInstance); // ✅ store in ThreadLocal

	    getDriver().get("https://rahulshettyacademy.com/seleniumPractise");
	    getDriver().manage().window().maximize();
	    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    log.info("SetUp done!");
	}


//	@BeforeMethod
//	@Parameters({"browser", "platform"})
//	public void setUp(String browser, String platform) throws MalformedURLException {
//		System.out.println("Inside initial setUp");
//		WebDriver driverInstance;
//
//		if (browser.equalsIgnoreCase("chrome")) {
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--start-maximized");
//			options.setCapability("platformName", platform); // e.g., WINDOWS, LINUX, MAC
//			driverInstance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//			System.out.println("Chrome launched successfully");
//
//		} else if (browser.equalsIgnoreCase("firefox")) {
//			FirefoxOptions options = new FirefoxOptions();
//			options.setCapability("platformName", platform);
//			driverInstance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//
//		} else if (browser.equalsIgnoreCase("edge")) {
//			EdgeOptions options = new EdgeOptions();
//			options.setCapability("platformName", platform);
//			driverInstance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//		}
//		else {
//			throw new IllegalArgumentException("Unsupported browser: " + browser);
//		}
//
//		setDriver(driverInstance);
//
//		getDriver().get("https://rahulshettyacademy.com/seleniumPractise");
//		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		log.info("Remote WebDriver initialized for " + browser + " on " + platform);
//	}


	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	} 

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
	    try {
	        if (getDriver() != null) {
	            getDriver().quit();
	        }
	    } finally {
	        driver.remove(); // clears ThreadLocal
	    }
	    log.info("Driver closed and slot freed.");
	}

	public static String currentDateTime() {
		LocalDateTime ldt = LocalDateTime.now();		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy_HH-mm-ss");
		return ldt.format(dtf); //Stringify
	}

	public static void captureFullPageOnFail(WebDriver driverInstance, String destination) {
	    if (driverInstance == null) {
	        System.out.println("Driver is null, cannot capture screenshot!");
	        return;
	    }
	    try {
	        File src = ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.FILE);
	        File dest = new File(destination);
	        FileUtils.copyFile(src, dest);
	        System.out.println("Screenshot saved at: " + destination);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
