package es.exemple.dao.mysql;

import es.exemple.dao.AlumnoDAO;
import es.exemple.dao.AsignaturaDAO;
import es.exemple.dao.DAOException;
import es.exemple.dao.DAOManager;
import es.exemple.dao.MatriculaDAO;
import es.exemple.dao.ProfesorDAO;
import es.exemple.model.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MysqlDAOManager implements DAOManager
{
	
	// Database Attributes
	private Connection conn = null;
	static final String DEF = "jdbc:mysql://";
	
	String host;
	String database;
	String user;
	String passwd;
	
	// DAO issues
	AlumnoDAO alumnos = null;
	ProfesorDAO profesores = null;
	AsignaturaDAO asignaturas = null;
	MatriculaDAO matriculas = null;
	
	
	public MysqlDAOManager(String host, String database, String user, String passwd) throws SQLException
	{
		this.host = host;
		this.database = database;
		this.user = user;
		this.passwd = passwd;
		
		 conn = DriverManager.getConnection(DEF+host+":3306/"+database, user, passwd);
	}

	@Override
	public AlumnoDAO getAlumnoDAO() {
		if(alumnos == null)
			alumnos = new MysqlAlumnoDAO(conn);
		return alumnos;
	}

	@Override
	public ProfesorDAO getProfesorDAO() {
		if(profesores == null)
			profesores = new MysqlProfesorDAO(conn);
		return profesores;
	}

	@Override
	public AsignaturaDAO getAsignaturaDAO() {
		if(asignaturas == null)
			asignaturas = new MysqlAsignaturaDAO(conn);
		return asignaturas;
	}

	@Override
	public MatriculaDAO getMAtriculaDAO() {
		if(matriculas == null)
			matriculas = new MysqlMatriculaDAO(conn);
		return matriculas;
	}
	
	// DELETE, inly for testing
	public static void main (String args[]) throws SQLException, DAOException
	{
		MysqlDAOManager manager = new MysqlDAOManager("10.0.0.68", "DATOS", "ejemplo", "password");
		List<Alumno> alumnos = manager.getAlumnoDAO().obtenerTodos();
		alumnos.forEach(s -> System.out.println(s));
	}
}
