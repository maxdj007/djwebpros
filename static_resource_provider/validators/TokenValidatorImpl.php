<?php
require_once('./JWT/Jwt.php');
require_once('./JWT/FactoryTrait.php');
require_once('./JWT/Token.php');
class TokenValidatorImpl implements TokenValidator {
	
	private $jwt = "";
	
	public function __construct(){
		$this->requestDataHelper = new RequestDataHelper();
		$this->utility = new Utility();
		$this->jwt = new Emarref\Jwt\Jwt();
	}
	
	public function validateToken($headers){
		if(array_key_exists(HEADER_PARAMETER_TOKEN, $headers) && !empty($headers[HEADER_PARAMETER_TOKEN])){
			$token = $this->jwt->deserialize($headers[HEADER_PARAMETER_TOKEN]);
			$algorithm = new Emarref\Jwt\Algorithm\Hs256(JWT_SECRET_KEY);
			$encryption = Emarref\Jwt\Encryption\Factory::create($algorithm);
			$context = new Emarref\Jwt\Verification\Context($encryption);
			$context->setIssuer(JWT_AUTHOR);
			$context->setAudience(JWT_AUDIENCE_GUEST);
			try {
				$this->jwt->verify($token, $context);
				return true;
			} catch (Emarref\Jwt\Exception\VerificationException $e) {
				$this->utility->checkJWTException($e);
			}
		} else {
			echo json_encode(array('error'=>true,'message'=>ERROR_NO_HEADER_TOKEN));
			exit();
		}
	}
	
}

?>