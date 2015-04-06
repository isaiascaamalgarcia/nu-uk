<?php
if(!defined('BASEPATH')) exit('direct access script no allowed');

class Test extends CI_Controller{

	public function index(){
		$data = sha1('temp');
		echo $data;
	}
}