package com.bobcard.test.test_rzp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.razorpay.Order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import okhttp3.Response;

import java.util.HashMap;

import org.json.JSONObject;

@Controller
public class RzpController {
	
	@Autowired
	private RzpModel rzpModel;
	
	@Autowired
	private RzpService rzp;
	
	@Value("${rzp.apikey}")
	public String rzpApiKey;
	
	@RequestMapping("/rzp")
	public ModelAndView rzp(@RequestParam("amt") double amt,ModelAndView mv) {
		
		rzpModel.setAmt(amt);
		rzpModel.setRzpAmount(rzpModel.getAmt());
		rzp.createOrder(rzpModel.getRzpAmount());
		mv.addObject("rzpModel",rzpModel);
		
		mv.addObject("rzpSecretKey",rzpApiKey);		
		mv.setViewName("rzp");
		System.out.println("rzpApiKey : " + rzpApiKey);
//		return "rzp";
		return mv;
	}
	
	@RequestMapping("/wait")
	public ModelAndView wait(ModelAndView mv) {
		
		mv.setViewName("wait");
		return mv;
	}
	
	@RequestMapping({"/","/test"})
	public String test(HttpServletRequest req,HttpSession session) {
		
		String path = req.getServletPath();
		session.removeAttribute("path");
		
//		System.out.println(path.equals("/test"));
		if(path.equals("/test")) {
			System.out.println("in /test url " + req.getParameter("amt"));
			session.setAttribute("path", path);
		}
		return "test";
	}
	
//	ResponseEntity // important stuff need to look into it
	
	@RequestMapping("/rzpOrderId1")
	@ResponseBody
//	public ResponseEntity<Response> rzpRestApi(@RequestParam("amt") double amt) {
		public String rzpRestApi(@RequestParam("amt") double amt) {

//		System.out.println("amt in controller: "+ amt);
		
		rzpModel.setAmt(amt);
//		System.out.println("req in /rzpOrderId amount: " + rzpModel.getAmt());
		rzpModel.setRzpAmount(rzpModel.getAmt());
//		System.out.println("req in /rzpOrderId rzpAmount:" + rzpModel.getRzpAmount());
		Order order = rzp.createOrder(rzpModel.getRzpAmount());
		System.out.println("order in controller :" + order);
//		return order;
		
		String resp = order.toString();
		HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");
		
		return resp;
		
		
//		return order.get("id");
//		return ResponseEntity.ok(order);

	}

}
