<?php

class Layout{
	
	private $request = "";
	
	public function __construct($request){
		$this->request = $request;
	}
	public function letsGo(){
		require_once('../routes.php');
		Routes::init("","")->initialize();
	}
	private function getController(){
		
	}
}
?>