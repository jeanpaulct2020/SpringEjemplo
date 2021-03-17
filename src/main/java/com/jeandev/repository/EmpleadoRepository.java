package com.jeandev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeandev.model.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

}
