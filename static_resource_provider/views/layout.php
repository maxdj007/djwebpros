<?php

class Layout{
	
	private $request = "";
	private $server = "";
	private $rawPostData = "";
	private $mergedRequest = "";
	
	public function __construct(){
		$this->request = $_REQUEST;
		$this->server = $_SERVER;
		$this->rawPostData = $HTTP_RAW_POST_DATA;
	}
	public function letsGo(){
		$this->mergedRequest = $this->mergeRequest();
		require_once('../routes.php');
		Routes::init($this->getController(),$this->getAction(),$this->mergedRequest)->initialize();
	}
	private function getController(){
		$uriSegments = explode("/", parse_url($this->mergedRequest['REQUEST_URI'], PHP_URL_PATH));
		return $uriSegments[1];
	}
	private function getAction(){
		$uriSegments = explode("/", parse_url($this->mergedRequest['REQUEST_URI'], PHP_URL_PATH));
		return $uriSegments[2];
	}
	private function mergeRequest(){
		return array_merge($this->request, $this->server, array("RAW_DATA" =>$this->rawPostData));
	}
}
?>