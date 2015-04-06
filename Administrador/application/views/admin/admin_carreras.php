<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/admin.css">
	</head>

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
			<form class="form-group col-sm-4 form-div" method="post" action="<?php echo base_url();?>/index.php/admin_school/add_carrera">
				<div class="form-group col-sm-6">
					<input type="text" class="form-control hidden" value="<?php echo $query->id;?>" name="idEscuela" />
			        <label>Carrera</label>
			        <input type="text" class="form-control" name="carrera" placeholder="Carrera">
			    </div>
			    <div class="form-group col-sm-6">
			        <label for="inputEmail">Tipo</label>
			        <select class="form-control" name="tipo">
			        	<?php
			        		$query = $this->db->get('tipo');
			        		$query = $query->result();
			        	?>
			        	<?php foreach ($query as $fila): ?>
			        		<option><?=$fila->nombre;?></option>
			        	<?php  endforeach;?>
			        </select>
			    </div>
			    <center>
			    	<button type="submit" class="btn btn-primary">Aceptar</button>
			    	<button type="reset" class="btn btn-default">Cancelar</button>
			    </center>
			</form>
			<div class="col-sm-8" style="margin-left:12em; margin-top:3em;">
				<table class="table table-striped table-hover table-condensed table-bordered">
					<thead>
						<tr>
							<td>Nombre</td>
							<td>Carrera</td>
							<td>Tipo</td>
							<td>Acciones</td>
						</tr>
					</thead>
					<tbody>
							<?php 
								$this->db->where('idEscuela',$admin_escuela);
								$registros = $this->db->get('relacion_escuela');
								$registro = $registros->result();
							?>
							<?php 
							foreach ($registro as $fill) {
								$idescuela2 = $fill->idCarrera;
								$var = $this->db->get_where('carrera', array('id'=>$idescuela2));
								$var = $var->row();
								$nombreCarrera = $this->db->get_where('tipo', array("tipo"=>$var->tipoCarrera));
								$nombreCarrera = $nombreCarrera->row();
								echo '<tr>';
								echo '<td>'.$var->carrera.'</td>';
								echo '<td>'.$nombreCarrera->nombre.'</td>';
								echo '<td>'.$var->tipoCarrera.'</td>';
								echo '<td><a href="'.base_url().'index.php/admin_school/removeCarrer/'.$fill->id.'">Eliminar</a></td>';
								echo '</tr>';
							}
							?>
					</tbody>
				</table>
			</div>
		</div>

	</body>
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
    <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
</html>