package com.springboot.webanalytics.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {	
	
	@RequestMapping("/")
	public void test() {
		System.out.println("-- inside test controller --");
		
	}
	
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-- inside welcome controller --");
		
		return "welcome";
	}
	
	@RequestMapping("/track")
	public String track(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-- inside track method -- ");
		
		try {
			//Decode query string from request
			String decodedData = URLDecoder.decode(request.getQueryString(), "UTF-8");
			String decodedJsonString = decodedData.substring(5, decodedData.length());
			System.out.println("-- jsonString -- " + decodedJsonString);
			
			//create map of request data to update additional information before writing to file
			BasicJsonParser jsonParser = new BasicJsonParser();
		    Map<String, Object> jsonMap = null;
		    jsonMap = jsonParser.parseMap(decodedJsonString);
			
			JSONObject analyticsData = new JSONObject();
			analyticsData.put("time", jsonMap.get("t"));
			analyticsData.put("event", jsonMap.get("e"));
			
			Map<String, Object> properties = (Map<String, Object>) jsonMap.get("kv");
			properties.put("ip", request.getRemoteAddr());
			properties.put("origin", request.getRemoteHost());
			properties.put("page", request.getHeader("referer"));
			properties.put("useragent", request.getHeader("User-Agent"));
			
			analyticsData.put("properties", properties);
			System.out.println("-- analytics data before wrting to file -- " + analyticsData.toJSONString());
			
			//Write final analytics data in JSON format
			FileWriter file = new FileWriter("/tmp/analytics.json", true);
			BufferedWriter out = new BufferedWriter(file);
			out.write(analyticsData.toJSONString());
			out.newLine();
		    out.flush();
		    out.close();
			System.out.println("Data written to file successfully...");
			
			//Check and update cookies
			Cookie[] existingCookies = request.getCookies();
			if (existingCookies == null) {
				System.out.println("set visitor id");
				Cookie cookie = new Cookie("visitorID", properties.get("id").toString());
				response.addCookie(cookie);
				response.setStatus(200);
				
			} else {
				System.out.println("cookie exists. update session id");
				Boolean sessionIdFlag = false;
				for(Cookie c : existingCookies) {
					if(c.getName() == "sessionId") {
						c.setValue(request.getSession().getId());
						sessionIdFlag = true;
					}
				}
				if (!sessionIdFlag) {
					Cookie cookie = new Cookie("sessionID", request.getSession().getId());
					response.addCookie(cookie);
					response.setStatus(200);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return "welcome";
	}
	

}
