<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"/>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css"/>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- Include the above in your HEAD tag -->

<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
	
	<link rel="stylesheet" href="/css/demo.css"
		type="text/css" />
		
	<%-- <%@ include file="/css/bootstrap.min.css" %>
	<%@ include file="/js/bootstrap.min.js" %>
	<%@ include file="" %> --%>
</head>
<body>

<div class="main">
	<div class="container">
		<center>
			<div class="middle">
				<div id="login">

					<form action="/login" method="post">

						<fieldset class="clearfix">

							<p>
								<span class="fa fa-user"></span><input type="text" name="username"
									Placeholder="Username" required="true" autofocus="true"/>
							</p>
							<!-- JS because of IE support; better: placeholder="Username" -->
							<p>
								<span class="fa fa-lock"></span><input type="password" name="password"
									Placeholder="Password" required="true"/>
							</p>
							<!-- JS because of IE support; better: placeholder="Password" -->

							<div>
								<span
									style="width: 48%; text-align: left; display: inline-block;"><a
									class="small-text" href="#">Forgot password?</a>
								</span>
								
								<span
									style="width: 50%; text-align: right; display: inline-block;"><input
									type="submit" value="Sign In"/>
								</span>
							</div>

						</fieldset>
						<div class="clearfix"></div>
					</form>

					<div class="clearfix"></div>

				</div>
				<!-- end login -->
				<div class="">
					<img src="\images\star.png">
					<div class="clearfix"></div>
				</div>

			</div>
		</center>
	</div>

</div>

</body>
</html>