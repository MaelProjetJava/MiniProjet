<%-- 
    Document   : AdminProd
    Created on : 13 déc. 2018, 15:37:50
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <body>
        <h1> Edition des bon de commandes  </h1>
         <div id="box">
       <form method="POST">
           
                   
          
                
            <div class="prix"> 
                
                <label for="prix "> <span>Prix  :  </span> </label>
                <input id="prix" name="Frais" value="${pro.purchaseCost}"  style="width:200px;">
                
            </div>
                
            <div class =" quantites">
                
                <label for = quantites> <span> Quantitées : </span> </label>
                <input id="quantites" name="quantites" value="${pro.quantityOnHand}" style="width:200px;" >
                   
           </div>    
                
            <div class="markup">
                <label for="markup"> <span> Markup  : </span>  </label>
                <input  id="markup" name="dateA" value="${pro.markup}" style="width:200px;" >
            </div>
            
            <div class="dispo" >
                <label for="dispo"> <span> Disponible: </span> </label>
                <input id="dispo" name="dateE" value="${pro.available}" style="width:200px;" >
            </div>
            
            <div class="des" >
                <label for="des"> <span> Description </span> </label>
                <input id="des" name="dateE" value="${pro.description}" style="width:200px;" >
            </div>
           
           
		<input name="action" value="Modifier/ajout" type="SUBMIT">
               
        </form>
         </div>
                 
                
                <div id=" result">
                <table border="1">
                    <tr><th>Prix</th><th>Quantitées</th><th>Markup</th><th>Disponible</th><th>Description</th></tr>
				<c:forEach var="pro" items="${purchaseOrders}">
					<tr>
						<td>${pro.purchaseCost}</td>
						<td>${pro.quantityOnHand}</td>
                                                <td>${pro.markup}</td>
                                                <td>${pro.available}</td>
                                                <td>${pro.description}</td>
						<td><input name="sup" value="Supprimmer" type="SUBMIT"></td>
					</tr>	  		    
				</c:forEach>  
			</table>
                
                </div>
                
    <a href="${exitURL}">Se déconnecter</a>            
    </body>
</html>

