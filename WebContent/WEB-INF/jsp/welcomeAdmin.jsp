
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>User Page</title>
	<link href="/TasckhProgect/resources/css/demo.css" rel="stylesheet" type="text/css">
	 <link rel="stylesheet" href="/TasckhProgect/resources/css/jqbar.css" />
	<link rel="stylesheet" type="text/css" href="/TasckhProgect/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/TasckhProgect/resources/css/bootstrap-responsive.css">

	<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,600' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" type="text/css" href="/TasckhProgect/resources/css/style.css">
	
	<link rel="stylesheet" href="/TasckhProgect/resources/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/TasckhProgect/resources/css/simpletextrotator.css" />
    
    <!--[if lt IE 9]> 
			<style>
				.rw-wrapper{ display: none; } 
				.rw-sentence-IE{ display: block;  }
			</style>
	<![endif]-->
</head>
<body >
<div class="logo"><input type="image" SRC="/TasckhProgect/resources/img/Logo1.png" title="Back Home" style="height:20%;
	width:20%; margin-bottom:30px; margin-top:10px; margin-left:20px"/></div>
	<div class="container">
	<div class="container topbottom">
		<div class="row-fluid">

			<div class="span5">
				<img src="/TasckhProgect/user/loadAvatar/${id}" alt="Profile Avatar" class="avatar">

				<div class="navigation">
					<div>
						<ul>
							<li>
								<img src="/TasckhProgect/resources/img/upload.png">
								<a href="/TasckhProgect/user/loadDoc">Gestione Documenti Globale</a>
							</li>
							
							<li>
								<img src="/TasckhProgect/resources/img/lista_user.png">
								<a href="/TasckhProgect/admin/load">Lista Utenti</a>
							</li>
							<li>
								<img src="/TasckhProgect/resources/img/LogOut-icon.png">
								<a href="/TasckhProgect/logout">Logout</a>
							</li>
						</ul>
					</div>
				</div> 			
			</div>

			<div class="span7 homeabout">
				<div class="person">
					<span class="name"><c:out value="${firstname} ${lastname}" /></span>
					<div class="font16"><c:out value="${salutation}" /> presso <span class="greentext"><a href="#">Begear</a></span></div>
				</div>
				<div class="desciption home">
					<p>Known as Johny, I am a web designer and <span class="rotate greentext">WordPress Theme Designer, WordPress Theme Developer</span> living in Pakistan with my wife and two kids. I‘m an active author at Themeforest where I enjoy spending my day in Photoshop converting custom designs into fancy pants <span class="greentext">Custom WordPress Themes</span>.</p>
				</div>	
				<div class="row">
					<div class="span6">
						 <div class="bars">
							<div id="bar-1">
							</div>
							<div id="bar-2">
							</div>
							<div id="bar-3">
							</div>
							<div id="bar-4">
							</div>
                           
						</div>
					</div>
					<div class="span6">
                    	<div class="user-tip"><img src="/TasckhProgect/resources/img/img-tip.png" alt="" /></div>
						<div id="bars-content">
							<div  class="content" id="content-1">HTML5 is a markup language used for structuring and presenting content for the World Wide Web and a core technology of the Internet.</div>
							<div  class="content" id="content-2">CSS is a style sheet language used for describing the presentation semantics  of a document written in a markup language.</div>
							<div  class="content" id="content-3">As part of web browsers, implementations allow client-side scripts to interact with the user and control the browser.</div>
							<div  class="content" id="content-4">WordPress is an open source blogging tool and a CMS based on PHP and MySQL, which runs on a web hosting service.</div>
							<div  class="content" id="content-5">A quick brown fox jumps over the lazy dog. A quick brown fox jumps over the lazy dog. A quick brown fox jumps over the lazy dog.</div>
							<div  class="content" id="content-6">A quick brown fox jumps over the lazy dog. A quick brown fox jumps over the lazy dog. A quick brown fox jumps over the lazy dog.</div>
						</div>
					</div>
				</div>			
				
			</div>

		</div>
	</div>
	</div>
	<script src="/TasckhProgect/resources/js/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script src="/TasckhProgect/resources/js/jqbar.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	 $(document).ready(function () {			
		
            $('#bar-1').jqbar({ label: 'HTML5', value: 80, barColor: '#21ba82' });

            $('#bar-2').jqbar({ label: 'CSS', value: 99, barColor: '#21ba82' });

            $('#bar-3').jqbar({ label: 'JavaScript', value: 85, barColor: '#21ba82' });

            $('#bar-4').jqbar({ label: 'WordPress', value: 75, barColor: '#21ba82' });

          
			
			$('#bars-content .content').css({'opacity':'0',display:'none'});
			$('#bars-content .content:eq(0)').css('display','block').animate({opacity:1},1000);
			$('.jqbar:first').addClass('active');
			$('.jqbar').hover(function(){ $(this).addClass('hover');},function(){ $(this).removeClass('hover');});
			$('.jqbar').click(function(){
				$('.jqbar').removeClass('active');
				var id = $(this).addClass('active').attr('id').replace('bar','content');				
				$('#bars-content .content').css({'opacity':'0',display:'none'});
				$('#' + id).css('display','block').animate({opacity:1},1000);
				
			});		
			
			 $(".rotate").textrotator({
				animation: "spin",
				separator: ",",
				speed: 2000
			  });
			
			
        });
		
    </script>
    <script type="text/javascript" src="js/jquery.simple-text-rotator.min.js"></script>
				
</body>


</html>