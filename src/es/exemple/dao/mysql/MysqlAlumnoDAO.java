package es.exemple.dao.mysql;

import es.exemple.dao.AlumnoDAO;
import es.exemple.dao.DAOException;
import es.exemple.model.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MysqlAlumnoDAO implements AlumnoDAO
{
	// SQL Sentences
	final String INSERT = "INSERT INTO alumnos(nombre,apellidos,fecha_nac) VALUES(?,?,?)";
	final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ? WHERE id_alumno = ?";
	final String DELETE	= "DELETE FROM alumnos where id_alumno = ?";
	final String GETALL = "SELECT id_alumno, nombre, apellidos, fecha_nac from alumnos";
	final String GETONE = "SELECT id_alumno, nombre, apellidos, fecha_nac from alumnos WHERE id_alumno = ?";
	
	// Database jdbc conection
	private final Connection conn;

	// Constructor
	public MysqlAlumnoDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insertar(Alumno a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(INSERT))
		{
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellidos());
			stat.setDate(3, new Date(a.getFecha_nac().getTime()));	// sql date, no utils Date (deprecated). Better use java.time
			
			// update alumno id (object) from database
			try(ResultSet rs = stat.getGeneratedKeys())
			{
				if(rs.next())	a.setId(rs.getLong(1));
				else throw new DAOException("Error al asignar ID al alumno insertado");
			}
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se insertó nada");
		}catch (SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
	}

	@Override
	public void modificar(Alumno a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(UPDATE))
		{
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellidos());
			stat.setDate(3, new Date(a.getFecha_nac().getTime()));	// sql date, no utils Date (deprecated). Better use java.time
			stat.setLong(4, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se modificó nada");
		}catch (SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
	}

	@Override
	public void eliminar(Alumno a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(DELETE))
		{
			stat.setLong(1, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se eliminó nada");
		}catch (SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
	}

	private Alumno convertirAlumno (ResultSet rs) throws SQLException
	{
		String nombre = rs.getString("nombre");
		String apellidos = rs.getString("apellidos");
		Date fecha_nac = rs.getDate("fecha_nac");

		Alumno a = new Alumno (nombre, apellidos, fecha_nac);
		a.setId(rs.getLong("id_alumno"));
		
		return a;		
	}
	
	@Override
	public List<Alumno> obtenerTodos() throws DAOException
	{
		List<Alumno> alumnos = new ArrayList<>();
		
		try(PreparedStatement stat = conn.prepareStatement(GETALL))
		{
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					alumnos.add(convertirAlumno(rs));
			}
		return alumnos;	
		}catch (SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}
	}

	@Override
	public Alumno obtener(Long id) throws DAOException
	{
		Alumno a = null;
		ResultSet rs = null;
		
		try(PreparedStatement stat = conn.prepareStatement(GETONE))
		{
			stat.setLong(1, id);
			rs = stat.executeQuery();
			
			if(rs.next())
				a = convertirAlumno(rs);
			else
				throw new DAOException("No se ha encontrado ese registro " + MysqlAlumnoDAO.class);
			
			return a;

		}catch (SQLException ex)
		{
			throw new DAOException("Error en SQL", ex);
		}finally {	// nos lo pordriamos ahorrar, como en el método anterior
			if(rs != null)	try {
				rs.close();
			} catch (SQLException ex) {
				throw new DAOException("Error en SQL", ex);
			}
		}
	}
	
}
