<?php

spl_autoload_register(function ($class) {
	if(strpos($class, "Emarref") !== false) {
		$classStructure = explode("\\", $class);
		$count = count($classStructure);
		$package = $classStructure[$count-2];
		$className = $classStructure[$count-1];
		
		include "JWT".DIRECTORY_SEPARATOR."".$package.DIRECTORY_SEPARATOR.$className.".php";
	}
	
});

?>