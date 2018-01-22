<?php

spl_autoload_register(function ($class) {
	if(strpos($class, "Emarref") !== false) {
		$classStructure = explode("\\", $class);
		$count = count($classStructure);
		$package = $classStructure[$count-2];
		$className = $classStructure[$count-1];
		
		include "JWT".DIRECTORY_SEPARATOR."".$package.DIRECTORY_SEPARATOR.$className.".php";
	} else if (strpos($class, "Helper") !== false || $class == "Utility") {
		include "helpers".DIRECTORY_SEPARATOR."".$class.".php";
	} else if (strpos($class, "Controller") !== false) {
		include "controllers".DIRECTORY_SEPARATOR."".$class.".php";
	} else if (strpos($class, "Model") !== false) {
		include "models".DIRECTORY_SEPARATOR."".$class.".php";
	} else if (strpos($class, "Validator") !== false) {
		include "validators".DIRECTORY_SEPARATOR."".$class.".php";
	} 
});

function __get($key){
	return $this->$key;
}

function __set($key, $value){
	$this->$key = $value;
}

?>