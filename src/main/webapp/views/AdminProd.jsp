<%-- 
    Document   : AdminProd
    Created on : 13 déc. 2018, 15:37:50
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/ClientEdit.css">
        <title>Edition des produits</title>
    </head>
    <body>
        <h1>Edition des produits</h1>
        <div id="box">
        <form method="POST">  
            <div class="des" >
                <label for="des"><span>Description</span></label>
                <input id="des" name="description" value="${product.description}" style="width:200px;" >
            </div>
            
            <div class="manufacturer">
                <label for="manufacturer"><span>Fabricant:</span></label>
                <select id="manufacturer" name="manufacturer">
                    <c:forEach var="manufacturer" items="${manufacturers}">
                        <option value="${manufacturer.id}" <c:if test="${manufacturer.id == product.manufacturerId}" >selected</c:if>>${manufacturer.name} </option>  
                    </c:forEach>
                </select>
            </div>
            
            <div class="productcode">
                <label for="productcode"><span>Code produit:</span></label>
                <select id="productcode" name="productcode">
                    <c:forEach var="productcode" items="${productCodes}">
                        <option value="${productcode.code}" <c:if test="${productcode.code == product.productCode}" >selected</c:if>>${productcode.description}</option>  
                    </c:forEach>
                </select>
            </div>
            
            <div class="prix"> 
                <label for="prix "><span>Prix:</span></label>
                <input id="prix" name="prix" value="${product.purchaseCost}"  style="width:200px;">
            </div>
                
            <div class =" quantites">
                <label for = quantites><span>Quantité:</span></label>
                <input id="quantite" name="quantite" value="${product.quantityOnHand}" style="width:200px;" >
            </div>    
                
            <div class="markup">
                <label for="markup"><span>Marge:</span> </label>
                <input  id="markup" name="markup" value="${product.markup}" style="width:200px;" >
            </div>
           
            <c:if test="${main_form_action eq 'Modifier'}">
                <input type="hidden" name="id" value="${product.id}">
            </c:if>
            <input name="action" value="${main_form_action}" type="SUBMIT">  
        </form>   
        </div>
                 
                
        <div id="result">
        <table border="1">
            <tr>
                <th>Description</th>
                <th>Fabricant</th>
                <th>Code produit</th>
                <th>Prix</th>
                <th>Quantité</th>
                <th>Marge</th>
                <th>Disponible</th>
            </tr>
            <c:forEach var="pro" items="${products}">
                <tr>
                    <td>${pro.description}</td>
                    <c:forEach var="manufacturer" items="${manufacturers}">
                        <c:if test="${manufacturer.id == pro.manufacturerId}">
                            <td>${manufacturer.name}</td>
                        </c:if>
                    </c:forEach>
                    <td>${pro.productCode}</td>
                    <td>${pro.purchaseCost}</td>
                    <td>${pro.quantityOnHand}</td>
                    <td>${pro.markup}</td>
                    <td>
                        <c:choose>
                            <c:when test="${pro.available}">oui</c:when>
                            <c:otherwise>non</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form method="POST">
                            <input type="hidden" name="id" value="${pro.id}">
                            <input type="hidden" name="action" value="modify">
                            <input value="Modifier" type="submit">
                        </form>
                    </td>
                    <td>
                        <form method="POST">
                            <input type="hidden" name="id" value="${pro.id}">
                            <input type="hidden" name="action" value="delete">
                            <input value="Supprimer" type="submit">
                        </form>
                    </td>
		</tr>	  		    
            </c:forEach>  
	</table>
        </div>
            
        <p>${message}</p>

        <a href="${adminStatURL}">Voir les statistiques</a>     
        <a href="${exitURL}">Se déconnecter</a>            
    </body>
</html>

