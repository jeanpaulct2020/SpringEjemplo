package com.jeandev.dto;

import com.jeandev.model.Empleado;

public class EmpleadoDTO {

	private String nombreCompleto;
	
	public EmpleadoDTO(Empleado empleado) {
		this.setNombreCompleto(String.join(" ", empleado.getNombres(), empleado.getApellidos()));
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	
}
