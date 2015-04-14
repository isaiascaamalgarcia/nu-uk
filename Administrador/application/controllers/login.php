<?php
if ( ! defined('BASEPATH')) exit('No direct script access allowed');
 
class Login extends CI_Controller
{
    public function __construct()
    {
        parent::__construct();
        $this->load->model('login_model');
        $this->load->library(array('session','form_validation'));
        $this->load->helper(array('url','form'));
        $this->load->database('default');
    }
    
    public function index()
    {    
        switch ($this->session->userdata('perfil')) {
            case '':
                $data['token'] = $this->token();
                $data['titulo'] = 'Login';
                $this->load->view('login',$data);
                break;
            case 'SuperU':
                redirect(base_url().'index.php/admin');
                break;
            case 'administrador':
                redirect(base_url().'index.php/admin_school');
                break;    
            case 'suscriptor':
                redirect(base_url().'suscriptor');
                break;
            default:        
            $data['token'] = $this->token();
                $data['titulo'] = 'Login';
                $this->load->view('login',$data);
                break;        
        }
    }
    public function token()
    {
        $token = md5(uniqid(rand(),true));
        $this->session->set_userdata('token',$token);
        return $token;
    }
    public function new_user()
    {
        if($this->input->post('token') && $this->input->post('token') == $this->session->userdata('token'))
        {
            $this->form_validation->set_rules('username', 'nombre de usuario', 'required|trim|min_length[2]|max_length[150]|xss_clean');
            $this->form_validation->set_rules('password', 'password', 'required|trim|min_length[5]|max_length[150]|xss_clean');
 
            //lanzamos mensajes de error si es que los hay
            
            if($this->form_validation->run() == FALSE)
            {
                $this->index();
            }else{
                $username = $this->input->post('username');
                $password = sha1($this->input->post('password'));
                $check_user = $this->login_model->login_user($username,$password);
                if($check_user == TRUE)
                {
                    $data = array(
                    'is_logued_in'     =>       TRUE,
                    'id_usuario'       =>       $check_user->id,
                    'perfil'           =>       $check_user->perfil,
                    'nombre'           =>       $check_user->nombre,
                    'username'         =>       $check_user->usuario,
                    'id_escuela'       =>       $check_user->escuela,
                    );        
                    $this->session->set_userdata($data);
                    $this->index();
                }
            }
        }else{
            redirect(base_url().'index.php/login');
        }
    }
    public function logout_ci()
    {
        $this->session->sess_destroy();
        $this->index();
    }
}