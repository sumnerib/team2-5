<%-- 
    Document   : index
    Created on : Dec 9, 2016, 11:28:50 AM
    Author     : saudalhilali
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Ask.me | Homepage</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    
        <style>
            body {
                background-image: url("img/back.png");
                background-repeat: repeat-x;
            }
            #main {
                padding-top: 200px;
                padding-right: 100px;
                padding-bottom: 300px;
                padding-left: 100px;
                
            }
            hr {
                display: block;
                height: 1px;
                border: 0;
                border-top: 1px solid #ccc;
                margin: 1em 0;
                padding: 0; 
            }
            
            footer {
                padding-right: 50px;
                padding-left: 50px;
                text-align: right;
                
            }
            
            #login {
                color: white;
                padding-top: 5px;
            }
            
            #caption {
                color: white;
                font-size: 11px;
            }
            
            .btn {
                font-family: "Roboto", sans-serif;
                text-transform: uppercase;
                outline: 0;
                background: #FC6544;
                width: 300px;
                border: 0;
                padding: 15px;
                color: #FFFFFF;
                font-size: 14px;
                -webkit-transition: all 0.3 ease;
                transition: all 0.3 ease;
                cursor: pointer;
              }
              .btn:hover,.btn:active,.btn:focus {
                color: #FFFFFF;
                background: #FB6241;
              }
        </style>
    </head>
    <%
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn.booleanValue()) {
    %>
    <jsp:forward page="feed.jsp" />
    <%
        }
    %>
    <body>
        <div style="text-align:center;" id="main">
            <p><img src="img/askme-logo.png" alt="Ask.me logo">
            </p>
            <p id="caption">Get All of your questions answered here.</p>
            <div id='cont'>
                <a href="register.jsp"><button type="submit" class="btn">Sign Up</button></a>
            </div>
            <p id="login">Or <a href="login.jsp">Log in </a></p>
        </div>
        <!-- Footer -->
        <footer>
            <hr>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Ask.me 2016</p>
                </div>
            </div>
        </footer>
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
