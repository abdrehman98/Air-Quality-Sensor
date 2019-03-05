<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table2/vendor/perfect-scrollbar/perfect-scrollbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/css/util.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/css/main.css">
	
	
	<div class="container">
	<h1 class="page-header text-center">Air Quality Kit</h1>
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<?php
				$user = $this->session->userdata('user');
				extract($user);
			?>
			<h2>______________________ </h2>
			<h2>______________________ </h2>
			<h2>Welcome to Dashboard! </h2>
			<p>Fullname: <?php echo $fname; ?></p>
			<p>Email: <?php echo $email; ?></p>
			<p>Device ID: <?php echo $deviceid; ?></p>
			<p>Acoount Created on: <?php echo $timestamp; ?></p>
			<?php
$query = $this->db->query("SELECT * FROM `partnerpreviousaqi` where deviceid = $deviceid");
$query2 = $this->db->query("SELECT * FROM `partnerpreviouspm25` where deviceid = $deviceid");
?>

		</div>
	</div>
</div>

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<th>Device ID</th>
                    						<th>AQI</th>
                    						<th>Date & Time</th>
							</tr>
						</thead>
						<tbody>
			<?php foreach ($query->result_array() as $row){?>
								<tr>
		    <td><?php echo $row['deviceid'];?></td>
                    <td><?php echo $row['aqi'];?></td>
                    <td><?php echo $row['timestamp'];}?></td>
							</tr>
								
						</tbody>
					</table>
					</div>
				</div>
				

				<div class="table100 ver3 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<th>Device ID</th>
                    						<th>PM 2.5</th>
                    						<th>Date & Time</th>
							</tr>
						</thead>
						<tbody>
					<?php foreach ($query2->result_array() as $row){?><tr>
		    		<td><?php echo $row['deviceid'];?></td>
                    <td><?php echo $row['pm25'];?></td>
                    <td><?php echo $row['timestamp'];}?></td>
							</tr>
								
						</tbody>
					</table>
					</div>
				</div>

			</div>	
		</div>
	</div>
	
	<script src="<?php echo base_url(); ?>fasset/table/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="<?php echo base_url(); ?>fasset/table/vendor/bootstrap/js/popper.js"></script>
	<script src="<?php echo base_url(); ?>fasset/table/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<?php echo base_url(); ?>fasset/table/vendor/select2/select2.min.js"></script>
	<script src="<?php echo base_url(); ?>fasset/table/js/main.js"></script>

  <script src="<?php echo base_url(); ?>fasset/table/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
  <script>
    $('.js-pscroll').each(function(){
      var ps = new PerfectScrollbar(this);

      $(window).on('resize', function(){
        ps.update();
      })
    });
      
    
  </script>