<!DOCTYPE html>
<%@page import="dbpack.dbconnection"%>
<%@page import="java.sql.*"%>
<html lang="en">
  <head>
    <title>Judicial &mdash; Colorlib Website Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,700,900|Oswald:400,700"> 
    <link rel="stylesheet" href="fonts/icomoon/style.css">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/mediaelementplayer.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
    <link rel="stylesheet" href="css/fl-bigmug-line.css">
    
  
    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/style.css">
    
  </head>
  <body bgcolor="red">
  
    
  
  <div class="site-wrap">

    <div class="site-navbar mt-4">
        <div class="container py-1">
          <div class="row align-items-center">
            <div class="col-8 col-md-8 col-lg-4">
              <h1 class="mb-0"><a href="index.html" class="text-white h2 mb-0"><strong class="text-uppercase">Judicial<span class="text-primary">.</span></strong></a></h1>
            </div>
            <div class="col-4 col-md-4 col-lg-8">
              <nav class="site-navigation text-right text-md-right" role="navigation">

                <div class="d-inline-block d-lg-none ml-md-0 mr-auto py-3"><a href="#" class="site-menu-toggle js-menu-toggle text-white"><span class="icon-menu h3"></span></a></div>

                <ul class="site-menu js-clone-nav d-none d-lg-block">
                 
                  <li class="active"><a href="userhome.jsp">Add New Case</a></li>
                  <li><a href="viewusercases.jsp">View Cases</a></li>
                  
                  <li><a href="logout.jsp">Logout</a></li>
                  
                </ul>
              </nav>
            </div>
           

          </div>
        </div>
      </div>
    </div>

  </div>   
  
    
   


    <div class="site-section section-5">
      <div class="container">
      
      </div>
    </div>
  
  <%
          if (request.getParameter("msg")!=null)
          {
              
            String msg=request.getParameter("msg");
            
            if (msg.equals("SUCCESS"))
            {
                
                     String caseid=request.getParameter("caseid");     
                
      %>
                <p align="center">
                    <font color="red">case details added successfully! Your case ID is <%=caseid%></font>
                </p>
      <%
            }
            
            else
            {
                %>
                <p align="center">
                    <font color="red">Failed to connect to Blockchain Server</font>
                </p>
                 <%
            }
                
          }
      %>
  
  <%
  try
  {
      Connection con=new dbconnection().getconnection();
  
      
  %>

    <div class="site-section section-6">
      <div class="container">
          
          <h1 >Welcome User <%=session.getAttribute("uname")%></h1>
          <br>
          <h1 align="center">Add New Case</h1>
    <form name="addcase" method="post" action="addcase" enctype="multipart/form-data">
    <table align="center" width="600" height="120">
   
        <tr>
            <td>Case Name</td>
            <td> <input type=text name=cname required/></td>
        </tr>
        <tr>
            <td>Case Details</td>
            <td> <input type=text name=details required/></td>
        </tr>
        <tr>
            <td>Select File</td>
            <td> <input type=file name=cfile required/></td>
        </tr>
        <tr>
            <td>Select Lawyer</td>
            <td>
                
                <select name="lawyer">
                    <%
                    PreparedStatement pst=con.prepareStatement("select id,lname from lawyer");
                    ResultSet rs=pst.executeQuery();
                    
                    while (rs.next())
                    {
                        %>
                        <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>
                        <%
                    }
                    
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Select Judge</td>
            <td>
                <select name="judge">
                     <%
                    PreparedStatement pst1=con.prepareStatement("select id,jname from judge");
                    ResultSet rs1=pst1.executeQuery();
                    
                    while (rs1.next())
                    {
                        %>
                        <option value="<%=rs1.getInt(1)%>"><%=rs1.getString(2)%></option>
                        <%
                    }
                    
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="addcase" value="Add Case" style="height:30px; width:100px"/>
            <input type="reset" name="reset" value="Reset" style="height:30px; width:100px"/>
            </td>
            
        </tr>
    </table>
</form>
    
   
    
      </div>
    </div>

<%
}
catch(Exception e)
{
    out.println(e);
    e.printStackTrace();
}

%>


  

  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/mediaelement-and-player.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/jquery.countdown.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/bootstrap-datepicker.min.js"></script>
  <script src="js/aos.js"></script>
  <!-- <script src="js/circleaudioplayer.js"></script> -->

  <script src="js/main.js"></script>
    
  </body>
</html>