package es.exemple.dao.mysql;

import es.exemple.dao.AlumnoDAO;
import es.exemple.dao.AsignaturaDAO;
import es.exemple.dao.DAOException;
import es.exemple.dao.DAOManager;
import es.exemple.dao.MatriculaDAO;
import es.exemple.dao.ProfesorDAO;
import es.exemple.model.Alumno;
import es.exemple.model.Asignatura;
import es.exemple.model.Matricula;
import es.exemple.model.Profesor;
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
	public MatriculaDAO getMatriculaDAO() {
		if(matriculas == null)
			matriculas = new MysqlMatriculaDAO(conn);
		return matriculas;
	}
	
	// DELETE, only for testing
	public static void main (String args[]) throws SQLException, DAOException
	{
		DAOManager manager = new MysqlDAOManager(
				"10.0.0.68", "DATOS", "ejemplo", "password");
		
//		manager.getMatriculaDAO().insertar(new Matricula(1, 1, 1999));
//		manager.getMatriculaDAO().insertar(new Matricula(1, 2, 1999));
//		manager.getMatriculaDAO().insertar(new Matricula(1, 3, 1999));
//		
//		manager.getMatriculaDAO().insertar(new Matricula(2, 1, 1999));
//		manager.getMatriculaDAO().insertar(new Matricula(2, 2, 1999));
//		manager.getMatriculaDAO().insertar(new Matricula(2, 3, 1999));
//		manager.getMatriculaDAO().insertar(new Matricula(2, 4, 1999));
		
		
		List<Alumno> alumnos = manager.getAlumnoDAO().obtenerTodos();
		List<Profesor> profesores = manager.getProfesorDAO().obtenerTodos();
		List<Asignatura> asignaturas = manager.getAsignaturaDAO().obtenerTodos();
		List<Matricula> matriculas = manager.getMatriculaDAO().obtenerTodos();
		
		alumnos.forEach(s -> System.out.println(s));
		profesores.forEach(s -> System.out.println(s));
		asignaturas.forEach(s -> System.out.println(s));
		matriculas.forEach(s -> System.out.println(s));
		
	}
}
