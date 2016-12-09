<%-- 
    Document   : feed
    Created on : Dec 7, 2016, 9:19:45 AM
    Author     : saudalhilali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
        <title>Ask.me | Feed</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/custom.css" rel="stylesheet">

    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
  </head>
  <body>
    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html">Ask.me</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="feed.jsp">Home</a></li>       
            <li><a href="profile.jsp">Profile</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <b class="caret"></b></a>
              <ul class="dropdown-menu">
             	<li><a href="#contact" data-toggle="modal">Contact</a></li>
                <li><a href="#logout">Logout</a></li>
              </ul>
            </li>            
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
		 
    <!-- Page Content -->
    <div class="container">

        <!-- Title -->
        <div class="row">
            <div id="top" class="col-lg-12">
                <h3>Top Questions</h3>
                <hr>
                                <!-- QUESTIONS WRAPPER START -->
                <div class="qwt-wrapper">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Post your question here
                        </div>
                        <div class="panel-body">
                            <textarea class="form-control" name="yourQuestion" placeholder="What is your question?" rows="3"></textarea>
                            <br />
                            <a href="/feed" class="btn btn-primary btn-sm pull-right">Ask</a>
                            <div class="clearfix"></div>
                            <hr />
                            <ul class="media-list">
                                <li class="media">
                                    <a href="#" class="pull-left">
                                        <div class="userFeed one"></div>
                                    </a>
                                    <div class="media-body">
                                        <span class="text-muted pull-right">
                                            <small class="text-muted">30 min ago</small>
                                        </span>
                                        <strong class="text-success">@ Rexona Kumi</strong>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            Lorem ipsum dolor sit amet, <a href="#"># consectetur adipiscing </a>.
                                        </p>
                                        <p>
                                            <a href="#" class="btn btn-primary btn-sm pull-right">Answer</a>
                                            &nbsp;
                                            <small class="text-muted"> <a href="#">2</a> Answers</small>
                                        </p>
                                    </div>
                                </li>
                                <li class="media">
                                    <a href="#" class="pull-left">
                                        <div class="userFeed one"></div>
                                    </a>
                                    <div class="media-body">
                                        <span class="text-muted pull-right">
                                            <small class="text-muted">30 min ago</small>
                                        </span>
                                        <strong class="text-success">@ John Doe</strong>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            Lorem ipsum dolor <a href="#"># ipsum dolor </a>adipiscing elit.
                                        </p>
                                        <p>
                                            <a href="#" class="btn btn-primary btn-sm pull-right">Answer</a>
                                            &nbsp;
                                            <small class="text-muted"> <a href="#">1</a> Answer</small>
                                        </p>
                                    </div>
                                </li>
                                <li class="media">
                                    <a href="#" class="pull-left">
                                        <div class="userFeed one"></div>
                                    </a>
                                    <div class="media-body">
                                        <span class="text-muted pull-right">
                                            <small class="text-muted">30 min ago</small>
                                        </span>
                                        <strong class="text-success">@ Madonae Jonisyi</strong>
                                        <p>
                                            Lorem ipsum dolor <a href="#"># sit amet</a> sit amet, consectetur adipiscing elit.
                                        </p>
                                        <p>
                                            <a href="#" class="btn btn-primary btn-sm pull-right">Answer</a>
                                            &nbsp;
                                            <small class="text-muted"> <a href="#">4</a> Answers</small>
                                        </p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                 <!-- Question WRAPPER END -->
            </div>
        </div>
        <!-- /.row -->

        <!-- Feed content -->
        <div class="row text-center">

            <div class="col-md-3 col-sm-6 hero-feature">
                
            </div>
            

    <!-- Fixed footer -->
    <div class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
    	<div class="container">
    		<div class="navbar-text pull-left">
    			<p>© 2016 Ask.me</p>
    		</div>
    	</div>
    </div>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <div class="modal fade" id="contact" role="dialog">
    	<div class="modal-dialog">
    		<div class="modal-content">
    			<form class="form-horizontal" role="form">
    				<div class="modal-header">
    					<h4>Contact<h4>
	    			</div>
	    			<div class="modal-body">
    					<div class="form-group">
    						<label for="contact-name" class="col-sm-2 control-label">Name</label>
    						<div class="col-sm-10">
    							<input type="text" class="form-control" id="contact-name" placeholder="First & Last Name">
    						</div>
    					</div>
    					<div class="form-group">
    						<label for="contact-email" class="col-sm-2 control-label">Email</label>
    						<div class="col-sm-10">
    							<input type="email" class="form-control" id="contact-email" placeholder="example@domain.com">
    						</div>
    					</div>
    					<div class="form-group">
    						<label for="contact-message" class="col-sm-2 control-label">Message</label>
    						<div class="col-sm-10">
    							<textarea class="form-control" rows="4"></textarea>
    						</div>
    					</div>
	    			</div>
	    			<div class="modal-footer">
    					<a class="btn btn-default" data-dismiss="modal">Close</a>
    					<button type="submit" class="btn btn-primary">Send</button>
    				</div>
    			</form>
    		</div>
    	</div>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  </body>
</html>