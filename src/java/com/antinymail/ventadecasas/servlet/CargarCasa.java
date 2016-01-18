/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antinymail.ventadecasas.servlet;

import com.antinymail.ventadecasas.ejb.CasaFacade;
import com.antinymail.ventadecasas.entitys.Casa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.codegen.Compiler.LOG;

/**
 *
 * @author Susana
 */
@WebServlet(name = "CargarCasa", urlPatterns = {"/CargarCasa"})
public class CargarCasa extends HttpServlet {
    @EJB
    private CasaFacade casaFacade;

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
        
         LOG.log(Level.INFO, "id:{0}", "id");
        
        //Recogemos el id pasado por casa_reg_respuesta.jsp
//        String $id=request.getParameter("id");
//        long id=Long.parseLong($id);
        
        
         String $id=request.getParameter("id");
        long id=Long.parseLong($id);
        
        //Creamos una instancia de Casa y llamamos al EJB SessionBean invocando al método find() y le pasamos el id de casa recogido en $id para posteriormente redirigir
        //hacia casa_form.jsp y mostrar allí todos los atributos del objeto casa recogidos con el método find() de casaFacade.
        Casa casa = casaFacade.find(id);
        request.setAttribute("casa", casa);
        RequestDispatcher rd = request.getRequestDispatcher("/casa_form.jsp");
        rd.forward(request, response);
        
        
        
      
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
