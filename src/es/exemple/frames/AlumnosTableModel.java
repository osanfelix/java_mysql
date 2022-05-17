
package es.exemple.frames;

import es.exemple.dao.AlumnoDAO;
import javax.swing.table.AbstractTableModel;
import es.exemple.dao.DAOException;
import es.exemple.model.Alumno;
import java.text.DateFormat;
import java.util.List;

public class AlumnosTableModel extends AbstractTableModel{

	private AlumnoDAO alumnos = null;
	private List<Alumno> datos;
	
	public AlumnosTableModel(AlumnoDAO alumnos)
	{
		 this.alumnos = alumnos;
	}

	public void updateModel() throws DAOException
	{
		datos = alumnos.obtenerTodos();
	}
	
	@Override
	public String getColumnName(int column)
	{
		switch(column)
		{
			case 0:	return "ID";
			case 1:	return "Nombre";
			case 2:	return "Apellidos";
			case 3:	return "Fecha nacimiento";
			default: return "[no]";
		}
	}

	@Override
	public int getRowCount()
	{
		return datos.size();
	}

	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Alumno al = datos.get(rowIndex);
		switch (columnIndex)
		{
			case 0: return al.getId();
			case 1: return al.getNombre();
			case 2: return al.getApellidos();
			case 3:
				DateFormat df = DateFormat.getDateInstance();
				return df.format(al.getFecha_nac());
			default: return null;
		}
	}
	
	
}
