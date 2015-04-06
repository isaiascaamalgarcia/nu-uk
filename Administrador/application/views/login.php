<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<?=base_url();?>css/login.css">
	</head>

	<body>

		<div class="login-form">
			<div class="head">
				<img src="<?=base_url();?>img/logoNuukFinal.png" />
			</div>

			<form method="post" action="<?=base_url();?>index.php/login/new_user">
				<li>
					<a href="" class="glyphicon glyphicon-user"></a>
					<input type="text" class="text" placeholder="Usuario" name="username" autofocus required>
				</li>
				<li>
					<a href="" class="glyphicon glyphicon-lock"></a>
					<input type="password" placeholder="Contraseña" name="password" required>
				</li>
					<?=form_hidden('token',$token)?>
					
                    <?php 
                    if($this->session->flashdata('usuario_incorrecto'))
                    {
                    ?>
                    <p><?=$this->session->flashdata('usuario_incorrecto')?></p>
                    <?php
                    }
                    ?>


				<div class="p-container">
					<input type="submit" value="Acceder">
					<div class="clear"></div>
				</div>
			</form>

		</div>

	</body>
</html>