/**
 * 
 */
package fr.diginamic.demo_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class TestConnexionJdbc {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection maConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/pizzeria?useUnicode=true"
						+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC",
				"root", "diginamic");
		System.out.println(maConnection.getClass().getName());
		maConnection.close();
	}

}
