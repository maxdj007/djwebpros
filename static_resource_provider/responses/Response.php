<?php

class Response implements JsonSerializable{
	
	private $status = "";
	private $error = "";
	private $message = "";
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message
		];
	}
	
}

?>