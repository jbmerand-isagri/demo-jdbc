package fr.diginamic.demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.diginamic.demo_jdbc.dao.Statements;
import fr.diginamic.demo_jdbc.exceptions.TechnicalException;

/**
 * Classe principale de lancement du test
 */
public class TestConnexionJdbc {

	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(TestConnexionJdbc.class);

	/**
	 * Méthode principale de lancement du test
	 * 
	 * @param args
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
			LOGGER.error("Echec du chargement du driver", e);
			throw new TechnicalException("Driver JDBC " + driverName + "non trouvé.", e);
		}

		// connexion à la bdd
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, userName, pwd);
			System.out.println(connexion.getClass().getName());
			Statements statements = new Statements();
			statements.insererArticles(connexion);
			statements.augmenterPrixDe25PourCent(connexion);
			statements.afficherLesArticles(connexion);
			statements.extraireEtAfficherMoyennePrix(connexion);
			statements.supprimerIntegraliteArticles(connexion);
		} catch (SQLException e) {
			LOGGER.error("Echec: connexion à la base de données", e);
			throw new TechnicalException("La connexion à la bdd a echoué.", e);
		} finally {
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					LOGGER.error("Echec: fermeture de la connexion à la base de données", e);
					throw new TechnicalException("Fermeture de connexion à la bdd echouée.", e);
				}
			}
		}

	}

}
