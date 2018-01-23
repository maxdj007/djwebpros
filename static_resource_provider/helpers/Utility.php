<?php

class Utility{
	
	public function __construct(){
		
	}
	
	public function checkImageOwner($image, $owner){
		if($image->getOwner() != $owner){
			echo json_encode(array("error"=>true, "message"=>ERROR_UNAUTHORIZED_RESOURCE));
			exit();
		} else {
			return true;
		}
	}
	
	public function checkJWTException($exception){
		if(strpos($exception, "ExpiredException") !== false){
			echo json_encode(array("error"=>true, "message"=>JWT_EXCEPTION_TOKEN_EXPIRED));
			exit();
		} else {
			echo json_encode(array("error"=>true, "message"=>JWT_EXCEPTION_TOKEN_INVALID));
			exit();
		}
	}
	
	public function calculateURL($filename){
		// TODO: the logic to create the URL for the resource
	}
	
	public function sterialiseFilename($filename){
		
	}
	
}

?>