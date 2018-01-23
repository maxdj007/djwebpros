<?php

class FileModel implements JsonSerializable{
	private $id;
	private $name;
	private $owner;
	private $scope;
	public function __construct() {
		
	}
	
	public function jsonSerialize() {
		return [
				'id' => $this->id,
				'name' => $this->name,
				'owner' => $this->owner,
				'scope' => $this->scope
		];
	}
}

?>