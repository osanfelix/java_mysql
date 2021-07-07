package es.exemple.model;

import java.util.Objects;

public class Asignatura
{
	private Long id;
	private String nombre;
	private Long idProfesor;

	public Asignatura(Long id, String nombre, Long idProfesor) {
		this.id = id;
		this.nombre = nombre;
		this.idProfesor = idProfesor;
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

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 11 * hash + Objects.hashCode(this.id);
		hash = 11 * hash + Objects.hashCode(this.nombre);
		hash = 11 * hash + Objects.hashCode(this.idProfesor);
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
		final Asignatura other = (Asignatura) obj;
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.idProfesor, other.idProfesor)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Asignatura{" + "id=" + id + ", nombre=" + nombre + ", idProfesor=" + idProfesor + '}';
	}
	
}
