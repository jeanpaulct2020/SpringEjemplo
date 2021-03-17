package com.jeandev.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeandev.model.Empleado;
import com.jeandev.model.Skill;

@Repository
@Transactional
public interface SkillRepository extends CrudRepository<Skill, Integer>{

	
	//Integer deleteByEmpleado(Empleado empleado);
	
	@Modifying
	@Query("DELETE FROM Skill s WHERE s.empleado = ?1")
	Integer eliminarPorEmpleado(Empleado empleado);
}
