<?php
class RegisterControls {
	public $_registeredControllers = array (
			"Retrieve" => array (
					"image" 
			), 
			"ErrorHandler" => array(
					"error"
			),
			"CheckToken" => array(
					"checkToken"
			)
	);
	
	public function getRegisteredControllers() {
		return $this->_registeredControllers;
	}
}

?>