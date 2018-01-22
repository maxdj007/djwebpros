<?php
class Routes {
	
	// Contollers to be added to the app.
	private $controllers = "";
	private $controller = "";
	private $action = "";
	private $request = "";
	private $validator = "";
	/** @var object */
	private static $_instance;
	
	public function __construct($controller, $action, $request){
		require_once('registerControls.php');
		$registerControls = new RegisterControls();
		$this->controllers = $registerControls->getRegisteredControllers();
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
		$this->validator = new TokenValidatorImpl();
	}
	
	function call($controller, $action, $errorMessage="") {
				
		if($controller !== "ErrorHandler"){
			foreach($this->controllers as $key=>$value){
				if($key === $controller){
					$controller = $controller."Controller";
					$controller = new $controller($controller, $action, $this->request);
					break;
				}
			}
		} else {
			$controller = new ErrorHandlerController($controller, $action, $this->request, $errorMessage);
		}
		
		$controller->{ $action } ();
	}
	
	public function initialize() {
		if($this->validator->validateToken(getallheaders())){
			$this->callControlers();
		}
	}
	
	public function callControlers(){
		if (array_key_exists ( $this->controller, $this->controllers)) {
			if (in_array ( $this->action, $this->controllers[$this->controller] )) {
				$this->call($this->controller, $this->action);
			} else {
				$this->call ( 'ErrorHandler', 'error', '' );
			}
		} else {
			$this->call ( 'ErrorHandler', 'error', '' );
		}
	}
	
	public static function init($controller, $action, $request){
		if(self::$_instance == null) self::$_instance = new self($controller, $action, $request);
		return self::$_instance;
	}
}
?>