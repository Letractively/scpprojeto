package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 */
public class BancoDados {
	public static Connection con;


	public static Connection conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/modelscp",
					"root", "123");
		} catch (SQLException ex) {
			Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return con;

	}

	public static void desconectar() {
		try {
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}