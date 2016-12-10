<%-- 
    Document   : profile
    Created on : Dec 7, 2016, 9:23:07 AM
    Author     : saudalhilali
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="db.DBQueryBean"%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Ask.me | Profile</title>

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
    <%! String name, gender, image, email;
        Date date;%> 
    <%
        }
        String query = "SELECT name, dob, gender, image, email FROM members WHERE username = '" + session.getAttribute("userid") + "'";
        DBQueryBean db = new DBQueryBean();
        ResultSet resultSet = db.doQuery(query);
        while (resultSet.next()) {
            name = resultSet.getString("name");
            date = resultSet.getDate("dob");
            gender = resultSet.getString("gender");
            image = resultSet.getString("image");
            email = resultSet.getString("email");
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
                        <li><a href="feed.jsp">Home</a></li>       
                        <li class="active"><a href="profile.jsp">Profile</a></li>
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

            <!-- Title -->
            <div class="row">
                <div id="top" class="col-lg-12">
                    ${topBar}
                    <h3>Profile</h3>
                    <hr>
                    <!-- Profile WRAPPER START -->
                    <div class="container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#profile">Profile</a></li>
                            <li><a data-toggle="tab" href="#edit">Edit Profile</a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="profile" class="tab-pane fade in active">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title" style="text-align: center;">
                                            Hello, <% out.print(name);%>!
                                        </h3>
                                    </div>
                                    <div class="panel-body">
                                        <div>
                                            <div>
                                                <div class="row">
                                                    <div class="user one"></div>
                                                </div>
                                                <div> 
                                                    &nbsp;
                                                    <div class="container" style="width: 500px;">
                                                    </div>
                                                    <table class="table table-user-information">
                                                        <tbody>
                                                             <tr>
                                                                <td>Username:</td>
                                                                <td>@<%=session.getAttribute("userid")%> </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Date of Birth</td>
                                                                <td><% out.print(date.toString());%></td>
                                                            </tr>
                                                            <tr>
                                                            <tr>
                                                                <td>Gender</td>
                                                                <% if (gender.equalsIgnoreCase("m")) {%>
                                                                <td>Male</td>
                                                                <% } else {
                                                                %>
                                                                <td>Female</td>
                                                                <%
                                                                    }
                                                                %>
                                                            </tr>
                                                            <tr>
                                                                <td>Email</td>
                                                                <td><a href="mailto:<%=email%>"><%= email %></a></td>
                                                            </tr>


                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div> 
                            </div>
                            <div id="edit" class="tab-pane fade">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title" style="text-align: center;">
                                            Here you can edit your profile.
                                        </h3>
                                    </div>
                                    <div class="panel-body">
                                        <div id='profile' class="panel">
                                            <form class="form-horizontal" action="edit" method="POST">
                                                <fieldset>
                                                    <!-- Update full name-->
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="newName">Full Name</label>  
                                                        <div class="col-md-4">
                                                            <input id="newName" name="newName" type="text" value="<%=name%>"< class="form-control input-md">

                                                        </div>
                                                    </div>
                                                            
                                                    <!-- Update username-->
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="newUsername">Username</label>  
                                                        <div class="col-md-4">
                                                            <input id="newUsername" name="newUsername" type="text" value="${userid}" class="form-control input-md">

                                                        </div>
                                                    </div>

                                                    <!-- Update password-->
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="newPassword">Password</label>  
                                                        <div class="col-md-4">
                                                            <input id="newPassword" name="newPassword" type="password" placeholder="New Password" class="form-control input-md">

                                                        </div>
                                                    </div>

                                                    <!-- Update password confirm-->
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="newPassword">Confirm Password</label>  
                                                        <div class="col-md-4">
                                                            <input id="conNewPassword" name="conNewPassword" type="password" placeholder="New Password" class="form-control input-md">

                                                        </div>
                                                    </div>


                                                    <!-- Update DOB -->
                                                    <div class="form-group">
                                                        <div>
                                                        <label for="newDOBDay" class="col-md-4 control-label">Date of birth</label>
                                                        </div>
                                                        
                                                        <div class="col-xs-4 col-md-2">   
                                                            <select name="newDOBDay" id="newDOBDay" class="form-control">
                                                                <option value="">Day</option>
                                                                <option value="">---</option>
                                                                <option value="01">01</option>
                                                                <option value="02">02</option>
                                                                <option value="03">03</option>
                                                                <option value="04">04</option>
                                                                <option value="05">05</option>
                                                                <option value="06">06</option>
                                                                <option value="07">07</option>
                                                                <option value="08">08</option>
                                                                <option value="09">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                            </select>
                                                        </div>

                                                        <div class="col-xs-4 col-md-3">
                                                            <select name="newDOBMonth" id="newDOBMonth" class="form-control">
                                                                <option value="">Month</option>
                                                                <option value="">-----</option>
                                                                <option value="01">January</option>
                                                                <option value="02">February</option>
                                                                <option value="03">March</option>
                                                                <option value="04">April</option>
                                                                <option value="05">May</option>
                                                                <option value="06">June</option>
                                                                <option value="07">July</option>
                                                                <option value="08">August</option>
                                                                <option value="09">September</option>
                                                                <option value="10">October</option>
                                                                <option value="11">November</option>
                                                                <option value="12">December</option>
                                                            </select>
                                                        </div>

                                                        <div class="col-xs-4 col-md-2">
                                                            <select name="newDOBYear" id="newDOBYear" class="form-control">
                                                                <option value="">Year</option>
                                                                <option value="">----</option>
                                                                <option value="2012">2012</option>
                                                                <option value="2011">2011</option>
                                                                <option value="2010">2010</option>
                                                                <option value="2009">2009</option>
                                                                <option value="2008">2008</option>
                                                                <option value="2007">2007</option>
                                                                <option value="2006">2006</option>
                                                                <option value="2005">2005</option>
                                                                <option value="2004">2004</option>
                                                                <option value="2003">2003</option>
                                                                <option value="2002">2002</option>
                                                                <option value="2001">2001</option>
                                                                <option value="2000">2000</option>
                                                                <option value="1999">1999</option>
                                                                <option value="1998">1998</option>
                                                                <option value="1997">1997</option>
                                                                <option value="1996">1996</option>
                                                                <option value="1995">1995</option>
                                                                <option value="1994">1994</option>
                                                                <option value="1993">1993</option>
                                                                <option value="1992">1992</option>
                                                                <option value="1991">1991</option>
                                                                <option value="1990">1990</option>
                                                                <option value="1989">1989</option>
                                                                <option value="1988">1988</option>
                                                                <option value="1987">1987</option>
                                                                <option value="1986">1986</option>
                                                                <option value="1985">1985</option>
                                                                <option value="1984">1984</option>
                                                                <option value="1983">1983</option>
                                                                <option value="1982">1982</option>
                                                                <option value="1981">1981</option>
                                                                <option value="1980">1980</option>
                                                                <option value="1979">1979</option>
                                                                <option value="1978">1978</option>
                                                                <option value="1977">1977</option>
                                                                <option value="1976">1976</option>
                                                                <option value="1975">1975</option>
                                                                <option value="1974">1974</option>
                                                                <option value="1973">1973</option>
                                                                <option value="1972">1972</option>
                                                                <option value="1971">1971</option>
                                                                <option value="1970">1970</option>
                                                                <option value="1969">1969</option>
                                                                <option value="1968">1968</option>
                                                                <option value="1967">1967</option>
                                                                <option value="1966">1966</option>
                                                                <option value="1965">1965</option>
                                                                <option value="1964">1964</option>
                                                                <option value="1963">1963</option>
                                                                <option value="1962">1962</option>
                                                                <option value="1961">1961</option>
                                                                <option value="1960">1960</option>
                                                                <option value="1959">1959</option>
                                                                <option value="1958">1958</option>
                                                                <option value="1957">1957</option>
                                                                <option value="1956">1956</option>
                                                                <option value="1955">1955</option>
                                                                <option value="1954">1954</option>
                                                                <option value="1953">1953</option>
                                                                <option value="1952">1952</option>
                                                                <option value="1951">1951</option>
                                                                <option value="1950">1950</option>
                                                                <option value="1949">1949</option>
                                                                <option value="1948">1948</option>
                                                                <option value="1947">1947</option>
                                                                <option value="1946">1946</option>
                                                                <option value="1945">1945</option>
                                                                <option value="1944">1944</option>
                                                                <option value="1943">1943</option>
                                                                <option value="1942">1942</option>
                                                                <option value="1941">1941</option>
                                                                <option value="1940">1940</option>
                                                                <option value="1939">1939</option>
                                                                <option value="1938">1938</option>
                                                                <option value="1937">1937</option>
                                                                <option value="1936">1936</option>
                                                                <option value="1935">1935</option>
                                                                <option value="1934">1934</option>
                                                                <option value="1933">1933</option>
                                                                <option value="1932">1932</option>
                                                                <option value="1931">1931</option>
                                                                <option value="1930">1930</option>
                                                                <option value="1929">1929</option>
                                                                <option value="1928">1928</option>
                                                                <option value="1927">1927</option>
                                                                <option value="1926">1926</option>
                                                                <option value="1925">1925</option>
                                                                <option value="1924">1924</option>
                                                                <option value="1923">1923</option>
                                                                <option value="1922">1922</option>
                                                                <option value="1921">1921</option>
                                                                <option value="1920">1920</option>
                                                                <option value="1919">1919</option>
                                                                <option value="1918">1918</option>
                                                                <option value="1917">1917</option>
                                                                <option value="1916">1916</option>
                                                                <option value="1915">1915</option>
                                                                <option value="1914">1914</option>
                                                                <option value="1913">1913</option>
                                                                <option value="1912">1912</option>
                                                                <option value="1911">1911</option>
                                                                <option value="1910">1910</option>
                                                                <option value="1909">1909</option>
                                                                <option value="1908">1908</option>
                                                                <option value="1907">1907</option>
                                                                <option value="1906">1906</option>
                                                                <option value="1905">1905</option>
                                                                <option value="1904">1904</option>
                                                                <option value="1903">1903</option>
                                                                <option value="1901">1901</option>
                                                                <option value="1900">1900</option>
                                                            </select>
                                                        </div>  
                                                    </div>

                                                    <!-- Update gender -->
                                                    <div class="form-group">
                                                        <div>
                                                        <label class="col-md-4 control-label" for="newGender">Gender</label>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <select id="newGender" name="newGender" class="form-control">
                                                                <option value="Male">Male</option>
                                                                <option value="Female">Female</option>
                                                            </select>
                                                        </div>
                                                    </div>


                                                    <!-- Update photo --> 
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="newPhoto">Upload photo</label>
                                                        <div class="col-md-4">
                                                            <input id="newPhoto" name="newPhoto" class="input-file" type="file">
                                                        </div>
                                                    </div>

                                                    <!-- Update-->
                                                    <div class="form-group">
                                                        <label class="col-md-4 control-label" for="singlebutton"></label>
                                                        <div class="col-md-4">
                                                            <button id="singlebutton" name="singlebutton" class="btn btn-primary">Update</button>
                                                        </div>
                                                    </div>

                                                </fieldset>
                                            </form>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
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