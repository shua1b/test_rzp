package com.bobcard.test.test_rzp;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@RestController
public class RzpRestController {
	
	@Autowired
	private RzpService rzp;
	
	@Autowired
	private RzpModel rzpModel;
	
	@Value("${rzp.webhook.secret}")
	public String rzpWebhookSecret;
	
	
	@CrossOrigin(origins = {"http://localhost","capacitor://localhost"}
//	,allowedHeaders={"Access-Control-Request-Headers","Access-Control-Allow-Origin"}
	)
	@RequestMapping("/rzpOrderId")
	public HashMap rzpRestApi(@RequestParam("amt") double amt) {
		
//		System.out.println("amt in controller: "+ amt);
		
		rzpModel.setAmt(amt);
//		System.out.println("req in /rzpOrderId amount: " + rzpModel.getAmt());
		rzpModel.setRzpAmount(rzpModel.getAmt());
//		System.out.println("req in /rzpOrderId rzpAmount:" + rzpModel.getRzpAmount());
		Order order = rzp.createOrder(rzpModel.getRzpAmount());
//		System.out.println("order in controller :" + order);
		JSONObject response = order.toJson();
//		System.out.println("response in controller :" + response);
//		System.out.println("response.toString() in controller :" + response.toString());
//		return order.get("id");
//		return order;
		
		HashMap<String, Object> map = new HashMap<>();
	    map.put("amount",order.get("amount"));
	    map.put("order_id",order.get("id"));
	    System.out.println("map in controller: " + map);
	    return map;

	}
	
//	/rzp-webhook
	
	@RequestMapping("/rzp-webhook")
	public ResponseEntity<String> rzpWebhook(
	@RequestHeader("X-Razorpay-Signature") String rzpSignature ,
	@RequestBody String data) {
		
//		rzpModel.setAmt(amt);
//		rzpModel.setRzpAmount(rzpModel.getAmt());
		System.out.println("data in /rzp-webhook : " + data);
		
		/*
		HashMap<String, Object> map = new HashMap<>();
	    map.put("amount",order.get("amount"));
	    map.put("order_id",order.get("id"));
	    System.out.println("map in controller: " + map);
	    return map;
	    */
		JSONObject jsonData = new JSONObject(data);
		System.out.println("jsonData: " + jsonData);
		
		// pick header from webhook
		// X-Razorpay-Signature
		System.out.println("rzp data.toString() : " + data);
		System.out.println("rzp signature : " + rzpSignature);
		System.out.println("rzp Webhook Secret : " + rzpWebhookSecret);
		
		// Do Not Parse or Cast the Webhook Request Body
		
		try {
			boolean webhookSignature =  Utils.verifyWebhookSignature(data,rzpSignature,rzpWebhookSecret);
			System.out.println("webhookSignature check : " +webhookSignature);
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("webhook received");

	}

}
