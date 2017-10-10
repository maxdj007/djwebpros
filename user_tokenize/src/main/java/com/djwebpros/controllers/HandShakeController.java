package com.djwebpros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djwebpros.commons.Constants;
import com.djwebpros.models.Token;
import com.djwebpros.models.TokenResponseModel;

@Controller
public class HandShakeController {

	@RequestMapping("/hello")
	public @ResponseBody TokenResponseModel handShakeInitializer(){
		TokenResponseModel newToken = new TokenResponseModel();
		Token token = new Token();
		token.setId(1);
		token.setToken("cooltoken");
		token.setLoggedIn(0);
		token.setValid(false);
		token.setUser_hash("afddsfasdfsdfasdfasdfasfd");
		newToken.setToken(token);
		newToken.setError(false);
		newToken.setMessage("hurray");
		newToken.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
		return  newToken;
	}
	
}
