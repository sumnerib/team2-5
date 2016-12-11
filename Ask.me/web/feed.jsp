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

        <title>Ask.me | Feed</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/custom.css" rel="stylesheet">

        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    </head>
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
                                <li><a href="logout">Logout</a></li>
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
                    ${topBar}
                    <h3>Questions</h3>
                    <hr>
                    <!-- QUESTIONS WRAPPER START -->
                    <div class="qwt-wrapper">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title" style="text-align: center;">
                                    Post your question here
                                </h3>
                            </div>
                            <div class="panel-body">
                                <form action="feed" method="GET">
                                    <textarea class="form-control" name="yourQuestion" id="yourQuestion" placeholder="What is your question?" rows="3"></textarea>
                                    &nbsp;<button href="fd" class="btn btn-primary btn-sm center-block">Ask</button>
                                </form>
                                <div class="clearfix"></div>
                                <hr />
                                <ul class="media-list">
                                    <%@ page import="java.sql.*" %>
                                    <%
                                        String query = "SELECT questionId, question, memberId FROM questions ORDER BY questionId DESC limit 5";
                                        DBQueryBean db = new DBQueryBean();
                                        ResultSet resultSet = db.doQuery(query);
                                        while (resultSet.next()) {
                                            String question = resultSet.getString("question");
                                            int questionId = resultSet.getInt("questionId");
                                            int memberId = resultSet.getInt("memberId");
                                            String memQuery = "SELECT username, image FROM members WHERE memberId = " + memberId;
                                            ResultSet member = db.doQuery(memQuery);
                                            member.next();
                                            String username = member.getString("username");
                                            String image = member.getString("image");
                                            if (!image.endsWith(".jpeg") && !image.endsWith(".jpg") && !image.endsWith(".png")) {
                                                image = "http://placehold.it/350x150";
                                            } else if (member.wasNull()) {
                                                image = "http://placehold.it/350x150";
                                            }
                                    %>


                                    <li class="media">
                                        <a href="<%=request.getContextPath()%>/<%=username%>" class="pull-left">
                                            <div class="userFeed" style="background-image: url('<%=image%>');"></div>
                                        </a>
                                        <div class="media-body">
                                            <a href="<%=request.getContextPath()%>/<%=username%>">
                                                <strong class="text" style="color: #FC6544">@<%= username%></strong>
                                            </a>
                                            <h3 style=" margin-top: auto; margin-right: 90px; ">
                                                <%= question%>
                                            </h3>
                                            <p>

                                                <a href="answer?questionId=<%=questionId%>" class="smBtn pull-right" style="margin-top: -2em;">Answer</a>
                                                <%
                                                    if (admin != null && admin.booleanValue()) {
                                                %>
                                                <a href="Delete?type=question&id=<%=questionId%>" class="smBtn pull-right" style="margin-top: -2em; margin-right: 10px; background: #a94442;">Delete</a>
                                                <%
                                                    }
                                                %>
                                                &nbsp;
                                                <%! int answerNum = 0;%>
                                                <%
                                                    String countQuery = "SELECT COUNT(*) FROM answers WHERE questionId = " + questionId;
                                                    DBQueryBean ddb = new DBQueryBean();
                                                    ResultSet rs = ddb.doQuery(countQuery);
                                                    if (rs.next()) {
                                                        answerNum = rs.getInt(1);
                                                    }
                                                %>
                                                <small class="text-muted"> <a href="<%=request.getContextPath()%>/answer?questionId=<%=questionId%>"><%= answerNum%></a> Answers</small>
                                            </p>
                                        </div>
                                        <hr/>
                                    </li>

                                    <%
                                        }
                                    %>

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


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
                </body>
                </html>