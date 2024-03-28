package com.bobcard.test.test_rzp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.json.JSONObject;
import com.razorpay.*;

@SpringBootApplication
public class TestRzpApplication {
	
	private static String orderId;
	
	public static void main(String[] args) {
		SpringApplication.run(TestRzpApplication.class, args);
		
		System.out.println("hello World test_rzp");
		
//		Tomcat tomcat = new Tomcat();
//		try {
//			tomcat.start();
//			tomcat.getServer().await();
//		} catch (LifecycleException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*
		try {
		RazorpayClient razorpay = new RazorpayClient("rzp_test_q6Wmgnzm2Tc0AH","2K5ny5Cx8lrVcVxlMzsbEHhF");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",50000);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt#1");
		JSONObject notes = new JSONObject();
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		orderRequest.put("notes",notes);

//		Order order = instance.orders.create(orderRequest);
		Order order = razorpay.orders.create(orderRequest);
		
		System.out.println("check order " + order);
		orderId = order.get("id");
		System.out.println("order id: " + orderId);
		
		}
		catch (RazorpayException e) {
			
			System.out.println("order generation error" + e );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
