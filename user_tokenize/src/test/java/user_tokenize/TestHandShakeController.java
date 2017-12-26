package user_tokenize;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;

import com.djwebpros.JWT.JWTokenCreator;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.Utility;
import com.djwebpros.responses.HandshakeResponseModel;
import com.djwebpros.responses.JWTMethodReturn;
import com.djwebpros.responses.RequestValidationModel;

import mockit.Deencapsulation;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class TestHandShakeController {
	
	com.djwebpros.controllers.HandShakeController HandShakeController = new com.djwebpros.controllers.HandShakeController();
	@Mocked HttpHeaders headers;
	@Mocked Utility utility;
	@Mocked JWTokenCreator jwtokenCreator;
	@Mocked RequestValidationModel requestValidationModel;
	@Mocked JWTMethodReturn jWTMethodReturn;
	@Test
	public void testhandShakeInitializer(){
		
		new NonStrictExpectations() {
			{
				Deencapsulation.setField(HandShakeController, "utility", utility);
				
				Deencapsulation.setField(HandShakeController, "jwtokenCreator", jwtokenCreator);
				
				utility.validateRequest((JSONObject)any, (HttpHeaders)any, anyString);
				result = requestValidationModel;
				
				requestValidationModel.isRequestValid();
				result = true;
				
				jwtokenCreator.createJWT(null);
				result = jWTMethodReturn;
				
			}
		};
		
		HandshakeResponseModel result = HandShakeController.handShakeInitializer("{ \"test\":\"abcd\"}", headers);
		assertEquals(result.getMessage(),Constants.STANDARD_SUCCESS_MESSAGE);
	}

}
