/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExampleMain
{

	public static void simpleConnection()
	{
		String jdbc = "jdbc:mysql://10.0.0.68:3306/DATOS";
		
		try(Connection connection  = DriverManager.getConnection(jdbc,"ejemplo", "password"))
		{
			// Do something with database...
		} catch (SQLException ex) {
			Logger.getLogger(ExampleMain.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	Connection connection = null;
	
	public ExampleMain()
	{
		
	}
	
	
	void connect() throws SQLException
	{
		String jdbc = "jdbc:mysql://10.0.0.68:3306/DATOS";
		connection  = DriverManager.getConnection(jdbc,"ejemplo", "password");
	}
	
	void query_Asignaturas_profesores() throws SQLException
	{
		try (
				Statement statement = connection.createStatement();
				ResultSet set = statement.executeQuery("SELECT asignaturas.id_asignatura,"
				+ " asignaturas.nombre, profesores.nombre, profesores.apellidos"
				+ " from asignaturas, profesores where asignaturas.id_profesor = profesores.id_profesor;")
			)
		{
			while (set.next())
			{
				int id_asignatura = set.getInt("id_asignatura");
				String asignatura = set.getString("asignaturas.nombre");
				String profesor = set.getString("profesores.nombre") + " " + 
						set.getString("apellidos");
				System.out.println(id_asignatura + " " + asignatura + ": "+ profesor);
			}
		}	
	}
	
	void query(String apellidos) throws SQLException
	{
		String query = "SELECT id_alumno, nombre, apellidos, fecha_nac from alumnos where apellidos = ?;";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, apellidos);
		ResultSet set = statement.executeQuery();

		while (set.next())
		{
			int id_alumno = set.getInt("id_alumno");
			String nombre = set.getString("nombre") + " " + set.getString("apellidos");
			System.out.println(id_alumno + " " + nombre);
		}
		set.close();
		statement.close();
	}
	
	void close() throws SQLException
	{
		if(connection != null)
			connection.close();
	}
	
	
	public void exemple1() throws SQLException
	{
		try	{
			connect();
			query_Asignaturas_profesores();
		} finally {
			close();
		}
	}
	
	public void exemple2() throws SQLException
	{
		try	{
			connect();
			query("Lopez");
		} finally {
			close();
		}
	}
	
	// Transaction 
	public void exemple3() throws SQLException
	{
		String jdbc = "jdbc:mysql://10.0.0.68:3306/DATOS";
		try(Connection con  = DriverManager.getConnection(jdbc,"ejemplo", "password"))
		{
			con.setAutoCommit(false);	// needed for transactions
			
			
			String query1 = "INSERT INTO profesores(nombre, apellidos) VALUES(?, ?);";
			String query2 = "INSERT INTO asignaturas(nombre, id_profesor) VALUES(?,?);";
			
			PreparedStatement statement1 = null, statement2 = null;
			
			// BEGIN
			try {
			statement1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			statement1.setString(1, "Joanot");
			statement1.setString(2, "Martorell");
			int affectedRows1  = statement1.executeUpdate();		// INSERTS, not query

			if(affectedRows1 == 0)	throw new SQLException("Creating profesor failed, no rows affected.");
			statement2 = con.prepareStatement(query2);
			statement2.setString(1, "Bases de datos");
			ResultSet keys = statement1.getGeneratedKeys();	// get profe id
			if(keys.next())	statement2.setInt(2, (int) keys.getLong(1));
			int affectedRows2 = statement2.executeUpdate();
			if(affectedRows2 == 0)	throw new SQLException("Creating asignatura failed, no rows affected.");

			// COMMIT
			con.commit();

			} catch (SQLException ex) {
				con.rollback();			// ROLLBACK
				Logger.getLogger(ExampleMain.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				if(statement1 != null)	statement1.close();
				if(statement2 != null)	statement2.close();
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		try {
			(new ExampleMain()).exemple1();
			(new ExampleMain()).exemple2();
			(new ExampleMain()).exemple3();	// Insert
		} catch (SQLException ex) {
			Logger.getLogger(ExampleMain.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
