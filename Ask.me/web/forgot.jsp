<%-- 
    Document   : Forgot password
    Created on : Dec 7, 2016, 9:27:29 AM
    Author     : saudalhilali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Ask.me | Retrieve Password </title>

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
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <!-- Page Content -->
        <!--Profile WRAPPER END -->
        <div class="login-page">
            <div class="form">
                <legend><strong>Forgot Password? </strong></legend>
                <div>${errorMessage}</div>
                <span class="help-block">In order to update your password. You need to fill out this form.</span>

                <form action="forgot" method="POST" class="login-form" data-toggle="validator" role="form">
                    <input name="type" value="checkQA" type="hidden"></input>

                    <input type="text" id="username" name="username" value="" placeholder="Your Username" required/>

                    <label>Your Security Question</label>
                    <div class="row">
                        <div style="margin-left: 16px; margin-right: 16px">
                            <select id="secQuestion" name="secQuestion" class="form-control input-lg">
                                <option value="What was your childhood nickname?">What was your childhood nickname?</option>
                                <option value="What is your favorite team?">What is your favorite team?</option>
                                <option value="What is your favorite movie?">What is your favorite movie?</option>
                                <option value="What was the make of your first car?">What was the make of your first car?</option>
                                <option value="Who is your childhood sports hero?">Who is your childhood sports hero?</option>
                            </select>                           
                        </div>
                    </div>
                    <input type="text" id="secAnswer" name="secAnswer" value="" placeholder="Your Answer" required/>

                    <button type="submit">Update my password</button>
                </form>
            </div>
        </div>
        <!-- /.row -->





        <!-- Fixed footer -->
        <div class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
            <div class="container">
                <div class="navbar-text pull-left">
                    <p>© 2016 Ask.me</p>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>