<%-- 
    Document   : feed
    Created on : Dec 7, 2016, 9:19:45 AM
    Author     : saudalhilali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="db.DBQueryBean" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Ask.me | Question</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/custom.css" rel="stylesheet">

        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    </head>
    <%@ page import="java.sql.*" %>
    <%
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn == null || !loggedIn.booleanValue()) {
            request.setAttribute("errorMessage", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                    + "  <strong>Oh snap!</strong> You need to be logged in to access this page."
                    + "</div>");
    %>
    <jsp:forward page="login.jsp" />
    <%
        }
        
        Boolean admin = false;
        String adminQuery = "SELECT admin FROM members WHERE username = '" + session.getAttribute("userid") + "'";
        DBQueryBean adminDb = new DBQueryBean();
        ResultSet adminSet = adminDb.doQuery(adminQuery);
        if (adminSet.next()) {
            admin = adminSet.getBoolean("admin");
        }
    %>
    <%
        String askerImage = "";
        DBQueryBean db = new DBQueryBean();
        String picQuery = "SELECT image FROM members WHERE username = '" + request.getAttribute("asker") + "'";
        ResultSet memPic = db.doQuery(picQuery);
        if (memPic.next()) {
            if (memPic.getString("image") != null) {
                askerImage = memPic.getString("image");
            }
            if (!askerImage.endsWith(".jpeg") && !askerImage.endsWith(".jpg") && !askerImage.endsWith(".png")) {
                askerImage = "http://placehold.it/350x150";
            }
        }

    %>
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
                        <li class="active"><a href="feed.jsp">Home</a></li>       
                        <li><a href="profile.jsp">Profile</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#contact" data-toggle="modal">Contact</a></li>
                                <li><a href="logout">Logout</a></li>
                            </ul>
                        </li>            
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <!-- Page Content -->
        <div class="container">
            <div id="top" class="col-lg-12" style="margin-bottom: 60px">
                ${topBar}
                <h3><div><a href="<%=request.getContextPath()%>/${asker}" class="pull-left">
                            <div class="userAnswer" style="background-image: url('<%=askerImage%>');"></div>
                        </a><div>${question}</div>
                        <p style="font-size: 20px;">by <a href="<%=request.getContextPath()%>/${asker}"><strong class="text" style="color: #FC6544">@${asker}
                                </strong></a>
                        </p>
                    </div>
                </h3>
                <hr>
            </div>

            <div id="top" class="col-lg-12">

                <!-- Title -->
                <div class="row">

                    <!-- Answers WRAPPER START -->
                    <div class="qwt-wrapper">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                Answers
                            </div>
                            <div class="panel-body">
                                <div class="clearfix"></div>
                                <%! String answerId;
                                    int questionId;%>
                                <%
                                    if (request.getParameter("questionId") == null) {
                                        response.sendRedirect("error.jsp");
                                    }
                                    questionId = Integer.parseInt(request.getParameter("questionId"));

                                    session.setAttribute("questionId", questionId);
                                    String query = "SELECT answer, memberId, answerId FROM answers WHERE questionId = " + questionId + " ORDER BY answerId DESC";
                                    DBQueryBean ddb = new DBQueryBean();
                                    ResultSet resultSet = ddb.doQuery(query);
                                    while (resultSet.next()) {
                                        String answer = resultSet.getString("answer");
                                        int memberId = resultSet.getInt("memberId");
                                        answerId = resultSet.getString("answerId");
                                        String memQuery = "SELECT username, image FROM members WHERE memberId = " + memberId;
                                        ResultSet member = db.doQuery(memQuery);
                                        member.next();
                                        String username = member.getString(1);
                                        String image = member.getString(2);
                                        if (member.wasNull() || !image.endsWith(".jpeg") && !image.endsWith(".jpg") && !image.endsWith(".png")) {
                                            image = "http://placehold.it/350x150";
                                            image = "http://placehold.it/350x150";
                                        }

                                %>
                                <li class="media">
                                    <a href="<%=request.getContextPath()%>/<%=username%>" class="pull-left">
                                        <div class="userFeed" style="background-image: url('<%=image%>');"></div>
                                    </a>
                                    <div class="media-body"><a href="<%=request.getContextPath()%>/<%=username%>">
                                            <strong class="text" style="color: #FC6544">@<%= username%></strong>
                                        </a><h3 style=" margin-top: auto; margin-right: 90px; ">
                                            <%= answer%>
                                        </h3>

                                        <%
                                        if (admin != null && admin.booleanValue()) {
                                        %>
                                        <a href="Delete?type=answer&id=<%=answerId%>" class="smBtn pull-right" style="margin-top: -4em; margin-right: 10px; background: #a94442;">Delete</a>
                                        <%
                                            }
                                        %>
                                    </div>
                                    <hr/>
                                </li>


                                <%
                                    }
                                %>
                                <ul class="media-list">



                                    <form action="answer?questionId=<%=questionId%>" method="POST">
                                        <textarea class="form-control" name="yourAnswer" id="yourAnswer" placeholder="Your answer" rows="3"></textarea>
                                        &nbsp;<button name="postAnswer" value="Add" href="answer?questionId=<%=questionId%>" class="btn btn-primary btn-sm center-block">Answer</button>
                                    </form>

                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- Answers WRAPPER END -->
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