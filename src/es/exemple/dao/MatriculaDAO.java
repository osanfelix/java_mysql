package es.exemple.dao;

import es.exemple.model.Matricula;
import es.exemple.model.Matricula.IdMatricula;
import java.util.List;

public interface MatriculaDAO extends DAO<Matricula, IdMatricula> {
	List<Matricula> obtenerPorAlumno(long alumno) throws DAOException;
	List<Matricula> obtenerPorAsignatura(long asignatura) throws DAOException;
	List<Matricula> obtenerPorCurso(int curso) throws DAOException;
}
