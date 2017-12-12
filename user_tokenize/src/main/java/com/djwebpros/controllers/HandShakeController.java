package com.djwebpros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djwebpros.commons.Constants;
import com.djwebpros.models.User;
import com.djwebpros.models.UserResponseModel;
import com.djwebpros.service.UserService;

@Controller
public class HandShakeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public @ResponseBody UserResponseModel handShakeInitializer(){
		UserResponseModel newResponse = new UserResponseModel();
		int id =1;
		User user = userService.getUserById(id);
		newResponse.setError(false);
		newResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
		newResponse.setMessage("This is a test message");
		newResponse.setUser(user);
		return  newResponse;
	}
	
}
