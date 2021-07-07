package es.exemple.model;

import java.util.Objects;


public class Profesor {
	private Long id;
	private String nombre;
	private String apellidos;

	public Profesor(Long id_profesor, String nombre, String apellidos) {
		this.id = id_profesor;
		this.nombre = nombre;
		this.apellidos = apellidos;
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.id);
		hash = 97 * hash + Objects.hashCode(this.nombre);
		hash = 97 * hash + Objects.hashCode(this.apellidos);
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
		final Profesor other = (Profesor) obj;
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		if (!Objects.equals(this.apellidos, other.apellidos)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Profesor{" + "id_profesor=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
	}
	
	
}
