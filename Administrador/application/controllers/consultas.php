<?php
if(!defined('BASEPATH')) exit ('No direct access script allowed');

class Consultas extends CI_Controller {

	public function __construct(){
		parent::__construct();
		$this->load->database('default');
	}

	public function index(){
		$table = $this->input->post('tableName');
		$query = $this->db->get($table);
		$query = $query->result(); //Obtiene todos los valores no es necesario ponerlo en un foreach

		echo json_encode($query); //Imprime todos los registros del query select from;
	}
}