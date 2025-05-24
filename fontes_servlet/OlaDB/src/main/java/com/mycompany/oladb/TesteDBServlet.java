/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.oladb;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heleno
 */
@WebServlet(name = "TesteDBServlet", urlPatterns = {"/oladb"})
public class TesteDBServlet extends HttpServlet {

    Connection conn = null;

    @Override
    public void init() throws ServletException {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/alomundodb", "usuario", "123");
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco de dados.");
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível fechar a conexão com o banco de dados.");
        }
    }

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
        incluirContato(request);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TesteDBServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='oladb' method='post'>");
            out.println("<input type='text' name='nome' placeholder='nome'>");
            out.println("<input type='text' name='idade' placeholder='idade'>");
            out.println("<input type='submit'>");
            out.println("</form>");

            try (PreparedStatement sql = conn.prepareStatement("select * from contato")) {
                ResultSet resultado = sql.executeQuery();
                while (resultado.next()) {
                    out.print("<p>" + resultado.getString("nome"));
                    out.println(", " + resultado.getInt("idade") + "</p>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TesteDBServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("</body>");
            out.println("</html>");

        }
    }

    private void incluirContato(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String idade = request.getParameter("idade");
        if (nome != null && idade != null) {
            try (PreparedStatement sql = conn.prepareStatement("insert into contato values (?,?)")) {
                sql.setString(1, nome);
                sql.setInt(2, Integer.parseInt(idade));
                sql.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TesteDBServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
