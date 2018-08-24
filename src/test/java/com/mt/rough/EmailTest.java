package com.mt.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.mt.utilities.MonitoringMail;
import com.mt.utilities.TestConfig;

public class EmailTest {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		MonitoringMail email=new MonitoringMail();
		
		String myIPaddress= InetAddress.getLocalHost().getHostAddress();
		
		String msg="http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenTesting/DataDrivenProjectReport/";
		
		email.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, msg);
		
		
		System.out.println("Email Sent");
		
		
		}//main	

}//class
