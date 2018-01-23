<?php

class RetrieveFileListResponse extends Response implements JsonSerializable {
	private $fileList = array();
	
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'status' => $this->status,
				'error' => $this->error,
				'message' => $this->message,
				'fileList' => $this->fileList
		];
	}
	
	
}

?>