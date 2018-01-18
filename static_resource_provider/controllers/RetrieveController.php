<?php
require_once('Controller.php');
require_once ('./helpers/RequestDataHelper.php');
require_once ('./helpers/Utility.php');
class RetrieveController extends Controller {
	
	public function __construct($controller, $action, $request) {
		$this->controller = $controller;
		$this->action = $action;
		$this->request = $request;
		$this->requestDataHelper = new RequestDataHelper();
		$this->utility = new Utility();
	}
	
	public function image() {
		// here we take out the image from the database to return.
		if(sizeof($parameters = $this->requestDataHelper->convertJSONToArray($this->request[POST_PARAMETERS_RAW_DATA])) != 0){
			require_once ('./models/image.php');
			$image = new Image();
			$image->getImageById($parameters[POST_PARAMETERS_IMAGE_ID]);
			if($image->getId() == null){
				echo json_encode(array('error'=>true,'message'=>ERROR_RESOURCE_NOT_FOUND));
			} else {
				if($image->getScope() == SCOPE_TYPE_PRIVATE){
					if ($this->utility->checkImageOwner($image, $parameters[POST_PARAMETERS_USER_HASH])){
						echo json_encode($image);
						exit();
					}
				} else if ($image->getScope() == SCOPE_TYPE_PROTACTED){
					//something TODO here
				} else {
					echo json_encode($image);
					exit();
				}
			}
		} else {
			echo json_encode(array('error'=>true,'message'=>ERROR_NO_PARAMETERS));
		}
		
	}
}
?>