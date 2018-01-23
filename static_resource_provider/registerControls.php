<?php
class RegisterControls {
	public $_registeredControllers = array (
			"Retrieve" => array (
					"image",
					"video",
					"file",
					"imageListByOwner",
					"imageListByTags",
					"imageListByTime",
					"videoListByOwner",
					"videoListByTags",
					"videoListByTime"
			), 
			"ErrorHandler" => array(
					"error"
			),
			"CheckToken" => array(
					"checkToken"
			),
			"Upload" => array(
					"image",
					"video",
					"file"
			),
			"Update" => array(
					"image",
					"video",
					"file"
			),
			"Delete" => array(
					"image",
					"video",
					"file"
			)
	);
	
	public function getRegisteredControllers() {
		return $this->_registeredControllers;
	}
}

?>