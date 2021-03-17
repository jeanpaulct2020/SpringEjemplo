package com.jeandev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeandev.model.TipoEmpleado;

@Repository
public interface TipoEmpleadoRepository extends CrudRepository<TipoEmpleado, Integer>{

	
	
}
