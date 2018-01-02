<?php
/**
 * Login
 *
 * PUBLIC:                 PRIVATE
 * -----------             ------------------
 * __construct             
 * login  
 *
 */
class Login extends CModel
{
    public function __construct()
    {
        parent::__construct();
    }

    public function login($username, $password)
    {
        $result = $this->_db->select('
            SELECT id, role
            FROM '.CConfig::get('db.prefix').'admins
            WHERE username = :username AND password = :password',
            array(
                ':username' => $username,
                ':password' => ((CConfig::get('password.encryption')) ? CHash::create(CConfig::get('password.encryptAlgorithm'), $password, CConfig::get('password.hashKey')) : $password)
            )
        );
        
        if(!empty($result)){
            $session = A::app()->getSession();
            $session->set('loggedIn', true);
            $session->set('loggedId', $result[0]['id']);
            $session->set('loggedRole', $result[0]['role']);
            
            return true;
        }else{
            return false;        
        }        
    }       
}
