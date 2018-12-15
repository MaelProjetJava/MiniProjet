package Controlleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import Modele.PurchaseOrder;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditPurchaseOrders", urlPatterns = {"/EditPurchaseOrders"})
public class EditPurchaseOrders extends HttpServlet {

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
        
        if (action != null) {
            if (action.equals("delete")) {
                if (dao.deletePurchaseOrder(Integer.valueOf(request.getParameter("ordernum"))))
                    request.setAttribute("message", "Bon de commande supprimé avec succès !");
                else
                    request.setAttribute("message", "Erreur lors de la suppression du bon de commande !");
            } else if (action.equals("modify")) {
                PurchaseOrder purchase = dao.getPurchaseOrder(Integer.valueOf(request.getParameter("ordernum")));
                if (purchase != null) {
                    request.setAttribute("purchaseOrder", purchase);
                    request.setAttribute("main_form_action", "Modifier");
                }
            } else if (action.equals("Ajouter")) {
                boolean success = false;
                
                try {
                    PurchaseOrder purchase = new PurchaseOrder(
                            0,
                            user.getCustomer().getId(),
                            Integer.valueOf(request.getParameter("article")),
                            Integer.valueOf(request.getParameter("quantites")),
                            Double.valueOf(request.getParameter("Frais")),
                            Date.valueOf(request.getParameter("dateA")),
                            Date.valueOf(request.getParameter("dateE")),
                            request.getParameter("tel")
                    );
                    
                    success = dao.addPurchaseOrder(purchase);
                } catch (Exception e) {}
                
                if (success)
                    request.setAttribute("message", "Bon de commande ajouté avec succès !");
                else
                    request.setAttribute("message", "Une erreur est survenue, impossible d'ajouter le bon de commande !");
            } else if (action.equals("Modifier")) {
                boolean success = false;
                
                try {
                    PurchaseOrder purchase = new PurchaseOrder(
                            Integer.valueOf(request.getParameter("orderNum")),
                            Integer.valueOf(request.getParameter("customerId")),
                            Integer.valueOf(request.getParameter("article")),
                            Integer.valueOf(request.getParameter("quantites")),
                            Double.valueOf(request.getParameter("Frais")),
                            Date.valueOf(request.getParameter("dateA")),
                            Date.valueOf(request.getParameter("dateE")),
                            request.getParameter("tel")
                    );
                    
                    success = dao.updatePurchaseOrder(purchase);
                } catch (Exception e) {}
                
                if (success)
                    request.setAttribute("message", "Bon de commande modifié avec succès !");
                else
                    request.setAttribute("message", "Une erreur est survenue, impossible de modifier le bon de commande !");
            }
        }
        
        if (request.getAttribute("main_form_action") == null)
            request.setAttribute("main_form_action", "Ajouter");
        
        request.setAttribute("purchaseOrders", dao.getPurchaseOrders(user.getCustomer()));
        request.setAttribute("articles", dao.getAllProducts());
        request.setAttribute("exitURL", request.getContextPath() + "/Exit");
        request.setAttribute("editClientDataURL", request.getContextPath() + "/EditClientData");
        request.getRequestDispatcher("/views/ClientEdit.jsp").forward(request, response);
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
