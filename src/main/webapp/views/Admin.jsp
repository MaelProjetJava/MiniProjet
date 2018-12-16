<%-- 
    Document   : Admin
    Created on : 28 nov. 2018, 16:22:14
    Author     : pedago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistiques des commandes</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load("current", {packages:["corechart"]});
            google.charts.setOnLoadCallback(showRevenuesCharts);
    
            function showRevenuesCharts() {
                showCustomersRevenues();
                showMicroMarketsRevenues();
                showProductCodesRevenues();
            }

            function drawCustomersRevenuesChart(chartData) {
                var data = google.visualization.arrayToDataTable(chartData);
                
                var options = {
                    title: 'Chiffres d\'affaires par client',
                    is3D: true
                };
                
                var chart = new google.visualization.PieChart(document.getElementById('customersRevenuesChart'));
                
                chart.draw(data, options);
            }
            
            function drawMicroMarketsRevenuesChart(chartData) {
                var data = google.visualization.arrayToDataTable(chartData);
                
                var options = {
                    title: 'Chiffres d\'affaires par zone géographique',
                    is3D: true
                };
                
                var chart = new google.visualization.PieChart(document.getElementById('microMarketsRevenuesChart'));
                
                chart.draw(data, options);
            }
            
            function drawProductCodesRevenuesChart(chartData) {
                var data = google.visualization.arrayToDataTable(chartData);
                
                var options = {
                    title: 'Chiffres d\'affaires par catégorie d\'article',
                    is3D: true
                };
                
                var chart = new google.visualization.PieChart(document.getElementById('productCodesRevenuesChart'));
                
                chart.draw(data, options); 
            }

            function showError(xhr, status, message) {
                $("#errormessage").html("Erreur: " + status + ": " + message);
            }
            
            function startDate() {
                return $("#startdate").val();
            }
            
            function endDate() {
                return $("#enddate").val();
            }
            
            function createUrl(baseUrl) {
                return baseUrl + "?start=" + startDate() + "&end=" + endDate();
            }

            function showProductCodesRevenues() {
                // On fait un appel AJAX pour chercher les codes
                $.ajax({
                    url: createUrl("${getProductCodesRevenuesURL}"),
                    dataType: "json",
                    error: showError,
                    success: // La fonction qui traite les résultats
                        function (result, status, jqXHR) {
                            // On reformate le résultat comme un tableau
                            var article = [];

                            // On met le descriptif des données
                            article.push([ "Catégorie","Chiffre d'affaires"]);
                            
                            for(var i = 0; i < result.length; i++) {
                                article.push([
                                    result[i].productCode,
                                    result[i].revenue
                                ]);
                            }
      
                            drawProductCodesRevenuesChart(article);
                    }
                });
            }
            
            function showMicroMarketsRevenues() {
                // On fait un appel AJAX pour chercher les codes
                $.ajax({
                    url: createUrl("${getMicroMarketsRevenuesURL}"),
                    dataType: "json",
                    error: showError,
                    success: // La fonction qui traite les résultats
                        function (result, status, jqXHR) {
                            // On reformate le résultat comme un tableau

                            var geo = [];

                            // On met le descriptif des données
                            geo.push([ "Zone géographique","Chiffre d'affaires"]);
                            
                            for(var i = 0; i < result.length; i++) {
                                geo.push([
                                    result[i].microMarket,
                                    result[i].revenue
                                ]);
                            }
      
                            drawMicroMarketsRevenuesChart(geo);
                        }
                });
            }
            
            function showCustomersRevenues() {
                // On fait un appel AJAX pour chercher les codes
                $.ajax({
                    url: createUrl("${getCustomersRevenuesURL}"),
                    dataType: "json",
                    error: showError,
                    success: // La fonction qui traite les résultats
                        function (result, status, jqXHR) {
                            // On reformate le résultat comme un tableau

                            var client = [];
                            // On met le descriptif des données
                            client.push([ "Client","Chiffre d'affaires"]);
                            
                            for(var i = 0; i < result.length; i++) {
                                client.push([
                                    result[i].customer,
                                    result[i].revenue
                                ]);
                            }
                            
                            drawCustomersRevenuesChart(client);
                        }
                });
            }
        </script>
    </head>
    <body>
        <h1>Statistiques des commandes</h1>

        <form id="codeForm" onsubmit="event.preventDefault(); showRevenuesCharts();">
            <fieldset>
                <legend>Saisir des dates</legend>
                
                <label for="startdate">Date de début:</label>
                <input type="date" id="startdate" name="startdate"><br/>
                
                <label for="enddate">Date de fin:</label>
                <input type="date" id="enddate" name="enddate" ><br/>
                
                <input type="submit" value="Actualiser">
            </fieldset>
        </form>
        
        <p id="errormessage"></p>

        <div id="productCodesRevenuesChart" style="width: 900px; height: 500px;"></div>
        <div id="microMarketsRevenuesChart" style="width: 900px; height: 500px;"></div>
        <div id="customersRevenuesChart" style="width: 900px; height: 500px;"></div>
        
        <a href="${editProductsURL}">Editer les produits</a>     
        <a href="${exitURL}">Se déconnecter</a>    
    </body>
</html>
