/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modele.MicroMarketRevenue;

/**
 *
 * @author Mael_
 */
@WebServlet(name = "GetMicroMarketsRevenues", urlPatterns = {"/GetMicroMarketsRevenues"})
public class GetMicroMarketsRevenues extends HttpServlet {

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
        
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        
        SimpleDateFormat dateparser = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = new Date(Long.MIN_VALUE);
        if (start != null) {
            try {
                startDate = dateparser.parse(start);
            } catch (ParseException e) {}
        }
        
        Date endDate = new Date(Long.MAX_VALUE);
        if (end != null) {
            try {
                endDate = dateparser.parse(end);
            } catch (ParseException e) {}
        }
        
        IDAO dao = new DAO(DataSourceFactory.getDataSource());
        
        List<MicroMarketRevenue> microMarketsRevenues = dao.getMicroMarketsRevenues(startDate, endDate);
                
        Gson gson = new Gson();
        String gsonData = gson.toJson(microMarketsRevenues);
   
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(gsonData);
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
