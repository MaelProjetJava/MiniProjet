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
    <title>Pour l'admin</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
   
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart(chartData1,chartData2,chartData3) {
        var data1 = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['hzguzhjhzio',     110],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
          var options1 = {
          title: 'chiffres daffaire/catégorie d article',
          is3D: true,
        };
        
        var data2 = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['hello',     10],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
        
         var data3 = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['hello',     1],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);
    
        var options2 = {
          title: 'chiffres daffaire/zone géographique',
          is3D: true,
        };

        var options3 = {
          title: 'chiffres daffaire/client',
          is3D: true,
        };


        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d1'));
        var chart2 = new google.visualization.PieChart(document.getElementById('piechart_3d2'));
        var chart3 = new google.visualization.PieChart(document.getElementById('piechart_3d3'));
        
        chart.draw(data1, options1);
        chart2.draw(data2, options2);
        chart3.draw(data3, options3);
      }
                 $(document).ready(// Exécuté à la fin du chargement de la page
                    function () {
                        // On montre la liste des codes
                        showCodes();
                    }
            );

            function showCodes() {
                // On fait un appel AJAX pour chercher les codes
                $.ajax({
                    url: "allCodes",//a modifier ,om de servlet
                    dataType: "json",
                    success: // La fonction qui traite les résultats
                    function (result) {
			// On reformate le résultat comme un tableau
			var article = [];
                        var geo = [];
                        var client = [];
			// On met le descriptif des données
			article.push(["Client", "Ventes"]);
			for(var client in result.records) {
                            article.push([client, result.records[client]]);
						}
			// On dessine le graphique
                        
                        
                        
                        
                        
			drawChart(article,geo,client);
			},
                    error: showError
                });
            }
            
    </script>
    
  </head>
  <body>
      <h1>Statistiques des commandes par date  </h1>
      
       <form id="codeForm" onsubmit="event.preventDefault(); showCodes();">
            <fieldset><legend>Saisir des dates </legend>
            date de début  : <input type="date" id="dated" name="dated"><br/>
            date de fin  : <input type="date" id="datef" name="datef" ><br/>
            <input type="submit" value="Modifier">
            </fieldset>
        </form>
      
    <div id="piechart_3d1" style="width: 900px; height: 500px;"></div>
    <div id="piechart_3d2" style="width: 900px; height: 500px;"></div>
    <div id="piechart_3d3" style="width: 900px; height: 500px;"></div>
  </body>
</html>
