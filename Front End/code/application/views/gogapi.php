<?php
$query = $this->db->query("SELECT * FROM `publicside` ORDER BY `deviceid` ASC");
?>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/vendor/perfect-scrollbar/perfect-scrollbar.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/css/util.css">
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>fasset/table/css/main.css">

          <link rel="stylesheet" href="<?php echo base_url(); ?>fasset/css/font-awesome.min.css">
        <!-- Themify Icons CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/themify-icons.css">
        <!-- Elegant Font Icons CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/elegant-font-icons.css">
        <!-- Elegant Line Icons CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/elegant-line-icons.css">
    <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/bootstrap.min.css">
    <!-- Venobox CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/venobox/venobox.css">
    <!-- OWL-Carousel CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/owl.carousel.css">
        <!-- Slick Nav CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/slicknav.min.css">
        <!-- Css Animation CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/css-animation.min.css">
        <!-- Nivo Slider CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/nivo-slider.css">
    <!-- Main CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/main.css">
        <!-- style CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/style.css">
    <!-- Responsive CSS -->
        <link rel="stylesheet" href="<?php echo base_url();?>fasset/css/responsive.css">

        <script src="<?php echo base_url();?>fasset/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
<body>
	
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
      
    <h3>-----------------------------------------------------------------------------------------------------------------------------</h3>
    <h4>					AIR QUALITY INDEX </h4>
    <h3>-----------------------------------------------------------------------------------------------------------------------------</h3>
					<table>
						<thead>
							<tr class="table100-head">
								<th>Device ID</th>
                    						<th>AQI</th>
                    						<th>Location Name</th>
							</tr>
						</thead>
						<tbody>
<?php foreach ($query->result_array() as $row){?>
								<tr>
		    <td><?php echo $row['deviceid'];?></td>
                    <td><?php echo $row['aqi'];?></td>
                    <td><?php echo $row['location_name'];}?></td>
							</tr>
								
						</tbody>
					</table>
				</div>
</div>
    <head>
    <style>
       /* Set the size of the div element that contains the map */
      #map {
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
       }
    </style>
  </head>
  <body>

  	<h3>-----------------------------------------------------------------------------------------------------------------------------</h3>
    <a href="<?php echo site_url('user/map');?>" class="default-btn">Click here to see Location of All active device using Google Map. </a>
    <h3>-----------------------------------------------------------------------------------------------------------------------------</h3>
    <!--The div element for the map -->
    


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