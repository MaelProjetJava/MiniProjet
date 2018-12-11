<%-- 
    Document   : clientEdit
    Created on : 28 nov. 2018, 15:17:47
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edition des bon de commandes </title>
    </head>
    <body>
        <h1> Edition des bon de commandes  </h1>
       <form method="POST">
            <label> Nom de l'article : </label>
            
                  <select name="article">
                    <c:forEach items="${articles}" var="article" >
                        <option value="${article.productID}" <c:if test="${article.productID=purchaseOrder.productId}" >selected</c:if>>${article.description} </option>  
                    </c:forEach>
                  </select>
                    
                    <p></p>
                    
                <label> Quantitées :  </label>
                <input name="quantites" value="${purchaseOrder.quantity}" >
                   <p></p>
                   
                <label> Frais de Ports  :  </label>
                <input name="Frais" value="${purchaseOrder.shippingcost}" >
                   <p></p>
             
                <label> Date d'achat  :  </label>
                <input name="dateA" value="${purchaseOrder.salesdate}" >
                   <p></p>
                   
                <label> Date d'envois :  </label>
                <input name="dateE" value="${purchaseOrder.shippingdate}" >
                   <p></p>
                   
                <label> Nom de l'entrepise :   </label>
                <input name="tel" value="${purchaseOrder.freighcompany}" >
                
                
		<input name="action" value="Modifier/ajout" type="SUBMIT">
               
        </form>
                 
                <table border="1">
                    <tr><th>Nom de l'article</th><th>Quantitées</th><th> Frais de Ports</th><th>Date d'achat</th><th>Date d'envois</th><th>Nom de l'entrepise</th><th>Action</th></tr>
				<c:forEach var="pur" items="${purchaseOrders}">
					<tr>
						<td>${pur.name}</td>
						<td>${pur.quantity}</td>
                                                <td>${pur.shippingcost}</td>
                                                <td>${pur.salesdate}</td>
                                                <td>${pur.shippingdate}</td>
                                                <td>${pur.freighcompany}</td>
                                                <input type="hidden" name="ordernum" value="${pur.ordernum}">
						<td><input name="sup" value="Supprimmer" type="SUBMIT"></td>
					</tr>	  		    
				</c:forEach>  
			</table>
                
                
                
                
    </body>
</html>
