package com.jeandev.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeandev.model.Empleado;

public interface EmpleadoService {

	Integer insert(Empleado empleado);
	
	Integer update(Empleado empleado);
	
	Integer delete(Integer idEmpleado);
	
	Empleado getById(Integer idEmpleado);
	
	List<Empleado> getAll();
	
	/*login*/
	Empleado login(String usuario, String clave);
}
