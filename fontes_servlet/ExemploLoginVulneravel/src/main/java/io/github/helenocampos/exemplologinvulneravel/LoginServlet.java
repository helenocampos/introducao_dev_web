/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package io.github.helenocampos.exemplologinvulneravel;

import io.github.helenocampos.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Heleno
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    Connection conn = null;

    @Override
    public void init() throws ServletException {
        iniciarConexao();
    }

    private void iniciarConexao() {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/exemplologin", "root", "123");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Usuario usuarioLogado = null;
        RequestDispatcher dispatcherSucesso = request.getRequestDispatcher("/WEB-INF/dashboard.jsp");
        RequestDispatcher dispatcherFalha = request.getRequestDispatcher("login.jsp");
        if(username != null && password != null){
            try{
                String query = "SELECT * FROM usuarios WHERE username = '"+username+"' AND password = '"+password+"'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    usuarioLogado = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            } catch(SQLException ex){
                System.out.println("Problema ao realizar login.");
            }
        }
        
        if(usuarioLogado != null){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(15*60); // 15 minutos
            session.setAttribute("usuario", usuarioLogado);
            dispatcherSucesso.forward(request, response);
        }else{
            request.setAttribute("erro", "Usuário/senha incorretos");
            dispatcherFalha.forward(request, response);
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
