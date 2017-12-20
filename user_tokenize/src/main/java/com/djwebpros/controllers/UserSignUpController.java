package com.djwebpros.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djwebpros.service.TokenService;
import com.djwebpros.service.UserService;

/**
 * 
 * @author DJ
 *
 */
@Controller
public class UserSignUpController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String signUp(@RequestBody String postPayload){
		JSONObject postJSONData = new JSONObject(postPayload);
		return  null;
	}
}
