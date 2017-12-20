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
public class UserCheckController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String loginController(@RequestBody String postPayload){
		//TODO : Add the mechanism for login
		return  "ksjhffhjklashflkasdhfkaskljfklafklashfkakf.asdfjklhasdklfasdklfhkl";
	}
	
	@RequestMapping(value = "/isLoggedIn", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String isLoggedIn(@RequestBody String postPayload){
		//TODO : Add the mechanism for login check
		JSONObject postJSONData = new JSONObject(postPayload);
		return  "ksjhffhjklashflkasdhfkaskljfklafklashfkakf.asdfjklhasdklfasdklfhkl";
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String logOutController(@RequestBody String postPayload){
		//TODO : Add the mechanism for Logout
		JSONObject postJSONData = new JSONObject(postPayload);
		return  "ksjhffhjklashflkasdhfkaskljfklafklashfkakf.asdfjklhasdklfasdklfhkl";
	}

}
