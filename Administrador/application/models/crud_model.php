<?php
if(!defined('BASEPATH')) exit('no direct access script allowed');
class Crud_model extends CI_Model {

	public function __construct(){
		parent:: __construct();
	}
	public function validate_user($name){
		$query = $this->db->get_where('administrador',array('usuario' => $name) );
		if($query->num_rows()>0){
			return TRUE;
		}
		return false;
	}
	public function getUsers(){
		$query = $this->db->get('administrador');
		if($query->num_rows > 0){
			return $query->result();
		}
	}
	public function delete_user($id){
		$this->db->where('id',$id);
		return $this->db->delete('administrador');
	}
}