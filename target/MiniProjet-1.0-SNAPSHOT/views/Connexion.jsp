

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/connexion.css">
        <title>MiniProjetJava</title>
    </head>
    <body>
        <h1>Mini Projet Java </h1>
        <div id="box">
        <h2>Identifiez vous</h2>
        
        <form method="POST">
            <div class=" login">
                <label for ="login" ><span> Login :</span> </label>
                
                <input id = "login" name="login" style="width: 200px;">
            </div>
               <div class="mdp">
                <label for ="mdp" > Password :</label>
                <input id = "mdp" name="mdp" type="password"style="width: 200px;" >
               
               </div>
		<input id = "action" name="action" value="Connexion" type="SUBMIT">
	</form>
    
        <p> ${erreurAut}</p> 
        </div>
    </body>
</html>
