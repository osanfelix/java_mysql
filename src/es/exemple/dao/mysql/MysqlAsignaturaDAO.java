package es.exemple.dao.mysql;

import es.exemple.dao.AsignaturaDAO;
import es.exemple.model.Asignatura;
import java.sql.Connection;
import java.util.List;

public class MysqlAsignaturaDAO implements AsignaturaDAO{

	MysqlAsignaturaDAO(Connection conn) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void insertar(Asignatura a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void modificar(Asignatura a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void eliminar(Asignatura a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Asignatura> obtenerTodos() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Asignatura obtener(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
