package com.jeandev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoempleado")
public class TipoEmpleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipoempleado", columnDefinition = "serial")
	private Integer idTipoEmpleado;
	
	//@Transient no me cree una tabla
	//private String estado;
	
	@Column(name = "descripcion", length = 50)
	private String descripcion;
	
	public TipoEmpleado() {
		super();
	}
	
	public TipoEmpleado(Integer idTipoEmpleado, String descripcion) {
		super();
		this.idTipoEmpleado = idTipoEmpleado;
		this.descripcion = descripcion;
	}
	
	public Integer getIdTipoEmpleado() {
		return idTipoEmpleado;
	}
	public void setIdTipoEmpleado(Integer idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoEmpleado [idTipoEmpleado=" + idTipoEmpleado + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
	
}
