<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<div th:replace="fragments/header :: header-css"/>
</head>

<div th:replace="fragments/header :: header"/>
<%@ include file="header.jsp" %>

<body class="bootstrap">
		
 
		
			<!--#INCLUDE VIRTUAL="TopNavInclude.asp" -->
			

			
			<div class="container">
			
				<div class="col-md-12"> 
						
					<div class="panel panel-default panel-fade">
					
						<div class="panel-heading">
					   
							<span class="panel-title">
							
								<div class="pull-left">
								
								<ul class="nav nav-tabs">
									<li class="active"><a href="#letters" data-toggle="tab"><i class="glyphicon glyphicon-search"></i> Search by</a></li>
									<li><a href="#emails" data-toggle="tab"><i class="glyphicon glyphicon-sort-by-order"></i> Priority</a></li>
									<li><a href="#loglist" data-toggle="tab"><i class="glyphicon glyphicon-list"></i> Logs</a></li>
								</ul>
									
								</div>
								
								<div class="btn-group pull-right">
									<div class="btn-group">
										<a href="#" class="btn  dropdown-toggle" data-toggle="dropdown">
											<!-- <span class="glyphicon glyphicon-cog"></span> -->
										</a>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">Action 1</a></li>
											<li><a href="#">Action 2</a></li>
											<li class="divider"></li>
											<li><a href="#">Another Action</a></li>
										</ul>
									</div>
								</div>

								<div class="clearfix"></div>

							</span>
							
						</div>

						<div class="panel-body">
                        
                            		
							<div class="tab-content">
                                 <div class="tab-pane fade in active" id="letters">
									<h3>Category</h3>
								 <FORM ACTION="/execute" METHOD="GET">
									<INPUT TYPE="hidden" NAME="FormName" VALUE="PrintLetters"/>
									<TABLE class="table table-striped">
									<THEAD>
										<TR><TH>Tick</TH><TH style="text-align:left">Subscription</TH><TH style="text-align:left">Venue</TH><TH>Date/Time</TH></TR>
									</THEAD>
									<TBODY>
										<TR><TD><INPUT TYPE="checkbox" NAME="Title" VALUE="selected"/></TD><TD>Title</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="Description" VALUE="selected"/></TD><TD>Description</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="prefix" VALUE="selected"/></TD><TD>prefix</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="selected"/></TD><TD>Season Subscription (Fall)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
									</TBODY>
									</TABLE>
									<INPUT TYPE="submit" CLASS="btn btn-outline btn-default" VALUE="Save"/>
									<% if(request.getParameter("response")!=null){ %>
									<p style="margin: 12px 0px 0px"><b>Save<%= " "%><%= request.getParameter("response") %></b></p>
									<% } %>
								</FORM>
								</div>
							
													
								    <div class="tab-pane fade" id="emails">
								<h3>Priority</h3>								 
								 <FORM ACTION="" METHOD="post">
									<INPUT TYPE="hidden" NAME="FormName" VALUE="PrintLetters"/>
									<TABLE class="table table-striped">
									<THEAD>
										<TR><TH>Print</TH><TH style="text-align:left">Subscription</TH><TH style="text-align:left">Venue</TH><TH>Date/Time</TH></TR>
									</THEAD>
									<TBODY>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Winter)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Spring)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Summer)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Fall)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
									</TBODY>
									</TABLE>
									<INPUT TYPE="submit" CLASS="btn btn-outline btn-default" VALUE="Save"/>
								</FORM>
								</div>
								
							    <div class="tab-pane fade" id="loglist">
								<h3>Logs</h3>									 
								 <FORM ACTION="" METHOD="post">
									<INPUT TYPE="hidden" NAME="FormName" VALUE="PrintLetters"/>
									<TABLE class="table table-striped">
									<THEAD>
										<TR><TH>Print</TH><TH style="text-align:left">Subscription</TH><TH style="text-align:left">Venue</TH><TH>Date/Time</TH></TR>
									</THEAD>
									<TBODY>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Winter)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Spring)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Summer)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
										<TR><TD><INPUT TYPE="checkbox" NAME="EventCode" VALUE="588031"/></TD><TD>Season Subscription (Fall)</TD><TD>Downtown Theater</TD><TD>1/1/2015 12:00 PM</TD></TR>
									</TBODY>
									</TABLE>
									<INPUT TYPE="submit" CLASS="btn btn-outline btn-default" VALUE="Save"/>
								</FORM>
								</div>

							</div>
							
						</div>

					</div>
						
				</div>
						
			</div>
			

			
			<footer>
			
				<!--#INCLUDE VIRTUAL="FooterInclude.asp"--> 
				
				<script src="https://code.jquery.com/jquery-1.9.1.js" type="text/javascript" ></script>
				<script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
				<script src="http://cdn.jsdelivr.net/jquery.easytabs/3.2.0/jquery.easytabs.min.js" type="text/javascript"></script>
				
				<script>
 
$('a.btn.btn-default').hover(function(e) {
$('a.btn.dropdown-toggle').trigger(e.type);
})
				</script>
				
				<script type="text/javascript">
				$(".display-fade").delay(25).animate({"opacity": "1"}, 800);
				$(".table-fade").delay(25).animate({"opacity": "1"}, 800);
				</script>
				
				<script type="text/javascript">
				var def="black";
				function showNotification(color)
				{
					$( "#notification" ).removeClass(def);
					$( "#notification" ).addClass(color);
					def=color;
					$( "#notification" ).fadeIn( "slow" );
					//alert('hi');
					$(".win8-notif-button").click(function()
					{
					//alert('hi');
					$(".notification").fadeOut("slow");
					});
				
				}
				</script>
				
			</footer>

		</body>
<!-- /.container -->

<%@ include file="footer.jsp" %>

</html>