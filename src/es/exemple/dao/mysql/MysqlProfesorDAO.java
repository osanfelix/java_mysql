
package es.exemple.dao.mysql;

import es.exemple.dao.ProfesorDAO;
import es.exemple.model.Profesor;
import java.sql.Connection;
import java.util.List;

public class MysqlProfesorDAO implements ProfesorDAO{

	// Constructor
	MysqlProfesorDAO(Connection conn) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void insertar(Profesor a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void modificar(Profesor a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void eliminar(Profesor a) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Profesor> obtenerTodos() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Profesor obtener(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
