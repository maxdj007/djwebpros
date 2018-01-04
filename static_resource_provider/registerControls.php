<?php
class RegisterControls {
	private static $_registeredControllers = array (
			"Retrieve" => array (
					"image" 
			), 
			"ErrorHandler" => array(
					"error"
			)
	);
	public function getRegisteredControllers() {
		return $_registeredControllers;
	}
}

?>