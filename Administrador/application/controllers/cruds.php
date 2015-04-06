<?php
if(!defined('BASEPATH')) exit ('No direct script access allowed');

class Cruds extends CI_Controller {
	public function __construct(){
		parent:: __construct();
		$this->load->database('default');
		
		$this->load->library(array('session','form_validation'));
		$this->load->helper(array('url','form'));
		$this->load->model('crud_model');
	}

	public function index(){
		$this->form_validation->set_rules('name_user','Nombre','required');
		$this->form_validation->set_rules('username','Usuario','required|max_length[50]|trim|callback_user_exist');
		$this->form_validation->set_rules('password','Contraseña','required|min_length[5]|matches[confirm_pass]');
		$this->form_validation->set_rules('confirm_pass', 'Confirmacion', 'required|min_length[5]');

		$this->form_validation->set_message('required', '<div class="container alert alert-warning alert-dimissable col-sm-12"><button type="button" class="close" data-dismiss="alert">&times; </button>El campo %s es requerido</div>');
		$this->form_validation->set_message('min_length', '<div class="container alert alert-warning alert-dimissable col-sm-12"><button type="button" class="close" data-dismiss="alert">&times; </button>El campo %s requiere al menos 5 caracteres</div>');
		$this->form_validation->set_message('user_exist', '<div class="container alert alert-warning alert-dimissable col-sm-12"><button type="button" class="close" data-dismiss="alert">&times; </button>Error: Ya existe un usuario con el mismo nombre</div>');
		
		
		if($this->form_validation->run()==FALSE){
			
			if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
			{
				redirect(base_url().'index.php/login');
			}
			$data['section'] = "Usuario";
			$data['action'] = "Agregar";
			$data['isInsert'] = "";
			$data['titulo'] = 'Bienvenido Administrador';
			$this->load->view('admin_user_add',$data);
			
		}else{
		 	if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
			{
				redirect(base_url().'index.php/login');
			}
			$value = $this->input->post('escuela');
			$this->db->where('nombre',$value);
			$escuela = $this->db->get('escuela');
			$escuela = $escuela->row();
			
			$data['escuela'] = $escuela->id;
			$data['perfil'] = 'administrador';
			$data['nombre'] = $this->input->post('name_user');
			$data['usuario'] = $this->input->post('username');
			$data['contrasena'] = sha1($this->input->post('password'));
			$this->db->insert('administrador', $data);
			$data['section'] = "Usuario";
			$data['isInsert'] = '<div class="container alert alert-success alert-dimissable col-sm-12"><button type="button" class="close" data-dismiss="alert">&times; </button>Error: Usuario agregado</div>';
			$data['action'] = "Agregar";
						$data['titulo'] = 'Bienvenido Administrador';
			$this->load->view('admin_user_add',$data);
			 
		 
		}
		 
	}

	public function user_exist(){
		$nombre = $this->input->post("username");
		$query = $this->db->get_where('administrador', array(
			'usuario' => $nombre
			));
		$count = $query->num_rows();
		if($count == 0){
			return TRUE;
		}
		else{
			return FALSE;
		}

	}

	public function modify(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
			{
				redirect(base_url().'index.php/login');
			}
			$data['section'] = "Usuario";
			$data['action'] = "Modificar";
			$data['titulo'] = 'Bienvenido Administrador';
			$this->load->view('admin_edit',$data);

}

	public function save_changes(){
		
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
			{
				redirect(base_url().'index.php/login');
			}
			
			
		$id = $this->input->post('id');		
		$value = $this->input->post('escuela');
		$this->db->where('nombre',$value);
		$escuela = $this->db->get('escuela');
		$escuela = $escuela->row();
		
		$data['escuela'] = $escuela->id;
		$data['nombre'] = $this->input->post('name_user');
		$data['usuario'] = $this->input->post('username');
		$this->db->where('id',$id);
		$this->db->update('administrador', $data);
		$data['section'] = "Usuario";
		$data['action'] = "Modificar";
		

	$data['titulo'] = 'Bienvenido Administrador';
		$this->load->view('admin_edit',$data);
	}
	public function change_pass(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU'){
			redirect(base_url().'index.php/login');
		}
		$id = $this->input->post('id');
		$data['contrasena'] = sha1($this->input->post('password'));
		$this->db->where('id',$id);
		$this->db->update('administrador', $data);
		$this->load->view('admin_edit',$data);
	}

	public function delete(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
		{
			redirect(base_url().'index.php/login');
		}
		$this->load->view('admin_user_delete');
	}
	public function sure_delete(){
		if($this->session->userdata('perfil') == FALSE || $this->session->userdata('perfil') != 'SuperU')
		{
			redirect(base_url().'index.php/login');
		}
		$id = $this->input->post('id');
		$this->db->where('id', $id);
		$this->db->delete('administrador');
		$this->load->view('admin_user_delete');
	}

}
