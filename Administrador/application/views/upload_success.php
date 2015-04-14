<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximun-scale=1" />
	</head>

	<body>
		<h3>Your file was successfully uploaded!</h3>
		<!-- Uploaded file specification will show up here -->
		<ul>
		<?php foreach ($upload_data as $item => $value):?>
		<li><?php echo $item;?>: <?php echo $value;?></li>
		<?php endforeach; ?>
		</ul>
		<p><?php echo anchor('admin_school/edit', 'Upload Another File!'); ?></p>
	</body>
</html>