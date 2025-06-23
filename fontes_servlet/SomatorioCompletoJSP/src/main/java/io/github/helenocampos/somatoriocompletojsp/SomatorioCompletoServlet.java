/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package io.github.helenocampos.somatoriocompletojsp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Heleno
 */
@WebServlet(name = "SomatorioCompletoServlet", urlPatterns = {"/somatoriocompleto"})
public class SomatorioCompletoServlet extends HttpServlet {

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
        Cookie[] cookies = request.getCookies();
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        RequestDispatcher dispatcherErro = getServletContext().getRequestDispatcher("/erro.html");
        RequestDispatcher dispatcherJSP = request.getRequestDispatcher("/somatorio.jsp");

        String inicioString = request.getParameter("inicio");
        String fimString = request.getParameter("fim");

        if (inicioString != null && fimString != null) {
            if (inicioString.equals("") && fimString.equals("")) {
                dispatcherErro.forward(request, response);
            } else {
                Integer acessosContext = (Integer) context.getAttribute("acessos");
                if (acessosContext == null) {
                    acessosContext = 1;
                }
                context.setAttribute("acessos", acessosContext + 1);
                Integer acessosCookies = 1;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("acessos")) {
                            acessosCookies = Integer.valueOf(cookie.getValue());
                        }
                    }
                }

                Integer acessosSession = (Integer) session.getAttribute("acessos");
                if (acessosSession != null) {
                    acessosSession += 1;
                    session.setAttribute("acessos", (acessosSession));
                } else {
                    session.setAttribute("acessos", 1);
                    acessosSession = 1;
                }

                Cookie cookie = new Cookie("acessos", Integer.valueOf(acessosCookies + 1).toString());
                response.addCookie(cookie);
                Integer inicio = Integer.parseInt(inicioString);
                Integer fim = Integer.parseInt(fimString);

                Integer soma = 0;
                for (int i = inicio; i <= fim; i++) {
                    soma += i;
                }
                request.setAttribute("acessosSession", acessosSession);
                request.setAttribute("acessosCookies", acessosCookies);
                request.setAttribute("acessosContext", acessosContext);
                request.setAttribute("soma", soma);
                request.setAttribute("inicio", inicio);
                request.setAttribute("fim", fim);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/somatorio.jsp");
        dispatcher.forward(request, response);

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
