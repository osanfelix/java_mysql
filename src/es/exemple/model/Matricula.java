package es.exemple.model;

import java.util.Objects;

public class Matricula
{
	public class IdMatricula
	{

		private long alumno;
		private long asignatura;
		private int year;
		
		public IdMatricula(long alumno, long asignatura, int year)
		{
			this.alumno = alumno;
			this.asignatura = asignatura;
			this.year = year;
		}
		
		public long getAlumno() {
			return alumno;
		}

		public void setAlumno(long alumno) {
			this.alumno = alumno;
		}

		public long getAsignatura() {
			return asignatura;
		}

		public void setAsignatura(long asignatura) {
			this.asignatura = asignatura;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
	}
	
	private IdMatricula id = null;
	private Integer nota = null;

	public Matricula(IdMatricula id) {
		this.id = id;
	}

	public IdMatricula getId() {
		return id;
	}

	public void setId(IdMatricula id) {
		this.id = id;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + Objects.hashCode(this.id);
		hash = 47 * hash + Objects.hashCode(this.nota);
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
		final Matricula other = (Matricula) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.nota, other.nota)) {
			return false;
		}
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Matricula{" + "alumno=" + id.getAlumno() + ", asignatura=" + id.getAsignatura() + ", year=" + id.getYear() + ", nota=" + nota + '}';
	}
	
	
}
