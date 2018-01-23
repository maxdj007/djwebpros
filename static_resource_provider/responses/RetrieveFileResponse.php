<?php

class RetrieveFileResponse extends Response implements JsonSerializable {
	private $tags = "";
	private $url = "";
	private $type = "";
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'tags' => $this->tags,
				'url' => $this->url,
				'type' => $this->type
		];
	}
	
	
}

?>
