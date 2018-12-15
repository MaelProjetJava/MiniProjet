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
            <div class="prix"> 
                <label for="prix "> <span>Prix  :  </span> </label>
                <input id="prix" name="Frais" value="${product.purchaseCost}"  style="width:200px;">
            </div>
                
            <div class =" quantites">
                <label for = quantites> <span> Quantitées : </span> </label>
                <input id="quantites" name="quantites" value="${product.quantityOnHand}" style="width:200px;" >
           </div>    
                
            <div class="markup">
                <label for="markup"> <span> Markup  : </span>  </label>
                <input  id="markup" name="dateA" value="${product.markup}" style="width:200px;" >
            </div>
            
            <div class="dispo" >
                <label for="dispo"> <span> Disponible: </span> </label>
                <input id="dispo" name="dateE" value="${product.available}" style="width:200px;" >
            </div>
            
            <div class="des" >
                <label for="des"> <span> Description </span> </label>
                <input id="des" name="dateE" value="${product.description}" style="width:200px;" >
            </div>
           
            <input name="action" value="Modifier/ajout" type="SUBMIT">  
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
                    <td>${pro.available}</td>
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

