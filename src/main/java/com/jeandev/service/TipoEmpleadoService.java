package com.jeandev.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jeandev.model.TipoEmpleado;

public interface TipoEmpleadoService {

	TipoEmpleado getTipo(Integer idTipoEmpleado);
	
	List<TipoEmpleado> getTipos();
}
