<?php

class TokenValidatorImpl implements TokenValidator {
	
	private $jwt = "";
	
	public function __construct(){
		$this->requestDataHelper = new RequestDataHelper();
		$this->utility = new Utility();
		$this->jwt = new Emarref\Jwt\Jwt();
	}
	
	public function checkToken(){
		if(sizeof($parameters = $this->requestDataHelper->convertJSONToArray($this->request[POST_PARAMETERS_RAW_DATA])) != 0){
			$token = $this->jwt->deserialize($parameters[HEADER_PARAMETER_TOKEN]);
			$algorithm = new Emarref\Jwt\Algorithm\Hs256(JWT_SECRET_KEY);
			$encryption = Emarref\Jwt\Encryption\Factory::create($algorithm);
			$context = new Emarref\Jwt\Verification\Context($encryption);
			$context->setIssuer(JWT_AUTHOR);
			$context->setAudience(JWT_AUDIENCE_GUEST);
			try {
				echo $this->jwt->verify($token, $context);
			} catch (Emarref\Jwt\Exception\VerificationException $e) {
				 echo get_class($e);
			}
		} else {
			echo json_encode(array('error'=>true,'message'=>ERROR_NO_PARAMETERS));
		}
	}
	
}

?>