/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.agendaeletronica;

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
@WebServlet(name = "TesteDBServlet", urlPatterns = {"/agenda"})
public class AgendaEletronicaServlet extends HttpServlet {

    Connection conn = null;

    @Override
    public void init() throws ServletException {
        iniciarConexao();
    }

    private void iniciarConexao() {
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/agenda", "usuario", "12345");
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
        String acao = request.getParameter("acao");
        if (acao != null) {
            if (acao.equals("inserir")) {
                incluirContato(request);
            }
            if (acao.equals("apagar")) {
                apagarContato(request);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style> table, td, th { border: 1px solid black; border-collapse: collapse; padding: 10px 10px 10px 10px; }</style>");
            out.println("<title>Servlet TesteDBServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='' method='post'>");
            out.println("<input type='hidden' name='acao' value='inserir'>");
            out.println("<input type='text' name='nome' placeholder='nome'>");
            out.println("<input type='text' name='endereco' placeholder='endereco'>");
            out.println("<input type='text' name='telefone' placeholder='telefone'>");
            out.println("<input type='submit'>");
            out.println("</form>");

            try (PreparedStatement sql = conn.prepareStatement("select * from contato")) {
                ResultSet resultado = sql.executeQuery();
                out.println("<br><table style='border:1px solid'>");
                out.println("<tr><th>Nome</th> <th>Endereço</th> <th>Telefone</th><th>Opções</th></tr>");
                while (resultado.next()) {
                    out.println("<tr>");
                    out.println("<td style='border:1px solid'>" + resultado.getString("nome") + "</td>");
                    out.print("<td>" + resultado.getString("endereco") + "</td>");
                    out.print("<td>" + resultado.getString("telefone") + "</td>");
                    out.print("<td><a href=agenda?acao=apagar&id=" + resultado.getString("id") + ">Apagar </a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (SQLException ex) {
                Logger.getLogger(AgendaEletronicaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("</body>");
            out.println("</html>");

        }
    }

    private void apagarContato(HttpServletRequest request) {
        String idString = request.getParameter("id");
        if (idString != null) {
            try (PreparedStatement sql = conn.prepareStatement("delete from contato where id = ?")) {
                conn.setAutoCommit(false);
                sql.setInt(1, Integer.parseInt(idString));
                sql.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                System.out.println("Problema tentar apagar o contato de id: " + idString);
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    System.out.println("Problema ao tentar dar rollback.");
                }
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.out.println("Problema ao ativar o auto commit.");
                }
            }
        }
    }

    private void incluirContato(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        if (nome != null && endereco != null && telefone != null) {
            try (PreparedStatement sql = conn.prepareStatement("insert into contato (nome, endereco, telefone) values (?,?,?)")) {
                sql.setString(1, nome);
                sql.setString(2, endereco);
                sql.setString(3, telefone);
                sql.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Erro ao inserir contato de nome: " + nome);
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
