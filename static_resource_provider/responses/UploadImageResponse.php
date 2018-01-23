<?php

class UploadImageResponse extends Response implements JsonSerializable {
	private $tags = "";
	Private $uploadStatus = "";
	private $url = "";
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'tags' => $this->tags,
				'url' => $this->url,
				'uploadStatus' => $this->uploadStatus
		];
	}
	
	
}

?>
