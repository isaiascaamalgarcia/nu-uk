<?php
if(!defined('BASEPATH')) exit('no direct access script allowed');

class Admin_school extends CI_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->library(array('session'));
		$this->load->library('form_validation');
		$this->load->helper(array('url'));
		$this->load->database('default');
	}

	public function index(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'administrador')
		{
			redirect(base_url().'index.php/login');
		}
		
		$this->load->view('admin/welcome_view');
	}
	public function edit(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'administrador')
		{
			redirect(base_url().'index.php/login');
		}
		$data["error"] = "";
		$this->load->view('admin/admin_edit', $data);
	}

	public function carreras(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'administrador')
		{
			redirect(base_url().'index.php/login');
		}
		$this->load->view('admin/admin_carreras');
	}

	public function add_carrera() {
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'administrador')
		{
			redirect(base_url().'index.php/login');
		}
		$data['carrera'] = $this->input->post('carrera');
		
		$idtipo = $this->input->post('tipo');
		$this->db->where('nombre', $idtipo);
		$idtipo = $this->db->get('tipo');
		$idtipo = $idtipo->row();
		$data['tipoCarrera'] = $idtipo->tipo;

		$this->db->insert('carrera', $data);
		$value = $this->input->post('idEscuela');
		$this->db->where('carrera', $data['carrera']);
		$query = $this->db->get('carrera');
		$query = $query->row();
		$dato['idCarrera'] = $query->id;
		$dato['idEscuela'] = $value;
		$this->db->insert('relacion_escuela',$dato);
		$this->load->view('admin/admin_carreras');
	}
	public function add_exist_carrera() {
		$carrera = $this->input->post('carrera');
		$value = $this->input->post('idEscuela');
		$this->db->where('carrera', $carrera);
		$query = $this->db->get('carrera');
		$query = $query->row();
		$dato['idCarrera'] = $query->id;
		$dato['idEscuela'] = $value;
		$this->db->insert('relacion_escuela',$dato);
		$this->load->view('admin/admin_carreras');
	}

	public function saveChangesSchool(){
		$idescuela = $this->input->post('ides');
		$tipo = $this->input->post('tipo');
		if($tipo == 'Universidad') {
			$tipo = 1;
		}
		else {
			$tipo = 0;
		}
		$data['nombre'] = $this->input->post('escuela');
		$data['tipo'] = $tipo;	
		$data['direccion'] = $this->input->post('direccion');
		$data['telefono'] = $this->input->post('telefono');
		$data['pagina'] = $this->input->post('pagina');
		$data['correo'] = $this->input->post('correo');
		$data['facebook'] = $this->input->post('facebook');
		$data['twitter'] = $this->input->post('twitter');
		$data['sector'] = $this->input->post('sector');
		$data['modificacion'] = $this->session->userdata('id_usuario');
		$this->db->where('id',$idescuela);
		$this->db->update('escuela', $data);
		$this->load->view('admin/admin_edit');
	}

	public function removeCarrer($id){
		//obtener los valores antes de eliminar y almancenar en variales
		$this->db->where('id', $id);
		$query =  $this->db->get('relacion_escuela');
		$query = $query->row();
		$idescuela = $query->idEscuela;
		$idCarrera = $query->idCarrera;
		//Eliminar los valores despues de respaldarlos
		$this->db->where('id', $id);
		$this->db->delete('relacion_escuela');
		//Eliminar los datos en la tabla carrera despues de quitar la relacion
		$this->db->where('id', $idCarrera);
		$this->db->delete('carrera');

		$this->load->view('admin/admin_carreras');
	}
	public function view_location(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'administrador')
		{
			redirect(base_url().'index.php/login');
		}
		$this->load->view('admin/admin_ubicacion');
	}
	public function newCoords() {
		$admin_escuela = $this->session->userdata('id_escuela');
		$this->db->where('id', $admin_escuela);
		$query = $this->db->get('escuela');
		$query = $query->row();
		$lat = $this->input->post('latitud');
		$lon = $this->input->post('longitud');
		$data['latitud']  = $lat;
		$data['longitud'] = $lon;
		$this->db->where('id', $query->id);
		$this->db->update('escuela', $data);
		$this->load->view('admin/admin_ubicacion');
	}

	public function file_view() {
		$this->load->view('admin/admin_edit', array('error' => ' '));
	}
	public function do_upload() {
		$config = array(
			'allowed_types'  => "jpg|png|jpeg|",
			'max_height' 	 => "768",
			'max_width' 	 => "1024",
			'max_size' 		 => "2048000",
			'overwrite' 	 => true,
			'upload_path' 	 => "./uploads/",
		);
		$this->load->library('upload', $config);
		if($this->upload->do_upload()) {
			$data = array('upload_data' => $this->upload->data());
			$admin_escuela = $this->session->userdata('id_escuela');
			$raw = $data['upload_data'];
			echo $raw['file_name'];
			$dato['logo'] = $raw['file_name'];
			$this->db->where('id', $admin_escuela);
			$this->db->update('escuela', $dato);
			$data["error"] = "";
			$this->load->view('admin/admin_edit', $data);
			

		}
		else {
			$error = array('error' => $this->upload->display_errors() );
			$this->load->view('admin/admin_edit', $error);
		}
	}
}
