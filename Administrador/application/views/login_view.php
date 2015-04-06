<!DOCTYPE html>
    <html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/bootstrap.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/bootstrap.min.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/text.css" media="screen" />
    </head>
    <body>
    <?php
    $username = array('name' => 'username', 'placeholder' => 'nombre de usuario');
    $password = array('name' => 'password',    'placeholder' => 'introduce tu password');
    $submit = array('name' => 'submit', 'value' => 'Iniciar sesión', 'title' => 'Iniciar sesión');
    ?>
    <div class="container-login center-block">
        <h1>Inicio de sesión</h1>
        <div id="top-bar" class="nav nav-tabs">
            <div class="tab-content tabs-login col-lg-12 col-md-12 col-sm-12 cols-xs-12">
                <div>
                    <?=form_open(base_url().'index.php/login/new_user')?>
                    <label for="username">Nombre de usuario:</label>
                    <?=form_input($username)?><p><?=form_error('username')?></p>
                    <label for="password">Introduce tu password:</label>
                    <?=form_password($password)?><p><?=form_error('password')?></p>
                    <?=form_hidden('token',$token)?>
                    <?=form_submit($submit)?>
                    <?=form_close()?>
                    <?php 
                    if($this->session->flashdata('usuario_incorrecto'))
                    {
                    ?>
                    <p><?=$this->session->flashdata('usuario_incorrecto')?></p>
                    <?php
                    }
                    ?>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>