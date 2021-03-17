package com.jeandev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "skills")
public class Skill implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idskill", columnDefinition = "serial")
	private Integer idSKill;
	
	@Column(name = "descripcion", length = 50)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "idempleado")
	@JsonIgnore
	private Empleado empleado;
	
	public Skill() {
		super();
	}


	public Skill(String descripcion, Empleado empleado) {
		super();
		this.descripcion = descripcion;
		this.empleado = empleado;
	}


	public Integer getIdSKill() {
		return idSKill;
	}


	public void setIdSKill(Integer idSKill) {
		this.idSKill = idSKill;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	@Override
	public String toString() {
		return "SKill [idSKill=" + idSKill + ", descripcion=" + descripcion + ", empleado=" + empleado + "]";
	}
	
	
}
