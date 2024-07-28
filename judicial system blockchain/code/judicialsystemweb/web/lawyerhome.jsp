<!DOCTYPE html>
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
                 
                  <li class="active"><a href="lawyerhome.jsp">View All Cases</a></li>
                                    
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
        if (msg.equals("FILEDOWNLOADED"))
        {
            %>
            <script>
                alert('file downloaded successfully!');
                </script>
            <%
        }
        else
         if (msg.equals("COMMENTUPDATED"))
        {
            %>
            <script>
                alert('Comment updated successfully!');
                </script>
            <%
        }
        else
        {
            {
            %>
            <script>
                alert('file download failed!');
                </script>
            <%
        }
        }
            
      } 
      
  %>

    <div class="site-section section-6">
      <div class="container">
          
          <h1 >Welcome Lawyer <%=session.getAttribute("uname")%></h1>
          <br>
          <h1 align="center">All Cases</h1>
          <table align="center" width="1000" border="1">
              <tr>
                  <th>USER</th>
                  <th>CASE ID</th>
                  <th>CASE NAME</th>
                  <th>DETAILS</th>
                  <th>JUDGE</th>
                  <th>JUDGE COMMENT</th>
                  <th>YOUR COMMENT</th>
                  <th>STATUS</th>
                  <th>VIEW FILE</th>
                  <th>UPDATE COMMENT</th>
              </tr>
              
                <%@page import="java.net.*,java.io.*,java.util.*"%>
              <%
                try
                {
                    Socket soc=new Socket("localhost",3000);
                    System.out.println("socket conneted");
                    ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
                    ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
                    oos.writeObject("GETLAWYERCASES");
                    //oos.writeObject((String)session.getAttribute("uname"));
                    oos.writeObject(Integer.toString((Integer)session.getAttribute("id")));
                    Vector v=(Vector)oin.readObject();
                    
                    if (v.size()==0)
                    {
                        %>
                        <script>
                            alert('No Data found!');
                        </script>    
                        <%
                    }
                    else
                    {
                        for (int i=0;i<v.size();i++)
                        {
                            StringTokenizer st=new StringTokenizer(v.get(i).toString().trim(),"$");
                            
                            while (st.hasMoreTokens())
                            {
                                %>
                                <tr>
                                    <td><%=st.nextToken()%></td>
                                    <%String caseid=st.nextToken();%>
                                    <td><%=caseid%></td>
                                    <td><%=st.nextToken()%></td>
                                    <td><%=st.nextToken()%></td>
                                    <td><%=st.nextToken()%></td>
                                    <td><%=st.nextToken()%></td>
                                    <td><%=st.nextToken()%></td>
                                    <td><%=st.nextToken()%></td>
                                    <%String fname=st.nextToken();%>
                                    <td><a href="sendotp?caseid=<%=caseid%>&fname=<%=fname%>"><%=fname%></a></td>
                                    <td>
                                        <form name="updatelcomment" method=get action="updatelcomment.jsp">
                                            <input type="hidden" name="caseid" value="<%=caseid%>" />
                                            <input type="submit" name="submit" value="Update Comment" />
                                        </form>
                                    </td>
                                </tr>
                                <%
                            }
                            
                        }
                    }
                        
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    e.printStackTrace();
                }
              %>
          </table>
              

   
    
      </div>
    </div>






  

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