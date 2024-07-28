/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbpack.dbconnection;
import email.SendMailExample;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
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
@WebServlet(name = "sendotp", urlPatterns = {"/sendotp"})
public class sendotp extends HttpServlet {

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
            String caseid=request.getParameter("caseid");
            String fname=request.getParameter("fname");
            HttpSession s=request.getSession();
            String uname=(String)s.getAttribute("uname");
            Random r=new Random();
            String otp=""+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
            otp.trim();
            s.setAttribute("OTP", otp);
            String msg="OTP for file "+fname+", case id: "+caseid+" is "+otp;
            
            Connection con=new dbconnection().getconnection();
            
            PreparedStatement pst=con.prepareStatement("select email from lawyer where uname=?");
            pst.setString(1,uname);
            ResultSet rs=pst.executeQuery();
            rs.next();
            String reply=new SendMailExample().main(rs.getString(1),msg);
            
            if (reply.equals("SUCCESS"))
            {
                response.sendRedirect("otpchecklawyer.jsp?caseid="+caseid+"&fname="+fname);
            }
            else
            {
                out.println("otp sending failed!");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
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
