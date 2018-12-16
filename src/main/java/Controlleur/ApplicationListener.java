package Controlleur;

import Modele.DAO;
import Modele.DataSourceFactory;
import Modele.IDAO;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.derby.tools.ij;

/**
 * Web application lifecycle listener, initialise la base de données au démarrage de l'application si nécessaire
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (!databaseExists())
			initializeDatabase();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	private boolean databaseExists() {
		IDAO dao = new DAO(DataSourceFactory.getDataSource());
		return dao.getAllProducts() != null;
	}

	private void initializeDatabase() {
                Logger.getLogger("MiniProjet").log(Level.INFO, "Creating embedded database...");
		try {
			Connection connection = DataSourceFactory.getDataSource().getConnection();
			int result = ij.runScript(
                                connection,
                                this.getClass().getResourceAsStream("export.sql"),
                                "UTF-8",
                                System.out,
                                "UTF-8"
                        );
			
                        if (result == 0)
				Logger.getLogger("MiniProjet").log(Level.INFO, "Database succesfully created");
			else
				Logger.getLogger("MiniProjet").log(Level.SEVERE, "Errors creating database");
		} catch (UnsupportedEncodingException|SQLException e) {
                        Logger.getLogger("MiniProjet").log(Level.SEVERE, "Errors creating database");
			Logger.getLogger("MiniProjet").log(Level.SEVERE, null, e);
		}

	}
}
