<?php
require_once('Controller.php');
class RetrieveController extends Controller {
 
	public function __construct($controller, $action, $request) {
		self::$controller = $controller;
		self::$action = $action;
		self::$request = $request;
	}
	
	public function image() {
		// here we take out the image from the database to return.
		
		
		
	}
}
?>