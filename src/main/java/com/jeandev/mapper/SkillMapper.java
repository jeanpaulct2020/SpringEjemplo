package com.jeandev.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jeandev.model.Skill;



@Mapper
public interface SkillMapper {

	@Insert("INSERT INTO skills( descripcion, idempleado) values (#{descripcion}, #{empleado.idEmpleado} )")
	Integer insert(Skill skill);
	
	@Select("SELECT * FROM skills WHERE idempleado = #{idEmpleado}")
	List<Skill> skillsPorEmpleado(@Param("idEmpleado") Integer idEmpleado);
	
}
