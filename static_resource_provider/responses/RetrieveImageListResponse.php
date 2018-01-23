<?php

class RetrieveImageListResponse extends Response implements JsonSerializable {
	private $imageList = array();
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'imageList' => $this->imageList
		];
	}
	
	
}

?>