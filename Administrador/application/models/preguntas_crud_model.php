<?php
if(!defined('BASEPATH')) exit('No direct access script allowed');

class Preguntas_crud_model extends CI_Model {
	public function __construct() {
        parent::__construct();
    }
/*----------Seccion de cruds-----------------*/
    public function deleteQuestion($id) {
		$this->db->where('id', $id);
		return $this->db->delete('encuesta');
    }
    public function addQuestion($question, $seccion) {
    	$data['pregunta']    = $question;
    	$data['tipoCarrera'] = $this->getIdForSection($seccion);

    	return $this->db->insert('encuesta',$data);
    }
    public function modifyQuestion($id, $pregunta, $seccion) {
    	$data['pregunta']    = $pregunta;
    	$data['tipoCarrera'] = $this->getIdForSection($seccion);

    	$this->db->where('id', $id);
		return $this->db->update('encuesta',$data);
    }
/*-----------Obtnencion de variables---------*/
    public function getIdForSection($seccion) {
        $this->db->where('nombre', $seccion);
        $query = $this->db->get('tipo');
        $query = $query->row();
        return $query->tipo;
    }
}