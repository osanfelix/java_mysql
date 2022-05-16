package es.exemple.dao.mysql;

import es.exemple.dao.AsignaturaDAO;
import es.exemple.dao.DAOException;
import es.exemple.model.Asignatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlAsignaturaDAO implements AsignaturaDAO
{
	// SQL Sentences
	final String INSERT = "INSERT INTO asignaturas(nombre,id_profesor) VALUES(?,?)";
	final String UPDATE = "UPDATE asignaturas SET nombre = ?, id_profesor = ? WHERE id_asignatura = ?";
	final String DELETE	= "DELETE FROM asignaturas where id_asignatura = ?";
	final String GETALL = "SELECT id_asignatura, nombre, id_profesor from asignaturas";
	final String GETONE = "SELECT id_asignatura, nombre, id_profesor from asignaturas WHERE id_asignatura = ?";

	// Database jdbc conection
	private final Connection conn;
	
	MysqlAsignaturaDAO(Connection conn)
	{
		this.conn = conn;
	}

	@Override
	public void insertar(Asignatura a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(INSERT))
		{
			stat.setString(1, a.getNombre());
			stat.setLong(2, a.getIdProfesor());
			
			// update alumno id (object) from database
			try(ResultSet rs = stat.getGeneratedKeys())
			{
				if(rs.next())	a.setId(rs.getLong(1));
				else throw new DAOException("Error al asignar ID a la asignatura insertada");
			}
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se insertó nada");
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL", ex);
		}
	}

	@Override
	public void modificar(Asignatura a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(UPDATE))
		{
			stat.setString(1, a.getNombre());
			stat.setLong(2, a.getIdProfesor());
			stat.setLong(3, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se modificó nada");
			
		} catch(SQLException ex) {
			throw new DAOException("Error en SQL " + ex);	
		}
	}

	@Override
	public void eliminar(Asignatura a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(DELETE))
		{
			stat.setLong(1, a.getId());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se eliminó nada");
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL " + ex);
		}
	}

	private Asignatura convertirAsignatura(ResultSet rs) throws SQLException
	{
		Long id_asignatura = rs.getLong("id_asignatura");
		String nombre = rs.getString("nombre");
		Long id_profesor = rs.getLong("id_profesor");
		
		return new Asignatura(id_asignatura, nombre, id_profesor);
	}
	
	@Override
	public List<Asignatura> obtenerTodos() throws DAOException
	{
		List<Asignatura> asignaturas = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETALL))
		{
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					asignaturas.add(convertirAsignatura(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return asignaturas;
	}

	@Override
	public Asignatura obtener(Long id) throws DAOException
	{
		Asignatura a = null;
		try(PreparedStatement stat = conn.prepareStatement(GETONE))
		{
			stat.setLong(1, id);
		
			try(ResultSet rs = stat.executeQuery())
			{
				if(rs.next())
					a = convertirAsignatura(rs);
				else
					throw new DAOException("No se ha encontrado ese registro " + MysqlAsignaturaDAO.class);
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL " + ex);
		}
		return a;
	}
}
