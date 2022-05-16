
package es.exemple.dao.mysql;

import es.exemple.dao.DAOException;
import es.exemple.dao.ProfesorDAO;
import es.exemple.model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlProfesorDAO implements ProfesorDAO
{

	// SQL Sentences
	final String INSERT = "INSERT INTO profesores(nombre,apellidos) VALUES(?,?)";
	final String UPDATE = "UPDATE profesores SET nombre = ?, apellidos = ? WHERE id_profesor = ?";
	final String DELETE	= "DELETE FROM profesores where id_profesor = ?";
	final String GETALL = "SELECT id_profesor, nombre, apellidos from profesores";
	final String GETONE = "SELECT id_profesor, nombre, apellidos from profesores WHERE id_profesor= ?";
	
	// Database jdbc conection
	private final Connection conn;
	
	// Constructor
	MysqlProfesorDAO(Connection conn)
	{
		this.conn = conn;
	}

	@Override
	public void insertar(Profesor a) throws DAOException {
		try(PreparedStatement stat = conn.prepareStatement(INSERT))
		{
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellidos());
			try(ResultSet rs = stat.getGeneratedKeys())
			{
				if(rs.next())	a.setId(rs.getLong(1));
				else throw new DAOException("Error al asignar ID al profesor insertado");
			}
			if(stat.executeUpdate() == 0)	throw new DAOException("No se insertó nada");
		
		} catch(SQLException ex)	{
			throw new DAOException("Error en SQL " + ex);	
		}
	}

	@Override
	public void modificar(Profesor a) throws DAOException  {
		try(PreparedStatement stat = conn.prepareStatement(UPDATE))
		{
			stat.setString(1, a.getNombre());
			stat.setString(2, a.getApellidos());
			stat.setLong(3, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se modificó nada");
			
		} catch(SQLException ex) {
			throw new DAOException("Error en SQL " + ex);	
		}
	}

	@Override
	public void eliminar(Profesor a) throws DAOException {
		try(PreparedStatement stat = conn.prepareStatement(DELETE))
		{
			stat.setLong(1, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se eliminó nada");
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL " + ex);
		}
	}

	private Profesor convertirProfesor(ResultSet rs) throws SQLException
	{
		String nombre = rs.getString("nombre");
		String apellidos = rs.getString("apellidos");
		return new Profesor(rs.getLong("id_profesor"), nombre, apellidos);
	}
	
	@Override
	public List<Profesor> obtenerTodos() throws DAOException
	{
		List<Profesor> profesores = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETALL))
		{
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					profesores.add(convertirProfesor(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return profesores;
	}

	@Override
	public Profesor obtener(Long id) throws DAOException
	{
		Profesor p = null;
		try(PreparedStatement stat = conn.prepareStatement(GETONE))
		{
			stat.setLong(1, id);
		
			try(ResultSet rs = stat.executeQuery())
			{
				if(rs.next())
					p = convertirProfesor(rs);
				else
					throw new DAOException("No se ha encontrado ese registro " + MysqlProfesorDAO.class);
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL " + ex);
		}
		return p;
	}
	
}
