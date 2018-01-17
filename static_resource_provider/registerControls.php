<?php
class RegisterControls {
	public $_registeredControllers = array (
			"Retrieve" => array (
					"image" 
			), 
			"ErrorHandler" => array(
					"error"
			)
	);
	
	public function getRegisteredControllers() {
		return $this->_registeredControllers;
	}
}

?>