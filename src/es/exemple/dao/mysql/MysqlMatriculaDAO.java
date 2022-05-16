package es.exemple.dao.mysql;

import es.exemple.dao.DAOException;
import es.exemple.dao.MatriculaDAO;
import es.exemple.model.Matricula;
import es.exemple.model.Matricula.IdMatricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MysqlMatriculaDAO implements MatriculaDAO{

	// SQL Sentences
	final String INSERT = "INSERT INTO matriculas(id_alumno,id_asignatura, fecha, nota) VALUES(?,?,?,?)";
	final String UPDATE = "UPDATE matriculas SET nota = ? WHERE id_alumno = ? AND id_asignatura = ? AND fecha = ?";
	final String DELETE	= "DELETE FROM matriculas where WHERE id_alumno = ? AND id_asignatura = ? AND fecha = ?";
	final String GETALL = "SELECT id_alumno,id_asignatura, fecha, nota from matriculas";
	final String GETONE = GETALL + " WHERE id_alumno = ? AND id_asignatura = ? AND fecha = ?";
	final String GETALU = GETALL + " WHERE id_alumno = ?";
	final String GETCUR = GETALL + " WHERE fecha = ?";
	final String GETASI = GETALL + " WHERE id_asignatura = ?";
	
	// Database jdbc conection
	private final Connection conn;
	
	// Constructor
	MysqlMatriculaDAO(Connection conn)
	{
		this.conn = conn;
	}

	@Override
	public void insertar(Matricula a) throws DAOException
	{
	try(PreparedStatement stat = conn.prepareStatement(INSERT))
		{
			stat.setLong(1, a.getId().getAlumno());
			stat.setLong(2, a.getId().getAsignatura());
			stat.setInt(3, a.getId().getYear());
			
			if(a.getNota() != null)
				stat.setInt(4, a.getNota());	// Nota can be null
			else
				stat.setNull(4, 0);
			
			if(stat.executeUpdate() == 0)	throw new DAOException("No se insertó nada");
		
		} catch(SQLException ex)	{
			throw new DAOException("Error en SQL " + ex);	
		}	
	}

	@Override
	public void modificar(Matricula a) throws DAOException {
		try(PreparedStatement stat = conn.prepareStatement(UPDATE))
		{
			stat.setInt(1, a.getNota());
			stat.setLong(2, a.getId().getAlumno());
			stat.setLong(3, a.getId().getAsignatura());
			stat.setInt(4, a.getId().getYear());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se modificó nada");
			
		} catch(SQLException ex) {
			throw new DAOException("Error en SQL " + ex);	
		}
	}

	@Override
	public void eliminar(Matricula a) throws DAOException
	{
		try(PreparedStatement stat = conn.prepareStatement(UPDATE))
		{
			stat.setLong(1, a.getId().getAlumno());
			stat.setLong(2, a.getId().getAsignatura());
			stat.setInt(3, a.getId().getYear());
			
			if(stat.executeUpdate() == 0)	throw new DAOException(
					"No se eliminó nada");
		} catch(SQLException ex) {
			throw new DAOException("Error en SQL " + ex);	
		}
		
	}

	
	private Matricula convertir (ResultSet rs) throws SQLException
	{
		long id_alumno = rs.getLong("id_alumno");
		long id_asignatura = rs.getLong("id_asignatura");
		int fecha = rs.getInt("fecha");
		Integer nota = rs.getInt("nota");	// nota can be null on Database.
		if(rs.wasNull())
			nota = null;	// Always after reading value (getInt)
		
		Matricula m = new Matricula(id_alumno, id_asignatura, fecha);
		m.setNota(nota);

		return m;		
	}
	
	@Override
	public List<Matricula> obtenerTodos() throws DAOException {
		List<Matricula> matriculas = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETALL))
		{
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					matriculas.add(convertir(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return matriculas;
	}

	@Override
	public Matricula obtener(IdMatricula id) throws DAOException
	{
		Matricula m = null;
		try(PreparedStatement stat = conn.prepareStatement(GETONE))
		{
			stat.setLong(1, id.getAlumno());
			stat.setLong(2, id.getAsignatura());
			stat.setInt(3, id.getYear());
			
			try(ResultSet rs = stat.executeQuery())
			{
				if(rs.next())
					m = convertir(rs);
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return m;
	}

	@Override
	public List<Matricula> obtenerPorAlumno(long alumno) throws DAOException {
		List<Matricula> matriculas = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETALU))
		{
			stat.setLong(1, alumno);
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					matriculas.add(convertir(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return matriculas;
	}

	@Override
	public List<Matricula> obtenerPorAsignatura(long asignatura) throws DAOException {
		List<Matricula> matriculas = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETASI))
		{
			stat.setLong(1, asignatura);
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					matriculas.add(convertir(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return matriculas;
	}

	@Override
	public List<Matricula> obtenerPorCurso(int curso) throws DAOException {
		List<Matricula> matriculas = new ArrayList<>();
		try(PreparedStatement stat = conn.prepareStatement(GETCUR))
		{
			stat.setInt(1, curso);
			try(ResultSet rs = stat.executeQuery())
			{
				while(rs.next())
					matriculas.add(convertir(rs));
			}
		}catch (SQLException ex)
		{
			throw new DAOException("Error SQL " + ex);
		}
		return matriculas;
	}
	
}
