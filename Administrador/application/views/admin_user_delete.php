<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/admin.css">

	</head>

	<body>

	

		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand " id="img-brand" href="#"><img src="../../img/logo.png" height="40" width="40"></a>
	        </div>
	        <div class="navbar-collapse collapse">
	        	<ul class="nav navbar-nav navbar-left">
	        		<li id="title"><a href="#">Nu' Uk</a></li>
	        	</ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#"><span class="glyphicon glyphicon-user"> <?=$this->session->userdata('nombre')?></span></a></li>
	            <li><a href="<?=base_url();?>index.php/login/logout_ci"><span class="glyphicon glyphicon-log-out"> Salir</span></a></li>
	          </ul>
	        </div>
	      </div>
		</nav>

		
		<nav class="side-bar">
			<ul class="menu">
				<h2 class="side-bar-title"> Escuelas</h2>
				<li><a href="<?=base_url();?>index.php/cruds"><span class="glyphicon glyphicon-plus"> Agregar</span></a></li>
				<li><a href="#"><span class="glyphicon glyphicon-remove"> Eliminar</span></a></li>
				<li><a href="<?= base_url();?>index.php/cruds/modify"><span class="glyphicon glyphicon-pencil"> Editar</span></a></li>
				
			</ul>
		</nav>
		<div class="panel panel-default" id="body">
			  <!-- Default panel contents -->
			  <div class="panel-heading">Usuarios</div>
			  <div class="panel-body">
			    
			  </div>
			  <table class="table">
			  	<tr>
			  		<td>Perfil</td>
			  		<td>Nombre</td>
			  		<td>Usuario</td>
			  		<td>Contraseña</td>
			  		<td>Accion</td>
			  	</tr>
		<?php $query = $this->db->get('administrador');
				$query = $query->result();
		 ?>
		<?php foreach ($query as $fila): ?>
			  <tr>
			  	<td><?=$fila->perfil;?></td>
			  	<td><?=$fila->nombre;?></td>
			  	<td><?=$fila->usuario;?></td>
			  	<td><?=$fila->contrasena;?></td>
			  	<td>
			  		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#<?=$fila->id?>">
                      Eliminar
                    </button>
			  	</td>
			  	<!--Modal-->
			  	<div class="modal fade" id="<?=$fila->id?>" tabindex="-1" role="dialog"aria-labelledby="myModalLabel" aria-hidden="true">
			  		<div class="modal-dialog">
			  			<div class="modal-content">
			  				<div class="modal-header">
			  					<button type="button" class="close" data-dismiss="modal" aria-label="close"><span aria-hidden="true">&times;</span></button>
			  					<h4 class="modal-title" id="myModalLabel">Editar</h4>
			  				</div>
			  				<div class="modal-body">
			  					¿Esta seguro de eliminar el siguiente dato?
			  					<form method="post" action="<?=base_url();?>index.php/cruds/sure_delete">
            						<div class="form-group col-sm-4 form-div">
            						<input type="text" class="form-control hidden" value="<?=$fila->id?>" name="id" />
            						<label>Nombre</label>
            						<label><?=$fila->nombre;?></label><br>
            						<label>Usuario</label><br>
            						<label><?=$fila->usuario;?></label><br>
            						<center><input type="submit" value="Aceptar" class="btn btn-info"></center>
            						</div>
            					</form>
			  				</div>
			  				<div class="modal-footer">
			  					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			  				</div>
			  			</div>
			  		</div>
			  	</div>
			  </tr>
			  
		<?php endforeach; ?>
		</table>
			</div>

	</body>
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
        <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
</html>