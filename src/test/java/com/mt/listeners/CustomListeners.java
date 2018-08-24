package com.mt.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.mt.base.TestBase;
import com.mt.utilities.MonitoringMail;
import com.mt.utilities.TestConfig;
import com.mt.utilities.TestUtils;
import com.relevantcodes.extentreports.LogStatus;



public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {
	public String msg;

	public void onTestStart(ITestResult result) {
		test = extentReportObject.startTest(result.getName().toUpperCase());

		
	}

	public void onTestSuccess(ITestResult result) {
		
		
		
		
		try {
			TestUtils.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.setProperty("org.uncommons.reportng.escape-output", "false"); 
		Reporter.log("<a target=_blank href=" + TestUtils.screenShotName + " > Login Screen shot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=_blank href=" + TestUtils.screenShotName + " > <img src=" + TestUtils.screenShotName + " width=30 height=30  ></img></a>");
		
		
		//here test is TestBase class member
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
		extentReportObject.endTest(test); //to close the test
		extentReportObject.flush(); //to generate the report

		
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
	MonitoringMail email=new MonitoringMail();
		
		try {
			String myIPaddress= InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			msg = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenTesting/DataDrivenProjectReport/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			email.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, msg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Email Sent");
		
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
