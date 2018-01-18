<?php

class Utility{
	
	public function __construct(){
		
	}
	
	public function checkImageOwner($image, $owner){
		if($image->owner != $owner){
			echo json_encode(array("error"=>true, "message"=>ERROR_UNAUTHORIZED_RESOURCE));
			exit();
		} else {
			return true;
		}
	}
	
}

?>