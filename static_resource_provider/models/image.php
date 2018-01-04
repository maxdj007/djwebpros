<?php
class Image {
	private $id;
	private $name;
	private $owner;
	private $scope;
	public function __construct($id, $name, $owner, $scope) {
		$this->id = $id;
		$this->name = $name;
		$this->owner = $owner;
		$this->scope = $scope;
	}
	public function getImageById($imageId) {
		$db = Db::getInstance ();
		$imageId = intval ( $imageId );
		$req = $db->prepare ( 'SELECT * FROM images WHERE id = :id' );
		$req->execute ( array (
				'id' => $imageId 
		) );
		$image = $req->fetch ();
		
		$this->setAllAttributes( $image ['id'], $image ['name'], $image ['owner'], $image ['scope'] );
	}
	public function getImageByName($imageName) {
		$db = Db::getInstance ();
		$req = $db->prepare ( 'SELECT * FROM images WHERE name = :name' );
		$req->execute ( array (
				'name' => $imageName
		) );
		$image = $req->fetch ();
		
		$this->setAllAttributes( $image ['id'], $image ['name'], $image ['owner'], $image ['scope'] );
	}
	public function getImagesByOwner($imageOwner) {
		$list = [ ];
		$db = Db::getInstance ();
		$req = $db->prepare ( 'SELECT * FROM images WHERE owner = :owner' );
		$req->execute ( array (
				'owner' => $imageOwner
		) );
		foreach ( $req->fetchAll () as $post ) {
			$list [] = new Image ( $image ['id'], $image ['name'], $image ['owner'], $image ['scope'] );
		}
		
		return $list;
	}
	public function getImagesByScope($imageScope){
		$list = [ ];
		$db = Db::getInstance ();
		$req = $db->prepare ( 'SELECT * FROM images WHERE scope = :scope' );
		$req->execute ( array (
				'scope' => $imageScope
		) );
		foreach ( $req->fetchAll () as $post ) {
			$list [] = new Image ( $image ['id'], $image ['name'], $image ['owner'], $image ['scope'] );
		}
		
		return $list;
	}
	public function setId($id) {
		$this->id = $id;
	}
	public function getId() {
		return $this->id;
	}
	public function setName($name) {
		$this->name = $name;
	}
	public function getName() {
		return $this->name;
	}
	public function setOwner($owner) {
		$this->owner = $owner;
	}
	public function getOwner() {
		return $this->owner;
	}
	public function setScope($scope) {
		$this->scope = $scope;
	}
	public function getScope() {
		return $this->scope;
	}
	private function setAllAttributes($id, $name, $owner, $scope){
		$this->id = $id;
		$this->name = $name;
		$this->owner = $owner;
		$this->scope = $scope;
	}

}
?>