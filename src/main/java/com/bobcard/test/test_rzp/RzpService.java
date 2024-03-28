package com.bobcard.test.test_rzp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;


@Service
public class RzpService {
	
	@Autowired
	public RzpModel rzpModel;
	
	@Value("${rzp.apikey}")
	public String rzpApiKey;
	
	@Value("${rzp.secretkey}")
	public String rzpSecretKey;
	
//	public RzpModel createOrder(double rzpAmount) {
	public Order createOrder(double rzpAmount) {
		
		System.out.println("Rzp Service createOrder : " + rzpAmount );
		
//		/*
		try {
//		RazorpayClient razorpay = new RazorpayClient("rzp_test_q6Wmgnzm2Tc0AH","2K5ny5Cx8lrVcVxlMzsbEHhF");
		RazorpayClient razorpay = new RazorpayClient(rzpApiKey,rzpSecretKey);

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount",rzpAmount);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt#1");
		JSONObject notes = new JSONObject();
		notes.put("notes_key_1","Tea, Earl Grey, Hot");
		orderRequest.put("notes",notes);

		Order order = razorpay.orders.create(orderRequest);
		
//		System.out.println("check order " + order);
//		System.out.println("check orderId from resp: " + order.get("id"));
		rzpModel.setOrderId(order.get("id"));
//		System.out.println("getOrderId in service: " + rzpModel.getOrderId());
		
		return order;
		
		}
		catch (RazorpayException e) {
			
			System.out.println("order generation error" + e );
			System.out.println("razorpay error: " + e.getMessage() );
			System.out.println("razorpay getLocalizedMessage error: " + e.getLocalizedMessage() );
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
//		*/
	}

}
