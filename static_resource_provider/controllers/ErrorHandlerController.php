<?php

Class ErrorHandlerController{
	
	private $controller;
	private $action;
	private $request;
	private $errorMessage;
	
	public function __construct($controller, $action, $request, $errorMessage){
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
		$this->errorMessage = $errorMessage;
	}
	
	public function error(){
		
	}
	
}

?>