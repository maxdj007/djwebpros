<?php

interface TokenValidator{
	
	public function validateToken($headers);
	public function validateRequestParams($request);
	
}

?>