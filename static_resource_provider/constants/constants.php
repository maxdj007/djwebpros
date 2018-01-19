<?php
//All error messages
define("ERROR_NO_PARAMETERS", "There were no parameters passed in the request");
define("ERROR_UNAUTHORIZED_ACCESS", "You do not have the permission to access this service");
define("ERROR_TOKEN_EXPIRED", "The Token attached to the request has expired");
define("ERROR_INVALID_TOKEN", "The Token attached to this request is invalid");
define("ERROR_UNAUTHORIZED_RESOURCE", "You do not have the permission to access the specified resource");
define("ERROR_UNAUTHORIZED_SCOPE", "You are trying to access a private resource publically");
define("ERROR_RESOURCE_NOT_FOUND", "The reource you are looking for could not be found");

//Header Parameters
define("HEADER_PARAMETER_APPLICATION", "application");
define("HEADER_PARAMETER_APPLICATION_ID", "application_id");
define("HEADER_PARAMETER_VERIFYING_HASH", "verifying_hash");
define("HEADER_PARAMETER_TOKEN", "token");

//Request Parameters
define("POST_PARAMETERS_RAW_DATA", "RAW_DATA");
define("POST_PARAMETERS_USER_HASH", "user_hash");
define("POST_PARAMETERS_IMAGE_ID", "id");
define("POST_PARAMETERS_IMAGE_NAME", "name");
define("POST_PARAMETERS_IMAGE_OWNER", "owner");
define("POST_PARAMETERS_IMAGE_SCOPE", "scope");
define("POST_PARAMETERS_VIDEO_ID", "id");
define("POST_PARAMETERS_VIDEO_NAME", "name");
define("POST_PARAMETERS_VIDEO_OWNER", "owner");
define("POST_PARAMETERS_VIDEO_SCOPE", "scope");

//Internal Constants
define("SCOPE_TYPE_PRIVATE", "private");
define("SCOPE_TYPE_PUBLIC", "public");
define("SCOPE_TYPE_PROTECTED", "protected");

//JWT related variables
define("JWT_SECRET_KEY", "ThisIsJuStThETeStStRiNgSeCrEtKeY");
define("JWT_AUTHOR", "secretAuthor");
define("JWT_TOKEN_EXPIRATION_TIME", "28800000");
define("JWT_AUDIENCE_GUEST", "GUEST");
define("JWT_EXCEPTION_TOKEN_EXPIRED", "The token attached with the request has expired.");
define("JWT_EXCEPTION_TOKEN_INVALID", "The token attached with the request is invalid.");


?>