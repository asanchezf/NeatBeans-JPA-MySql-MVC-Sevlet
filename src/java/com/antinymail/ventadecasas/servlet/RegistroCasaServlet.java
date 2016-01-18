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
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Susana
 */

@MultipartConfig
@WebServlet(name = "RegistroCasaServlet", urlPatterns = {"/RegistroCasaServlet"})
public class RegistroCasaServlet extends HttpServlet {
    @EJB
    private CasaFacade casaFacade;
    @PersistenceContext(unitName = "VentadeCasasPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

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
        
        
        //Recogemos la información que nos llega desde el formulario de alta
        
        String $id = request.getParameter("id");
        
        String nombre=request.getParameter("nombre");
        String ubicacion=request.getParameter("ubicacion");
        String descripcion=request.getParameter("descripcion");
        String precio=request.getParameter("precio");
        
        LOG.log(Level.INFO, "Nombre:{0}", nombre);
        LOG.log(Level.INFO, "Ubicacion:{0}", ubicacion);
        LOG.log(Level.INFO, "Descripci\u00f3n:{0}", descripcion);
        LOG.log(Level.INFO, "Precio:{0}", precio);
        
        
        //Creamos instancia de Casa
        
         boolean esNuevo = ($id == null || $id.isEmpty()); //si no tiene datos, es nuevo
            //si es nuevo, es new, sino, lo busca en la base de datos
         Casa casa = esNuevo ? new Casa() : casaFacade.find(Long.valueOf($id));
        
        
        //enviamos los valores
         //casa.setId($id);
         
        casa.setNombre(nombre);
        casa.setDescripcion(descripcion);
        casa.setUbicacion(ubicacion);
        casa.setPrecio(precio);
        
        //Con botón dcho. Insert Code llamamos al Call Enterprise Bean==> @EJB private CasaFacade casaFacade; Nos crea automáticamente la llamada con la variable casaFacade.
        //Ahora podemos llamar al método create pasándole el objeto casa que contiene todos los atributos para poder crear un nuevo registro en la BB.DD.
        //casaFacade.create(casa);
        
        
//         if (fotoSize>0)
//                alumno.setFoto(foto);
            
            if (esNuevo) { //si es nuevo...
                casaFacade.create(casa); //... lo crea
            } else {//... sino...
                casaFacade.edit(casa); //.. lo actualiza
            }
        
        LOG.log(Level.INFO, "Casa creada:", casa);
        
        RequestDispatcher redirigir = request.getRequestDispatcher("/casa_reg_respuesta.jsp");
        request.setAttribute("Casa", casa);
        redirigir.forward(request, response);
        
     
    }
    private static final Logger LOG = Logger.getLogger(RegistroCasaServlet.class.getName());

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

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
