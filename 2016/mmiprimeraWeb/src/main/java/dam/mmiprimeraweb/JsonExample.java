/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.mmiprimeraweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ejemplo;

/**
 *
 * @author oscar
 */
@WebServlet(name = "JsonExample", urlPatterns = {"/JsonExample"})
public class JsonExample extends HttpServlet {

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
        
        
        ArrayList<Ejemplo> ejemplos = new ArrayList();
        
        Ejemplo ej = new Ejemplo();
        ej.setNombre("kk");
        ej.setId(1);
        ej.setPeso("33");
        ej.addInquietud("lll");
        ej.addInquietud("lll222");
        ej.addInquietud("lllasdasdasd");
        
        ejemplos.add(ej);
        ejemplos.add(new Ejemplo(2,"ee","67"));
        
        
        ObjectMapper mapper = new ObjectMapper();

        // equivalente a las lineas de abajo. 
        //mapper.writeValue(response.getOutputStream(), ej);

        String ejemplo = mapper.writeValueAsString(ejemplos);
        PrintWriter out = response.getWriter();
        out.println(ejemplo);

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
