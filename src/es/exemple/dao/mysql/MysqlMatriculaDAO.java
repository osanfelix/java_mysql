package es.exemple.dao.mysql;

import es.exemple.dao.MatriculaDAO;
import es.exemple.model.Matricula;
import es.exemple.model.Matricula.IdMatricula;
import java.sql.Connection;
import java.util.List;

public class MysqlMatriculaDAO implements MatriculaDAO{

	MysqlMatriculaDAO(Connection conn) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void insertar(Matricula a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void modificar(Matricula a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void eliminar(Matricula a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Matricula> obtenerTodos() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Matricula obtener(IdMatricula id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
