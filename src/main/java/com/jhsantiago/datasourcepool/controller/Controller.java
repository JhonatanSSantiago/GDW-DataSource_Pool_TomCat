/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhsantiago.datasourcepool.controller;
import com.jhsantiago.datasourcepool.dao.ErroDAOException;
import com.jhsantiago.datasourcepool.dao.PessoaDaoClasseBanco;
import com.jhsantiago.datasourcepool.dao.PessoaDaoInterface;
import com.jhsantiago.datasourcepool.model.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhons
 */
public class Controller extends HttpServlet {

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
        response.setContentType("text/xml;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            List<Pessoa> pessoas = null;
            PessoaDaoInterface dao = null;
            try {
                dao = new PessoaDaoClasseBanco();
                pessoas = dao.pegaPessoas();
                out.print("<pessoas>");
                for (int i = 0; i < pessoas.size(); i++) {
                    Pessoa p1 = pessoas.get(i);
                    out.println(p1);
                }
                out.print("</pessoas>");
            } catch (ErroDAOException ex) {
                out.print("<erro>Erro ao tentar ler os dados</erro>");
            } finally {
                try {
                    dao.sair();
                } catch (ErroDAOException ex) {
                    out.print("<erro>Erro ao tentar fechar</erro>");
                }
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
