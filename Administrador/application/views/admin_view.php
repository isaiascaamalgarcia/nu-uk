<!DOCTYPE html>
    <html lang="es">
    <head>
        <title>Nu-uk</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/bootstrap.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/text.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<?=base_url()?>css/bootstraps.min.css" media="screen" />
        <script type="text/javascript" src="<?php echo base_url(); ?>js/jquery.js"></script>
        <script type="text/javascript" src="<?php echo base_url(); ?>js/bootstrap.js"></script>

        <style>
            #body {
                background: #6A0888;

            }
            span {
                font-weight: bold;
                font-size: 13pt;
            }
            .form-div{
              width: 60%;
              margin-left: 20%;
              margin-right: 20%;
            }
</style>

    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#acolapsar">
                        <span class="sr-only">Nu'uk</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand">Nu'Uk</a>
                </div>

                <div class="collapse navbar-collapse" id="acolapsar">
                    <ul class="nav navbar-nav">
                        <li><a href="#"><span class="glyphicon glyphicon-home"></span> Escuelas</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-education"></span> Usuarios</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> <?=$this->session->userdata('nombre')?></a></li>
                        <li><a href="<?=base_url();?>index.php/login/logout_ci"><span class="glyphicon glyphicon-off"></span> Cerrar sesión</a></li>
                        
                    </ul>
                </div>

            </div>
        </nav>

    

       <!---->        
         <div id="sidebar"role="navigation">
           
            <ul class="nav nav-sidebar col-sm-3 col-md-2" style="padding-right:0px;">
              <li class="sidebar-title"><span STYLE="font-size: 18pt; padding-left:15px;"> <?=$section?></span></li>
              <li><a href="<?=base_url();?>index.php/cruds"><span class="glyphicon glyphicon-plus"> Agregar</span></a></li>
              <li><a href="<?=base_url();?>index.php/cruds/modify"><span class="glyphicon glyphicon-pencil"> Modificar</span></a></li>
              <li><a href="#"><span class="glyphicon glyphicon-remove"> Eliminar</span></a></li>
            </ul>          
        </div><!--/span-->
        
        
        <div class="col-sm-2 col-md-10" id="body">
          <h1 class="page-header">
            <?=$section?>
            <p class="lead">(<?=$action?>)</p>
          </h1>
          <?php echo validation_errors(); ?>
          <?php if($section == "Usuario" && $action == "Agregar"): ?>
            <form method="post" action="<?=base_url();?>index.php/cruds">
              <div class="form-group col-sm-4 form-div">
                <label>Nombre</label>
                <input type="text" class="form-control" placeholder="Nombre" name="name_user"><br>
                <label>Usuario</label>
                <input type="text" class="form-control" placeholder="Usuario" name="username"><br>
                <label>Contraseña</label>
                <input type="password" class="form-control" name="password" placeholder="Contraseña"><br>
                <label>Confirmar contraseña</label>
                <input type="password" class="form-control" name="confirm_pass" placeholder="Confirmar"><br>
                <center><input type="submit" value="Aceptar" class="btn btn-info"></center>
              </div>
            </form>
          <?php endif;?>
          <?php if($section=="Usuario" && $action=="Modificar"):?>
            <?php $users  = $this->db->get('administrador'); 
                  $users = $users->result();
                  
                  ?>
                  <?php 
                  echo '<div list-group table>';
                  foreach ($users as $fila){
                    
                    echo '<a href="#'.$fila->id.'" class="list-group-item">'.$fila->nombre."|".$fila->usuario."|".$fila->contrasena.'</a>';
                    //////////////////////
                    echo '<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#'.$fila->id.'">';
                      echo 'Editar';
                    echo '</button>';

                    echo '<!-- Modal -->';
                    echo '<div class="modal fade" id="'.$fila->id.'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
                      echo '<div class="modal-dialog">';
                        echo '<div class="modal-content">';
                          echo '<div class="modal-header">';
                            echo '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
                            echo '<h4 class="modal-title" id="myModalLabel">Editar</h4>';
                          echo '</div>';
                          echo '<div class="modal-body">';
                            echo 'Contenido';
            echo  '<form method="post" action="'.base_url().'index.php/cruds/save_changes">';
            echo  '<div class="form-group col-sm-4 form-div">';
            echo '<input type="text" class="form-control hidden" value="'.$fila->id.'" name="id" />' ;
            echo   '<label>Nombre</label>';
            echo    '<input type="text" class="form-control" placeholder="Nombre" value="'.$fila->nombre.'" name="name_user"><br>';
            echo    '<label>Usuario</label>';
            echo    '<input type="text" class="form-control" placeholder="Usuario" value="'.$fila->usuario.'" name="username"><br>';
            echo    '<label>Contraseña</label>';
            echo    '<input type="password" class="form-control" name="password" placeholder="Contraseña"><br>';
            echo    '<label>Confirmar contraseña</label>';
            echo    '<input type="password" class="form-control" name="confirm_pass" placeholder="Confirmar"><br>';
            echo    '<center><input type="submit" value="Aceptar" class="btn btn-info"></center>';
            echo  '</div>';
            echo '</form>';
                          echo '</div>';
                          echo '<div class="modal-footer">';
                            echo '<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>';
                            
                          echo '</div>';
                        echo '</div>';
                      echo '</div>';
                    echo '</div>';
                    //////////////////////
                    
                  }
                  echo '</div>';
                  ?>
                  
                  
          <?php endif;?>
        </div>

       </div>

       <!---->

    </body>
</html>
    