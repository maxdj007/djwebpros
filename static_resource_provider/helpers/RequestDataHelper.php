<?php

class RequestDataHelper{
	
	public function convertJSONToArray($jsonString){
		return json_decode($jsonString, TRUE);
	}
	
}

?>