<?php

class RetrieveVideoListResponse extends Response implements JsonSerializable {
	private $videoList = array();
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'videoList' => $this->videoList
		];
	}
	
	
}

?>