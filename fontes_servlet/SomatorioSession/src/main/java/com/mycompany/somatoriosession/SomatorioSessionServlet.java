/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.somatoriosession;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HelenoCampos
 */
@WebServlet(name = "SomatorioSessionServlet", urlPatterns = {"/somatorioSession"})
public class SomatorioSessionServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Integer acessos = (Integer)session.getAttribute("acessos");
            if(acessos != null){
                session.setAttribute("acessos", (acessos+1));
            }else{
                session.setAttribute("acessos", 1);
            }
                
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListagemFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form action='somatorioSession' method='post'>");
            out.println("<input type='text' name='inicio' placeholder='inicio'>");
            out.println("<input type='text' name='fim' placeholder='fim'>");
            
            out.println("<input type='submit'>");
            out.println("</form><br>");
            
            String inicioString = request.getParameter("inicio");
            String fimString = request.getParameter("fim");
            
            if (inicioString != null && fimString != null) {
                Integer inicio = Integer.parseInt(inicioString);
                Integer fim = Integer.parseInt(fimString);
                
                Integer soma = 0;
                for (int i = inicio; i <= fim; i++) {
                    soma+=i;
                }
                out.println("O somatório de " + inicio + " a "+ fim + " é "+ soma + ". <br>");
                out.println("Você usou o serviço "+ (acessos)  + " vezes.");
            }
            
            out.println("</body>");
            out.println("</html>");
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
