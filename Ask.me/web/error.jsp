<%-- 
    Document   : Error
    Created on : Dec 7, 2016, 9:26:59 AM
    Author     : saudalhilali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Ask.me | Error</title>

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
                    <a class="navbar-brand" href="index.jsp">Ask.me</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="index.jsp">Home</a></li>       
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#contact" data-toggle="modal">Contact</a></li>
                            </ul>
                        </li>            
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <!-- Page Content -->
        <div class="container">

            <!-- Error WRAPPER START -->
            <div class="login-page">
                <div class="form">
                    <legend><strong>Oops! :(</strong></legend>
                        <div>${errorMessage}</div>
                        <p class="text-danger">There seem to be something wrong with your requested page.</p>
                        <a href="/Ask.me/index.jsp"><button>take me back home</button></a>
                </div>
            </div>
            <!-- Error WRAPPER END -->
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