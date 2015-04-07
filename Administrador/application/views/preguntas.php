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
	          <a class="navbar-brand " id="img-brand" href="#"><img src="<?=base_url();?>/img/logo.png" height="40" width="40"></a>
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

		
		<nav class="side-bar" style="position:fixed;">
			<ul class="menu">
				<h2 class="side-bar-title"> Usuarios</h2>
				<li><a href="<?=base_url();?>index.php/cruds"><span class="glyphicon glyphicon-plus"> Agregar</span></a></li>
				<li><a href="<?=base_url();?>index.php/cruds/delete"><span class="glyphicon glyphicon-remove"> Eliminar</span></a></li>
				<li><a href="<?= base_url();?>index.php/cruds/modify"><span class="glyphicon glyphicon-pencil"> Editar</span></a></li>
				<li><a href="<?=base_url();?>index.php/encuestas"><span class="glyphicon glyphicon-pencil"> Preguntas</span></a></li>
			</ul>
		</nav>

		<div class="col-sm-2 col-md-10" id="body">
			<h1 class="page-header">
          	Preguntas
            <p class="lead">Visualizaciones</p>
          </h1>
			<form id="body" method="post" action="<?=base_url();?>/index.php/encuestas/add">
				<div class="col-sm-7">
	            	<input type="text" class="form-control" name="pregunta" placeholder="Pregunta">
	            </div>
	            <!--Llaves foraneas para tipo de carrera-->
	            <div class="col-sm-3">
	            	<select class="form-control" name="tipo">
		            <?php
		            	$query = $this->db->get('tipo');
		            	$query = $query->result();
		            	foreach ($query as $fila):
		            ?>
		            				<option><?=$fila->nombre;?></option>
		            <?php endforeach ?>
    	            </select>
    	        </div>
    	        <div class="col-sm-2">
    	        	<center>
    	        		<input type="submit" class="btn btn-success btn-sm" value="Aceptar" />
    	        	    <input type="reset"  class="btn btn-info btn-sm" value="Cancelar"/>
    	        	</center>
    	        </div>
	        </form>

			<table class="table table-striped table-bordered">
				<?php 
				$query = $this->db->get('encuesta');
				$query = $query->result();
				?>

				<tr>
					<td>Pregunta</td>
					<td>Seccion</td>
					<td>Acciones</td>
				</tr>
				<?php foreach ($query as $fill): ?>
					<tr>
						<td><?=$fill->pregunta;?></td>
						<td><?=$fill->tipoCarrera;?></td>
						<?php $seccionSelected = $fill->tipoCarrera; ?>
						<td>
							<button type="button" class="btn btn-danger btn-sm" style="width:100px;" data-toggle="modal" data-target="#<?=$fill->id?>">
		                      Eliminar
		                    </button>
		                    <button type="button" class="btn btn-primary btn-sm" style="width:100px;" data-toggle="modal" data-target="#1<?=$fill->id;?>">
							  Editar
							</button>
						</td>
						<!-- Modal -->
							<div class="modal fade" id="1<?=$fill->id;?>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title" id="myModalLabel">Editar pregunta</h4>
							      </div>
							      
								      <div class="modal-body">
								        <form method="post" action="<?=base_url();?>index.php/encuestas/modify">
								        	<label>Pregunta</label>
								        	<input type="text" class="form-control hidden" value="<?=$fill->id?>" name="id" />
		            						<input type="text" class="form-control" name="pregunta" placeholder="pregunta" value="<?=$fill->pregunta?>" required><br>
		            						<!--tipo de carrera-->
		            						<select class="form-control" name="tipo">
									            <?php
									            	$query = $this->db->get('tipo');
									            	$query = $query->result();
									            	foreach ($query as $fila):
									            ?>
									        <?php if($fila->tipo != $seccionSelected):?>
									            				<option><?=$fila->nombre;?></option>
		            						<?php else:?>
		            											<option selected="selected"><?=$fila->nombre;?></option>
					            			<?php endif;?>
									            <?php endforeach ?>
							    	            </select><br>
		            						<!--Fin de tipo de carrera-->
		            						<input type="submit" value="Aceptar" class="btn btn-info">
			            				</form>
								      </div>
								      <div class="modal-footer">
								        
								      </div>
							      
							    </div>
							  </div>
							</div>
							<!-- EliminarModal -->
								<div class="modal fade" id="<?=$fill->id;?>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								        <h4 class="modal-title" id="myModalLabel">Confirmacion</h4>
								      </div>
								      
									      <div class="modal-body">
									        <h1>¿Desea borrar el registro?</h1>
									      </div>
									      <div class="modal-footer">
									        <a href="<?php echo base_url();?>/index.php/encuestas/delete/<?=$fill->id;?>" class="btn btn-info">Eliminar</a>
									      </div>
								      
								    </div>
								  </div>
								</div>

					</tr>
				<?php endforeach;?>
			</table>
		</div>

	</body>
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
        <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
</html>