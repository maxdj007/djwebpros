package com.djwebpros.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HandShakeController {

	@RequestMapping("/hello")
	public @ResponseBody List<String> handShakeInitializer(){
		System.out.println("I am here");
		List<String> newOne = new ArrayList<String>();
		newOne.add("sdfsdf");
		newOne.add("sdfsdf");
		newOne.add("sdfsdf");
		newOne.add("sdfsdf");
		newOne.add("sdfsdf");
		return  newOne;
	}
	
}
