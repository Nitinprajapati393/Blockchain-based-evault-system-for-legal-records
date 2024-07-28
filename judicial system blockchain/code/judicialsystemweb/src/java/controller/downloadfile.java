/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "downloadfile", urlPatterns = {"/downloadfile"})
public class downloadfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/msword");
        String fname=request.getParameter("fname");
        response.setHeader("Content-Disposition",
                           "attachment; filename=\""
                               + fname + "\"");
        try
		{
			PrintWriter out = response.getWriter();
			
			//String path = request.getSession().getServletContext().getRealPath("/");
			//int a = path.indexOf('.');
			//String storingpath = path.substring(0, a);
			
			
			String photopath = "C:/cases/";
			
			response.setContentType("application/msword");
	        response.setHeader("Content-Disposition",
	                           "attachment; filename=\""
	                               + fname + "\"");
	        
	        FileInputStream inputStream = new FileInputStream(photopath + fname);
	        
	        int in;
	        while ((in = inputStream.read()) != -1) {
	            out.write(in);
	        }
	 
	        // Close FileInputStream and PrintWriter object
	        inputStream.close();
	        out.close();
                HttpSession s=request.getSession();
                s.removeAttribute("OTP");
                
                response.setContentType("text/html");
                out.println("<h1>FIle Downloaded Successfully!!</h1>");
                out.println("<br>");
                out.println("<a href=\"lawyerhome.jsp\">BACK</a>");
               // RequestDispatcher dd = request.getRequestDispatcher("lawyerhome.jsp?msg=FILEDOWNLOADED");

                //dd.forward(request, response);
	        //response.sendRedirect("lawyerhome.jsp?msg=FILEDOWNLOADED");
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
                        RequestDispatcher dd = request.getRequestDispatcher("lawyerhome.jsp?msg=DOWNLOADFAILED");

                         dd.forward(request, response);
                        //response.sendRedirect("lawyerhome.jsp?msg=DOWNLOADFAILED");
		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
