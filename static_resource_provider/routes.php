<?php
class Routes {
	
	// Contollers to be added to the app.
	private $controllers = array ();
	
	public function __construct(){
		$registerControls = new RegisterControls();
		$controllers = $registerControls->getRegisteredControllers();
	}
	
	function call($controller, $action) {
		require_once ('controllers/' . $controller . 'Controller.php');
		
		foreach($controllers as $key=>$value){
			if($key === $controller){
				$controller = new $controller();
				break;
			}
		}
		
		$controller->{ $action } ();
	}
	public function initialize() {
		if (array_key_exists ( $controller, $controllers )) {
			if (in_array ( $action, $controllers [$controller] )) {
				call ( $controller, $action );
			} else {
				call ( 'pages', 'error' );
			}
		} else {
			call ( 'pages', 'error' );
		}
	}
}
?>