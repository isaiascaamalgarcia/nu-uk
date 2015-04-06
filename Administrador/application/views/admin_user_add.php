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
	          <a class="navbar-brand " id="img-brand" href="#"><img src="../img/logo.png" height="40" width="40"></a>
	        </div>
	        <div class="navbar-collapse collapse">
	        	<ul class="nav navbar-nav navbar-left">
	        		<li id="title"><a href="#">Nu' Uk</a></li>
	        	</ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#"><span class="glyphicon glyphicon-user"> <?=$this->session->userdata('nombre')?></span></a></li>
	            <li><a href="<?=base_url();?>index.php/login/logout_ci"><span class="glyphicon glyphicon-log-out"> Salir</span></a></li>
	          </ul>
	          <!-- <form class="navbar-form navbar-right">
	            <input type="text" class="form-control" placeholder="Search...">
	          </form> -->
	        </div>
	      </div>
		</nav>

		
		<nav class="side-bar">
			<ul class="menu">
				<h2 class="side-bar-title"> Escuelas</h2>
				<li><a href="<?=base_url();?>index.php/cruds"><span class="glyphicon glyphicon-plus"> Agregar</span></a></li>
				<li><a href="<?=base_url();?>index.php/cruds/delete"><span class="glyphicon glyphicon-remove"> Eliminar</span></a></li>
				<li><a href="<?= base_url();?>index.php/cruds/modify"><span class="glyphicon glyphicon-pencil"> Editar</span></a></li>
				
			</ul>
		</nav>

		<div class="col-sm-2 col-md-10" id="body">
          <h1 class="page-header">
          	Usuario
            <p class="lead">Agregar</p>
          </h1>

          <?echo $isInsert;?>
          <?php echo validation_errors(); ?>
            <form method="post" action="<?=base_url();?>index.php/cruds">
              <div class="form-group col-sm-4 form-div">
                <label>Nombre</label>
                <input type="text" class="form-control" placeholder="Nombre" name="name_user" autofocus required><br>
                <label>Usuario</label>
                <input type="text" class="form-control" placeholder="Usuario" name="username" required><br>
                <label>Contraseña</label>
                <input type="password" class="form-control" name="password" placeholder="Contraseña" required><br>
                <label>Confirmar contraseña</label>
                <input type="password" class="form-control" name="confirm_pass" placeholder="Confirmar" required><br>
                <label>Escuela</label>
                <select class="form-control" name="escuela">
                	<?php
                	$query = $this->db->get('escuela');
                	$query = $query->result();
                	?>
                	<?php foreach ($query as $fila) :?>
                		<option><?=$fila->nombre;?></option>
                	<?php endforeach?>
                </select>
                <center><input type="submit" value="Aceptar" class="btn btn-info"></center>
              </div>
            </form>
          
        </div>

	</body>
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
        <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
</html>