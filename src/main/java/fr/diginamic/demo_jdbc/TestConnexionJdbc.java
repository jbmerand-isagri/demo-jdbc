package fr.diginamic.demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fr.diginamic.demo_jdbc.exceptions.TechnicalException;

/**
 *
 */
public class TestConnexionJdbc {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		String userName = monFichierConf.getString("database.user");
		String pwd = monFichierConf.getString("database.password");
		String url = monFichierConf.getString("database.url");

		// chargement du driver
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new TechnicalException("Driver JDBC " + driverName + "non trouvé.", e);
		}

		// connexion à la bdd
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, userName, pwd);
			System.out.println(connexion.getClass().getName());
		} catch (SQLException e) {
			throw new TechnicalException("La connexion à la bdd a echoué.", e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new TechnicalException("Fermeture de connexion à la bdd echouée.", e);
			}
		}

	}

}
