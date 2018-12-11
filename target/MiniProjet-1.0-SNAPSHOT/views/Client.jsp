<%-- 
    Document   : client
    Created on : 27 nov. 2018, 16:18:51
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Client.css">
        <title>Edition des données utilisateurs </title>
    </head>
    <body>
        <h1>Edition des données utilisateurs </h1>
         <div id="box">
        <form method="POST">
            <div class=" nom">
                
            <label for ="nom" ><span> Nom : </span> </label>
            <input id ="nom" name="nom" value="${user.name}" style="width:200px;">
            
            </div>
            
            <div class="adresse1">
                
                 <label for ="adresse1" ><span> Adresse N°1 : </span> </label>
                <input id ="adresse1" name="adresse1" value="${user.addr1}" style="width:200px;" >
                
            </div>
                    
            
            <div class="adresse2">
                
                <label for ="adresse2" ><span> Adresse N°2 : </span> </label>
                <input id ="adresse2" name="adresse2" value="${user.addr2}" style="width:200px;" >
                
            </div>
            
            <div class="zip">
                
                <label for ="zip" ><span> Zip : </span> </label>
                
                <select id ="zip" name="zip" style="margin:auto;">
                    <c:forEach items="${micromarkets}" var="micromarket" >
                        <option value="${micromarker.zipCode}" <c:if test="${zip.zipCode=user.zip}" >selected</c:if>>${zip.zipCode}</option>  
                    </c:forEach>
                </select>
                
            </div>
            
            <div class="ville">
                
                <label for ="ville" ><span> Ville : </span> </label>
                <input id ="ville" name="ville" value="${user.city}" style="width:200px;" >
                
            </div>
            
            <div class="etat">
                
                <label for ="etat" ><span> Etat : </span> </label>
                <input id ="etat" name="etat" value="${user.state}" style="width:20px; margin:auto;">
                
            </div>
            
            <div class=" tel">
                
                <label for ="tel" ><span> N°de téléphone : </span> </label>
                <input id ="tel" name="tel" value="${user.phone}"style="width:200px;" >
                
            </div>
            
            <div class="fax">
                
                <label for ="fax" ><span> Fax : </span> </label>
                <input id ="fax" name="fax" value="${user.fax}"style="width:200px;" >
                
            </div>
            
            <div class="email">
                
                <label for ="email" ><span> Email : </span> </label>
                <input id ="email" name="email" value="${user.email}" type="email" style="width:200px;">
                
            </div>
                
                
		<input id="action" name="action" value="Modifier" type="SUBMIT">
               
        </form>
                
        <p>${message}</p>
         
        <a href="${editPurchaseOrdersURL}">Editer ses bons de commandes</a>
        <a href="${exitURL}">Se déconnecter</a>
        </div>
    </body>
</html>
