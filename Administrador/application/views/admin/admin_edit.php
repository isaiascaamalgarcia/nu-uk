<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/admin.css">

	<body>
		<?php
			$admin_escuela = $this->session->userdata('id_escuela');
			$this->db->where('id', $admin_escuela);
			$query = $this->db->get('escuela');
			$query = $query->row();
			if($query->tipo== 1){
				$estado = "Universidad";
			}
			else {
				$estado = "Preparatoria";
			}
		?>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand " id="img-brand" href="#"><img src="<?=base_url();?>/img/logo.png" height="40" width="40"></a>
	        </div>
	        <div class="navbar-collapse collapse">
	        	<ul class="nav navbar-nav navbar-left">
	        		<li id="title"><a href="#">Nu' Uk</a></li>
	        	</ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#"><span class="glyphicon glyphicon-user"> <?=$this->session->userdata('nombre');?></span></a></li>
	            <li><a href="<?=base_url();?>index.php/login/logout_ci"><span class="glyphicon glyphicon-log-out"> Salir</span></a></li>
	          </ul>
	        </div>
	      </div>
		</nav>
		
		<nav class="side-bar" style="position:fixed;">
			<ul class="menu">
				<h2 class="side-bar-title"> Escuelas</h2>
				<li><a href="<?=base_url();?>index.php/admin_school/edit"><span class="glyphicon glyphicon-pencil"> Modificar</span></a></li>
				<li><a href="<?php echo base_url();?>index.php/admin_school/carreras"><span class="glyphicon glyphicon-pencil"> Carreras</span></a></li>
				<li><a href="<?php echo base_url();?>index.php/admin_school/view_location"><span class="glyphicon glyphicon-pencil"> Ubicacion</span></a></li>
				
			</ul>
		</nav>

		<div id="body">
			<center>
				<h1><?=$query->nombre;?></h1>
				<h4><?=$estado;?></h4>
			</center>
			<hr>
			<form class="form-group col-sm-4 form-div" method="post" action="<?php echo base_url()?>/index.php/admin_school/saveChangesSchool">
				<div class="form-group col-sm-6">
					<input class="hidden" value="<?=$query->id;?>" name="ides">
			        <label >Nombre</label>
			        <input type="text" class="form-control" name="escuela" placeholder="Nombre" value="<?=$query->nombre;?>">
			    </div>
			    <div class="form-group col-sm-6">
			        <label for="inputEmail">Tipo</label>
			        <select class="form-control" name="tipo">
			        	<?php if($query->tipo == 0):?>
			        		<option selected="selected">Preparatoria</option>
			        		<option>Universidad</option>
			        	<?php else: ?>
			        		<option>Preparatoria</option>
			        		<option selected="selected">Universidad</option>
			        	<?php endif;?>
			        		
			        </select>
			    </div>
			    <div class="form-group col-sm-8">
			        <label>Dirección</label>
			        <input type="text" class="form-control" name="direccion" placeholder="Direccion" value="<?=$query->direccion;?>">
			    </div>
			    <div class="form-group col-sm-4">
			        <label>Teléfono</label>
			        <input type="text" class="form-control" name="telefono" placeholder="Telefono" value="<?=$query->telefono;?>">
			    </div>
			    <div class="form-group col-sm-3">
			    	<label>Página web</label>
			    	<input type="text" class="form-control" name="pagina" placeholder="Página web" value="<?=$query->pagina;?>">
			    </div>
			    <div class="form-group col-sm-3">
			    	<label>Correo</label>
			    	<input type="text" class="form-control" name="correo" placeholder="Correo" value="<?=$query->correo;?>">
			    </div>
			    <div class="form-group col-sm-3">
			    	<label>Facebook</label>
			    	<input type="text" class="form-control" name="facebook" placeholder="Facebook" value="<?=$query->facebook;?>">
			    </div>
			    <div class="form-group col-sm-3">
			    	<label>Twitter</label>
			    	<input type="text" class="form-control" name="twitter" placeholder="Twitter" value="<?=$query->twitter;?>">
			    </div>
			    <div class="form-group col-sm-12">
			    	<label>Sector</label>
			    	<select class="form-control" name="sector">
			        	<?php if($query->tipo != "PUBLICO"):?>
			        		<option selected="selected">PUBLICO</option>
			        		<option>PRIVADA</option>
			        	<?php else: ?>
			        		<option>PUBLICO</option>
			        		<option selected="selected">PRIVADA</option>
			        	<?php endif;?>
			        		
			        </select>
			    </div>
			    <center>
			    	<button type="submit" class="btn btn-primary">Aceptar</button>
			    	<button type="reset" class="btn btn-default">Cancelar</button>
			    </center>
			</form>

			

		</div>

		

	</body>
	
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
    <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
</html>