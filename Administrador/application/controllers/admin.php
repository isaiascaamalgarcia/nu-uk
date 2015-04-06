<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/**
 * 
 */
class Admin extends CI_Controller {
	
	public function __construct() {
		parent::__construct();
		$this->load->library(array('session'));
		$this->load->library('form_validation');
		$this->load->helper(array('url'));
	}
	
	public function index()
	{
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
		{
			redirect(base_url().'index.php/login');
		}
		$data['section'] = "Usuario";
		$data['action'] = "Agregar";
		$data['titulo'] = 'Bienvenido Administrador';
		$this->load->view('admin_welcome',$data);
	}
	public function escuelas(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
		{
			redirect(base_url().'index.php/login');
		}
		$data['section'] = "Usuario";
		$data['action'] = "Agregar";
		$data['titulo'] = 'Bienvenido Administrador';
		$this->load->view('admin_school',$data);
	}
}
