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
        <link rel="stylesheet" href="css/ClientEdit.css">
        <title>Edition des bons de commandes </title>
    </head>
    <body>
        <h1> Edition des bons de commandes  </h1>
        <div id="box">
        <form method="POST">
            <div class="article">
                <label for ="article "> <span> Nom de l'article :</span> </label>
                <select id ="article" name="article">
                    <c:forEach items="${articles}" var="article" >
                        <option value="${article.id}" <c:if test="${article.id == purchaseOrder.productId}" >selected</c:if>>${article.description} </option>  
                    </c:forEach>
                </select>
           </div>
                   
           <div class =" quantites">
                <label for = quantites> <span> Quantité : </span> </label>
                <input id="quantites" name="quantites" value="${purchaseOrder.quantity}" style="width:200px;" > 
           </div>     
                
            <div class="Frais"> 
                <label for="Frais "> <span> Frais de Ports  :  </span> </label>
                <input id="Frais" name="Frais" value="${purchaseOrder.shippingCost}"  style="width:200px;">
            </div>
                
            <div class="dateA">
                <label for="dateA"> <span> Date d'achat  : </span>  </label>
                <input  id="dateA" name="dateA" value="${purchaseOrder.salesDate}" style="width:200px;" >
            </div>
            
            <div class="dateE" >
                <label for="dateE"> <span> Date d'envois : </span> </label>
                <input id="dateE" name="dateE" value="${purchaseOrder.shippingDate}" style="width:200px;" >
            </div>
           
            <div class="tel">
                <label for="tel"> <span>Transporteur : </span>  </label>
                <input id="tel" name="tel" value="${purchaseOrder.freightCompany}" style="width:200px;" >
            </div> 
            
            <c:if test="${main_form_action eq 'Modifier'}">
                <input type="hidden" name="orderNum" value="${purchaseOrder.orderNum}">
                <input type="hidden" name="customerId" value="${purchaseOrder.customerId}">
            </c:if>
            <input name="action" value="${main_form_action}" type="SUBMIT"> 
        </form>  
        </div>
            
        <div id=" result">
        <table border="1">
            <tr>
                <th>Nom de l'article</th>
                <th>Quantitées</th>
                <th>Frais de Ports</th>
                <th>Date d'achat</th>
                <th>Date d'envois</th>
                <th>Transporteur</th>
            </tr>
                
            <c:forEach var="pur" items="${purchaseOrders}">
		<tr>
                    <td>${pur.productName}</td>
                    <td>${pur.quantity}</td>
                    <td>${pur.shippingCost}</td>
                    <td>${pur.salesDate}</td>
                    <td>${pur.shippingDate}</td>
                    <td>${pur.freightCompany}</td>
                    <td>
                        <form method="POST">
                            <input type="hidden" name="ordernum" value="${pur.orderNum}">
                            <input type="hidden" name="action" value="modify">
                            <input value="Modifier" type="submit">
                        </form>
                    </td>
                    <td>
                        <form method="POST">
                            <input type="hidden" name="ordernum" value="${pur.orderNum}">
                            <input type="hidden" name="action" value="delete">
                            <input value="Supprimer" type="submit">
                        </form>
                    </td>
		</tr>	  		    
            </c:forEach>  
        </table>     
        </div>
        
        <p>${message}</p>

        <a href="${editClientDataURL}">Editer ses données utilisateur</a>  
        <a href="${exitURL}">Se déconnecter</a>            
    </body>
</html>
