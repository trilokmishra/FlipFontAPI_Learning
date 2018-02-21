package Flipfont_Tags;

import java.io.File;
import java.lang.reflect.Method;

import org.junit.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class DummyForExReport {

	ExtentReports reports;
	ExtentTest testInfo;
	ExtentHtmlReporter htmlReporter; 
	
	@BeforeTest
	public void setup() {
		htmlReporter =new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"AutomationReport.html"));
	    htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	    reports = new ExtentReports();
	    reports.setSystemInfo("Environment", "QA");
	    reports.attachReporter(htmlReporter);
	}
	
	@Test public void methodOne() {
		Assert.assertTrue(true);
		testInfo.log(Status.INFO,"This is SAMPLE TESTONE");
	}
	
	@Test public void methodTwo() {
		Assert.assertTrue(false);
		testInfo.log(Status.INFO,"This is SAMPLE TESTONE");
	}
	
	@BeforeMethod
	public void register(Method method) {
		String testName = method.getName();
		testInfo= reports.createTest(testName);
		
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			testInfo.log(Status.PASS, "the Test Method named as : "+result.getName()+"is passed");
			
		}else if(result.getStatus()==ITestResult.FAILURE){
			testInfo.log(Status.PASS, "the Test Method named as : "+result.getName()+"is Failed");
			testInfo.log(Status.FAIL,"Test failure"+result.getThrowable());
			testInfo.log(Status.PASS,"The Test Method named as : "+result.getName()+"is passed");
		}
	}
		@AfterTest
		public void cleanUp() {
			reports.flush();
		}
	}
	

