<?php
use Emarref\Jwt\Jwt;
require_once('Controller.php');
require_once('./JWT/Jwt.php');
require_once('./JWT/FactoryTrait.php');
require_once('./JWT/Token.php');
require_once ('./helpers/RequestDataHelper.php');
require_once ('./helpers/Utility.php');
class CheckTokenController extends Controller{
	private $jwt = "";
	public function __construct($controller, $action, $request){
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
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
				echo $e->getMessage();
			}
		} else {
			echo json_encode(array('error'=>true,'message'=>ERROR_NO_PARAMETERS));
		}
	}
	
}

?>