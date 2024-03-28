package com.bobcard.test.test_rzp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.razorpay.Order;

@Component
public class RzpModel {
	
	private double amt; // make it float later
	private double rzpAmount; // make it float later
	private String orderId;
	private String rzpKey;
	
	@Value("${rzp.apikey}")
	public String rzpApiKey;
	
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public double getRzpAmount() {
		return rzpAmount;
	}
	public void setRzpAmount(double rzpAmount) {
		this.rzpAmount = this.getAmt() * 100;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "RzpModel [amt=" + amt + ", rzpAmount=" + rzpAmount + ", orderId=" + orderId + "]";
	}
	
	public String getRzpKey() {
		return rzpKey;
	}
	public void setRzpKey(String rzpKey) {
		this.rzpKey = rzpApiKey;
		
	}
	public void myFunc(String resp) {
		System.out.println("my function called: " + resp);
	}

}
