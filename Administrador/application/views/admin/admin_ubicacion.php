<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/admin.css">

	<body onload="initialize()">
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
<?php 
    		$admin_escuela = $this->session->userdata('id_escuela');
			$this->db->where('id', $admin_escuela);
			$query = $this->db->get('escuela');
			$query = $query->row();
    ?>
		<div id="body">
			<center>
				<h1><?=$query->nombre;?></h1>
				<h4><?=$estado;?></h4>
			</center>
			<hr>		
			
			<form class="form-inline" method="post" action="<?php echo base_url()?>index.php/admin_school/newCoords">
				
					<label>Latitud</label>
					<input type ="text" id="latitud" name="latitud" class="form-control" value="<?php echo $query->latitud;?>">
				
				
					<label>Longitud</label>
					<input type ="text" id="longitud" name="longitud" class="form-control" value="<?php echo $query->longitud;?>">
				
				
			    	<button type="submit" class="btn btn-primary">Aceptar</button>
			    	<button type="reset" class="btn btn-default">Cancelar</button>
			    
			</form>
			<div id="map" style="width: 500px; height: 400px; border-radius: 10px; border: 2px solid black; margin: auto; margin-top:15px;">
		</div>
		
	


		

	</body>
	
	<script type="text/javascript" src="<?=base_url();?>js/jquery.min.js"></script>
    <script type="text/javascript" src="<?=base_url();?>js/bootstrap.js"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false&callback=iniciar">
	</script>
    <script type="text/javascript">
    var lat, lon;
    function iniciar() {
    	geolocalizar();
    }
    function geolocalizar() {
    	navigator.geolocation.getCurrentPosition(mostrar, errores);
    }
    function errores(errorCrash) {
    	alert("No se pudo ubicar tu localizacion");
    	console.log(errorCrash);
    }
    function mostrar(geo){
    	lat = geo.coords.latitude;
    	lon = geo.coords.longitude;
    	imprimirMapa(lat, lon);
    }
    function imprimirMapa(lat, lon){
    	var myPosition = new google.maps.LatLng(getLatitude(), getLongitude());
    	var mapOptions = {

    		center: myPosition,
    		zoom: 13,
    		mapTypeId: google.maps.MapTypeId.ROADMAP};
    	var map = new google.maps.Map(document.getElementById("map"),mapOptions);
  		var	myPositionMarker = new google.maps.Marker({
    		draggable:  true,
    		position: myPosition,
    		title: 'Ubicacion',
    		map: map
    	});
	    	google.maps.event.addListener(myPositionMarker, 'dragend', function (event) {
		    document.getElementById("latitud").value = this.getPosition().lat();
		    document.getElementById("longitud").value = this.getPosition().lng();
		});
    }
    function getLatitude() {
    	var latit = "<?php echo $query->latitud;?>";
    	return (latit);
    	
    }
    function getLongitude() {
    	var longi = "<?php echo $query->longitud;?>";
    	return (longi);
    }
</script> 
    </script>
</html>