<?php
if(!defined('BASEPATH')) exit ('no direct access script allowed');

class Escuelas extends CI_Controller {
	public function __construct() {
		parent::__construct();
		$this->load->library(array('session'));
		$this->load->library('form_validation');
		$this->load->helper(array('url'));
		$this->load->database('default');
	}

	public function index(){
		$this->load->view('admin/admin_ubicacion');
	}
}