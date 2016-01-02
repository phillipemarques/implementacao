/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.web.repositories.controller;

import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.wrappers.MyExperimentToOntology;
import br.ufjf.wrappers.OntologyToMyExperiment;
import generated.Ontology;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

/**
 *
 * @author phillipe
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }
        
    
    private void doSearch(HttpServletRequest request,
        HttpServletResponse response) throws IOException, ServletException, JAXBException, MyExperimentException {

        String searchValue = request.getParameter("search");
        String workflowCkeck = request.getParameter("workflow");
        String fileCkeck = request.getParameter("file");
        String packCkeck = request.getParameter("pack");        
        
        String xmlSearchResult = null;
        
        //mapping ontology to myExperiment format
        String queryTime = OntologyToMyExperiment.mapToMyExperimentQuery(workflowCkeck,fileCkeck,packCkeck);
        Search search = OntologyToMyExperiment.searchMyExperiment(searchValue,queryTime);
        
        //mapping myExperiment result to ontology format
        try {
            Ontology ontology = new Ontology();
            MyExperimentToOntology.createOntology(search,ontology);            
            xmlSearchResult = MyExperimentToOntology.writeOntology(ontology);
        } catch (JAXBException | IOException e) {
            System.out.println("Exception");
        }
        
        request.setAttribute("search",xmlSearchResult);
        
        RequestDispatcher disp;
        disp = request.getRequestDispatcher("/public/index.jsp");
        disp.forward(request, response);
    }
    
    
    
    
//    private void doSearch(HttpServletRequest request,
//            HttpServletResponse response) throws IOException {
//
//        String searchValue = request.getParameter("search");
//        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            //out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
//            out.println("<h2>Search query:" + searchValue + "</h2>");            
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

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
            throws ServletException, IOException, JAXBException, MyExperimentException {
        
        String action = request.getParameter("action");

        if (action == null) {
            throw new ServletException("No action specified.");
        } else if (action.equals("search")) {
            doSearch(request, response);
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MyExperimentException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MyExperimentException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
