<?php
if(!defined('BASEPATH')) exit ('No direct access script allowed');

class Encuestas extends CI_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->library(array('session'));
		$this->load->library('form_validation');
		$this->load->helper(array('url'));
		$this->load->database('default');
		$this->load->model('preguntas_crud_model');
	}
/*------Carga la vista par las preguntas------------*/
	public function index(){

		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
		{
			redirect(base_url().'index.php/login');
		}

		$this->load->view('preguntas');
	}
/*---------------Creacion de los cruds---------------*/
	public function delete($id) {
		if( $this->preguntas_crud_model->deleteQuestion($id) ) {
			$this->load->view('preguntas');
		}
	}

	public function add() {
		$pregunta    = $this->input->post('pregunta');
		$tipoCarrera = $this->input->post('tipo');
		if( $this->preguntas_crud_model->addQuestion($pregunta, $tipoCarrera) ) {
			$this->load->view('preguntas');
		}
		
	}
	
	public function modify(){
		$id   = $this->input->post('id');
		$preg = $this->input->post('pregunta');
		$secc = $this->input->post('tipo');
		
		if( $this->preguntas_crud_model->modifyQuestion($id, $preg, $secc) ) {
			$this->load->view('preguntas');
		}
	}

}