package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter("\\.reports\\"+repName); //specify the location of the report
		//sparkReporter = new ExtentSparkReporter("./reports/" + repName);
		//sparkReporter = new ExtentSparkReporter("C:/Users/shyam/eclipse-workspace/PetStoreAutomation_DataDriven/reports/" + repName);

		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of project
		sparkReporter.config().setReportName("Pet Store User API");  //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("Oparating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","shyam");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
//		test.log(Status.SKIP, "Test Skipped");
//		test.log(Status.SKIP,result.getThrowable().getMessage()); 
	}
	
public void onTestFailure(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
//		test.log(Status.SKIP, "Test Skipped");
//		test.log(Status.SKIP,result.getThrowable().getMessage()); 
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}

public void onTestSkipped(ITestResult result) {
	
	test=extent.createTest(result.getName());
	test.createNode(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	//test.log(Status.PASS, "Test Failed");
	test.log(Status.SKIP, "Test Skipped");
	test.log(Status.SKIP,result.getThrowable().getMessage()); 
	//test.log(Status.FAIL, result.getThrowable().getMessage());
}

public void onFinish(ITestResult result) {
	extent.flush();
	
}
}
