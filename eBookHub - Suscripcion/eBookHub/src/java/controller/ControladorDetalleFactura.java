/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetalleFactura;
import modelDAO.DetalleFacturaDAO;

public class ControladorDetalleFactura extends HttpServlet {

    String listarDetalleFactura = "view/listarDetalleFactura.jsp";
    String addDetalleFactura = "view/addDetalleFactura.jsp";
    String editDetalleFactura = "view/editarDetalleFactura.jsp";
    
    DetalleFactura nuevaDetalleFactura = new DetalleFactura();
    DetalleFacturaDAO nuevaDetalleFacturaDAO = new DetalleFacturaDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorDetalleFactura</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDetalleFactura at " + request.getContextPath() + "</h1>");
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
        
        String acceso = "";
        String action = request.getParameter("accion");
        
        if(action.equalsIgnoreCase("listarDetalleFactura")){
            acceso = listarDetalleFactura;
        }else if(action.equalsIgnoreCase("addDetalleFactura")){
            acceso = addDetalleFactura;
        }else if(action.equalsIgnoreCase("AgregarDetalleFactura")){
            String idFactura = request.getParameter("txtIdFactura");
            String idServicio = request.getParameter("txtIdServicio");
            nuevaDetalleFactura.setIdFactura(Integer.valueOf(idFactura));
            nuevaDetalleFactura.setIdServicio(Integer.valueOf(idServicio));
            nuevaDetalleFacturaDAO.addDetalleFactura(nuevaDetalleFactura);
            acceso = listarDetalleFactura;
        }else if(action.equalsIgnoreCase("editarDetalleFactura")){
            request.setAttribute("iddf", request.getParameter("idDetallFactura"));
            acceso = editDetalleFactura;
        }
        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
        
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
