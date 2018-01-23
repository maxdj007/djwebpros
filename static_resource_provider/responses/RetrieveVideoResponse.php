<?php

class RetrieveVideoResponse extends Response implements JsonSerializable {
	private $tags = "";
	private $url = "";
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'tags' => $this->tags,
				'url' => $this->url
		];
	}
	
	
}

?>