
package es.exemple.model;

import java.util.Date;
import java.util.Objects;


public class Alumno
{
	private Long id = null;		// Can be null
	private String nombre;
	private String apellidos;
	private Date fecha_nac;

	public Alumno(String nombre, String apellidos, Date fecha_nac)
	{
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nac = fecha_nac;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	@Override
	public String toString() {
		return "Alumno{" + "id_alumno=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + Objects.hashCode(this.id);
		hash = 59 * hash + Objects.hashCode(this.nombre);
		hash = 59 * hash + Objects.hashCode(this.apellidos);
		hash = 59 * hash + Objects.hashCode(this.fecha_nac);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Alumno other = (Alumno) obj;
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		if (!Objects.equals(this.apellidos, other.apellidos)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.fecha_nac, other.fecha_nac)) {
			return false;
		}
		return true;
	}
	
	
}

