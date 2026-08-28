package com.kart.utilities;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.kart.tests.BaseTest;

public class ExtentReporting implements ITestListener, ISuiteListener {
	ExtentSparkReporter spark;
	ExtentReports er;
	ExtentTest test;
	String path = System.getProperty("user.dir") + "/extentReports/report.html";

	//Create ExtentTest only once in onTestStart → reuse it in success/failure/skip.
	//Use absolute paths for screenshots → ensures they display in report.html.

	//private static boolean isConfigured = false;

	public void reportConfig() {
		//if (!isConfigured) {
			spark = new ExtentSparkReporter(path);
			er = new ExtentReports();
			er.attachReporter(spark);

			spark.config().setDocumentTitle("VegKart_DSR");
			spark.config().setReportName("VegKart_AutomationReportStatus");
			spark.config().setTheme(Theme.DARK);

			er.setSystemInfo("QA", "Ankit Kumar");
			er.setSystemInfo("OS", OS.FAMILY_WINDOWS);
			er.setSystemInfo("System Name", "Windows11");
			er.setSystemInfo("Environment", "Test");

			//isConfigured = true;
		//}
	}


	public void onStart(ISuite suite) {
		reportConfig();
	}

	public void onFinish(ISuite suite) {
		er.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = er.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,
				MarkupHelper.createLabel("Passed Test Case : " + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Failed Test Case : " + result.getName(), ExtentColor.RED));

		String destination = System.getProperty("user.dir") + "/failTestScreenshots/"
				+ BaseTest.currentDateTime() + result.getName() + ".png";

		WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
		BaseTest.captureFullPageOnFail(driver, destination);

		BaseTest.captureFullPageOnFail(driver,destination);

		try {
			test.addScreenCaptureFromPath(destination, "Failure Screenshot");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkip(ITestResult result) {
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Skipped Test Case : " + result.getName(), ExtentColor.AMBER));
	}
}
