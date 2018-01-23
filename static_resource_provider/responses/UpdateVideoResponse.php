<?php

class UpdateVideoResponse extends Response implements JsonSerializable {
	private $updateTime = "";
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'updateTime' => $this->tags
		];
	}
	
	
}

?>
