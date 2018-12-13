package Controlleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import Modele.ProductCodeRevenue;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "GetProductCodesRevenues", urlPatterns = {"/GetProductCodesRevenues"})
public class GetProductCodesRevenues extends HttpServlet {

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

        Date startDate = new Date(1);
        if (start != null) {
            try {
                startDate = Date.valueOf(start);
            } catch (IllegalArgumentException e) {}
        }
        
        Date endDate = new Date(8098, 12, 31);
        if (end != null) {
            try {
                endDate = Date.valueOf(end);
            } catch (IllegalArgumentException e) {}
        }
        
        IDAO dao = new DAO(DataSourceFactory.getDataSource());
        
        List<ProductCodeRevenue> productCodesRevenues = dao.getProductCodesRevenues(startDate, endDate);
                
        Gson gson = new Gson();
        String gsonData = gson.toJson(productCodesRevenues);
   
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
