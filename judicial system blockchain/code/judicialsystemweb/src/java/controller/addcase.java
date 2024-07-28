/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadFile;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "addcase", urlPatterns = {"/addcase"})
public class addcase extends HttpServlet {

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
            
		String photopath = "";
		String FileName = "";
		String photoserverpath = "", photodbpath = "";
		String photofolder = "", photofolder1 = "";
		Hashtable ht;
		Enumeration e;
		UploadFile file;
                
            MultipartFormDataRequest mp = new MultipartFormDataRequest(request);

			javazoom.upload.UploadBean upb = new javazoom.upload.UploadBean();
			photopath = "C:/cases";
			upb.setFolderstore(photopath);
			upb.setOverwrite(true);
			upb.store(mp);
			ht = mp.getFiles();
			e = ht.elements();
			while (e.hasMoreElements()) {
				UploadFile uf = (UploadFile) e.nextElement();
				
				FileName = uf.getFileName();
				photoserverpath = "" + "/" + uf.getFileName();
				photodbpath =  uf.getFileName();
				String cname = mp.getParameter("cname");
				String details = mp.getParameter("details");
                                String lawyer = mp.getParameter("lawyer");
                                String judge = mp.getParameter("judge");
                                
                                String caseid=""+(1000+new Random().nextInt(10000));
                                caseid.trim();
                                //ServerSocket ss=new ServerSocket(5000);
                                Socket soc=new Socket("localhost",3000);
                                System.out.println("socket conneted");
                                ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
                                ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
                                System.out.println("streams created");
            
                                HttpSession session=request.getSession();
            
                                oos.writeObject("ADDBLOCK");
                                oos.writeObject(session.getAttribute("uname").toString());
                                oos.writeObject(caseid);
				oos.writeObject(cname);
                                oos.writeObject(details);
                                oos.writeObject(lawyer);
                                oos.writeObject("-"); //lawyer comment
                                oos.writeObject(judge);
                                oos.writeObject("-"); //judge comment
                                oos.writeObject(photodbpath); //case file
                                oos.writeObject("PENDING"); //status
                                
                                String reply=(String) oin.readObject();
				
				response.sendRedirect("userhome.jsp?msg=SUCCESS&caseid="+caseid);
				
			}
                
            
            
           
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
            response.sendRedirect("userhome.jsp?msg=FAILED");
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
