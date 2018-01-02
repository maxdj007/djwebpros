<?php

class RegisterControls{
	private static $_registeredControllers = array(
			'component'    	=> array(
					'Controller' => 'retrieve', 		
					'Methods' => array('image')
			)
	);
	
	public function getRegisteredControllers(){
		return $_registeredControllers;
	}
}

?>