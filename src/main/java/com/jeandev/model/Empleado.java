package com.jeandev.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idempleado", columnDefinition = "serial")
	private Integer idEmpleado;
	
	
	@NotEmpty(message="El nombre es obligatorio")
	@Column(name = "nombres", length = 50)
	private String nombres;
	
	@NotEmpty(message="Los apellidos son obligatorios")
	@Column(name = "apellidos", length = 100)
	private String apellidos;
	
	@NotEmpty(message="El documento es obligatorio")
	@Column(name = "documento", length = 10)
	private String documento;
	
	
	@NotEmpty(message="La fecha de nacmiento es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechanacimiento")
	private LocalDate fechaNacimiento;
	
	@NotNull(message="El sueldo es obligatorio")
	@Column(name = "sueldo", columnDefinition = "NUMERIC")
	private double sueldo;
	
	@NotNull(message="El campo es obligatorio")
	@Column(name = "numerohijos", columnDefinition = "NUMERIC")
	private int numeroHijos;
	
	@Column(name = "usuario", length = 10)
	private String usuario;
	
	@Column(name = "clave", length = 10)
	private String clave;
	
	@ManyToOne()
	@JoinColumn(name = "tipoempleado") // la tabla con la que se va a relacionar
	private TipoEmpleado tipoEmpleado;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL) //el cascade.ALL : mantiene la persistencia: todas las operaciones que yo haga en empleado, persisten en skills
	//@JsonIgnore
	private List<Skill> skills;
	
	public Empleado() {
		super();
	}

	public Empleado(Integer idEmpleado, @NotEmpty(message = "El nombre es obligatorio") String nombres,
			@NotEmpty(message = "Los apellidos son obligatorios") String apellidos,
			@NotEmpty(message = "El documento es obligatorio") String documento,
			@NotEmpty(message = "La fecha de nacmiento es obligatoria") LocalDate fechaNacimiento,
			@NotNull(message = "El sueldo es obligatorio") double sueldo,
			@NotNull(message = "El campo es obligatorio") int numeroHijos, String usuario, String clave,
			TipoEmpleado tipoEmpleado, List<Skill> skills) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.sueldo = sueldo;
		this.numeroHijos = numeroHijos;
		this.usuario = usuario;
		this.clave = clave;
		this.tipoEmpleado = tipoEmpleado;
		this.skills = skills;
	}






	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public int getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}


	
	
	
	public List<Skill> getSkills() {
		return skills;
	}



	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", documento=" + documento + ", fechaNacimiento=" + fechaNacimiento + ", sueldo=" + sueldo
				+ ", numeroHijos=" + numeroHijos + ", usuario=" + usuario + ", clave=" + clave + ", tipoEmpleado="
				+ tipoEmpleado + ", skills=" + skills + "]";
	}

	
	
}
