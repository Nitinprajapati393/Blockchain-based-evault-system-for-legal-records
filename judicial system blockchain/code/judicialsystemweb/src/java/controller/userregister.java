/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbpack.dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "userregister", urlPatterns = {"/userregister"})
public class userregister extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String fname=request.getParameter("fname");
            String age=request.getParameter("age");
            String address=request.getParameter("address");
            String no=request.getParameter("mno");
            String email=request.getParameter("email");
            String uname=request.getParameter("uname");
            String pass=request.getParameter("pass");
            
            Connection con=new dbconnection().getconnection();
            
            PreparedStatement pst2=con.prepareStatement("select * from user where uname=?");
            pst2.setString(1,uname);
            ResultSet rs=pst2.executeQuery();
            
            if (rs.next())
            {
                response.sendRedirect("userregister.jsp?msg=ALREADY");
            }
            else
            {
            
            PreparedStatement pst=con.prepareStatement("insert into user(cname,age,address,contact,email,uname,pass) values(?,?,?,?,?,?,?)");
            pst.setString(1,fname);
            pst.setString(2,age);
            pst.setString(3,address);
            pst.setString(4,no);
            pst.setString(5,email);
            pst.setString(6,uname);
            pst.setString(7,pass);
            pst.executeUpdate();
            
            response.sendRedirect("userregister.jsp?msg=SUCCESS");
            
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
            response.sendRedirect("userregister.jsp?msg=FAILED");
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
