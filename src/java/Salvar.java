/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josemir
 */
@WebServlet(urlPatterns = {"/Salvar"})
public class Salvar extends HttpServlet {

    
    
    private Banco Con = new Banco();
    private Connection liga;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    
    
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
Date hora = (Date) Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
String dataFormatada = sdf.format(hora);

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        try {
            this.liga = Con.Conectar();
            
  
        
        String sql = "INSERT INTO MENSAGEMS (`idMENSAGENS`, `mensagem`, `hora`, `usuario`) VALUES (NULL, '"+request.getParameter("msg")+"', '"+dataFormatada+"','"+request.getParameter("mensagem")+"');";
  
        ps = liga.prepareStatement(sql);
  
        ps.execute();
        
        response.sendRedirect("chat.html");
        } 
        catch (SQLException ex) {
            Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //catch (ClassNotFoundException ex) {
           // Logger.getLogger(Salvar.class.getName()).log(Level.SEVERE, null, ex);
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
