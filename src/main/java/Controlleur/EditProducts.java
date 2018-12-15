package Controlleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import Modele.Product;
import Modele.PurchaseOrder;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "EditProducts", urlPatterns = {"/EditProducts"})
public class EditProducts extends HttpServlet {

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
        
        if(user == null || !user.isAdministrator()) {
            response.sendRedirect(request.getContextPath() + "/Entry");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("delete")) {
                if (dao.deleteProduct(Integer.valueOf(request.getParameter("id"))))
                    request.setAttribute("message", "Produit supprimé avec succès !");
                else
                    request.setAttribute("message", "Erreur lors de la suppression du produit ! Le produit est peut-être utilisé par un bon de commande.");
            } else if (action.equals("modify")) {
                Product product = dao.getProduct(Integer.valueOf(request.getParameter("id")));
                if (product != null) {
                    request.setAttribute("product", product);
                    request.setAttribute("main_form_action", "Modifier");
                }
            } else if (action.equals("Ajouter")) {
                boolean success = false;
                
                try {
                   int quantityOnHand = Integer.valueOf(request.getParameter("quantite"));
                    
                    Product product = new Product(
                            0,
                            Integer.valueOf(request.getParameter("manufacturer")),
                            request.getParameter("productcode"),
                            Double.valueOf(request.getParameter("prix")),
                            quantityOnHand,
                            Double.valueOf(request.getParameter("markup")),
                            quantityOnHand == 0 ? "FALSE" : "TRUE",
                            request.getParameter("description")
                    );
                    
                    success = dao.addProduct(product);
                } catch (Exception e) {}
                
                if (success)
                    request.setAttribute("message", "Produit ajouté avec succès !");
                else
                    request.setAttribute("message", "Une erreur est survenue, impossible d'ajouter le produit !");
            } else if (action.equals("Modifier")) {
                boolean success = false;
                
                try {
                    int quantityOnHand = Integer.valueOf(request.getParameter("quantite"));
                    
                    Product product = new Product(
                            Integer.valueOf(request.getParameter("id")),
                            Integer.valueOf(request.getParameter("manufacturer")),
                            request.getParameter("productcode"),
                            Double.valueOf(request.getParameter("prix")),
                            quantityOnHand,
                            Double.valueOf(request.getParameter("markup")),
                            quantityOnHand == 0 ? "FALSE" : "TRUE",
                            request.getParameter("description")
                    );
                    
                    success = dao.updateProduct(product);
                } catch (Exception e) {}
                
                if (success)
                    request.setAttribute("message", "Produit modifié avec succès !");
                else
                    request.setAttribute("message", "Une erreur est survenue, impossible de modifier le produit !");
            }
        }
        
        if (request.getAttribute("main_form_action") == null)
            request.setAttribute("main_form_action", "Ajouter");
        
        request.setAttribute("manufacturers", dao.getAllManufacturers());
        request.setAttribute("productCodes", dao.getAllProductCodes());
        request.setAttribute("products", dao.getAllProducts());
        request.setAttribute("adminStatURL", request.getContextPath() + "/AdminStat");
        request.setAttribute("exitURL", request.getContextPath() + "/Exit");
        request.getRequestDispatcher("/views/AdminProd.jsp").forward(request, response);
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
