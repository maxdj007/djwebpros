<?php
require_once('Controller.php');
require_once ('../helpers/RequestDataHelper.php');
class RetrieveController extends Controller {
 
	private $requestDataHelper = "";
	private $constants = "";
	
	public function __construct($controller, $action, $request) {
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
		$this->requestDataHelper = new RequestDataHelper();
	}
	
	public function image() {
		// here we take out the image from the database to return.
		if(sizeof($parameters = $this->requestDataHelper->convertJSONToArray($this->request['RAW_DATA'])) != 0){
			require_once ('../models/image.php');
			$image = new Image();
			$image->getImageById($parameters['id']);
			return $image;
		} else {
			return json_encode("{'ERROR'=>'true','MESSAGE'=>'No id Found'}");
		}
		
	}
}
?>