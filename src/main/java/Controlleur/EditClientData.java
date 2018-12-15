package Controlleur;

import Modele.Customer;
import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "EditClientData", urlPatterns = {"/EditClientData"})
public class EditClientData extends HttpServlet {

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
        
        IDAO dao = new DAO(DataSourceFactory.getDataSource());
        HttpSession userSession = request.getSession(true);
        User user = (User) userSession.getAttribute("user");
        
        if(user == null || user.isAdministrator()) {
            response.sendRedirect(request.getContextPath() + "/Entry");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("Modifier")) {
            boolean success = false;
            Customer customer = null;
            try {
                customer = (Customer) user.getCustomer().clone();
                customer.setName(request.getParameter("nom"));
                customer.setAddr1(request.getParameter("adresse1"));
                customer.setAddr2(request.getParameter("adresse2"));
                customer.setZip(request.getParameter("zip"));
                customer.setCity(request.getParameter("ville"));
                customer.setState(request.getParameter("etat"));
                customer.setPhone(request.getParameter("tel"));
                customer.setFax(request.getParameter("fax"));
                customer.setEmail(request.getParameter("email"));
                
                success = dao.updateCustomer(customer);
            } catch (Exception e) {}
            
            if (success) {
                user.setCustomer(customer);
                request.setAttribute("message", "Données modifiées avec succès !");
            } else {
                request.setAttribute("message", "Une erreur est survenue, impossible de modier les données !");
            }
        }
        
        request.setAttribute("user", user.getCustomer());
        request.setAttribute("micromarkets", dao.getAllMicroMarkets());
        request.setAttribute("exitURL", request.getContextPath() + "/Exit");
        request.setAttribute("editPurchaseOrdersURL", request.getContextPath() + "/EditPurchaseOrders");
        request.getRequestDispatcher("/views/Client.jsp").forward(request, response);
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
