<?php
class Routes {
	
	// Contollers to be added to the app.
	private $controllers = "";
	private $controller = "";
	private $action = "";
	private $request = "";
	/** @var object */
	private static $_instance;
	
	public function __construct($controller, $action, $request){
		require_once('registerControls.php');
		$registerControls = new RegisterControls();
		$this->controllers = $registerControls->getRegisteredControllers();
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
	}
	
	function call($controller, $action, $errorMessage="") {
		require_once ('controllers/' . $controller . 'Controller.php');
		
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
		if (array_key_exists ( $this->controller, $this->controllers)) {
			if (in_array ( $action, $this->controllers[$controller] )) {
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