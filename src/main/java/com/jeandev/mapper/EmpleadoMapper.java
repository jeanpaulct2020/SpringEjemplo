package com.jeandev.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jeandev.model.Empleado;
import com.jeandev.model.TipoEmpleado;

@Mapper
public interface EmpleadoMapper {

	@Insert("INSERT INTO empleado (nombres, apellidos, documento, fechanacimiento, sueldo, numerohijos, usuario, clave, tipoempleado)" +
			"VALUES (#{nombres}, #{apellidos}, #{documento}, #{fechaNacimiento}, #{sueldo}, #{numeroHijos}, #{usuario}, #{clave}, #{tipoEmpleado.idTipoEmpleado})")
	@Options(useGeneratedKeys = true, keyProperty = "idEmpleado", keyColumn = "idempleado") //cuando se ejecute la query caoture el valor que le especifiques y lo inyecte en el empleado
	Integer insert(Empleado empleado);
	
	@Update("UPDATE empleado SET nombres = #{nombres}, apellidos = #{apellidos}, documento = #{documento}, fechanacimiento = #{fechaNacimiento}, " 
			+ "sueldo = #{sueldo}, numerohijos = #{numeroHijos}, usuario = #{usuario}, clave = #{clave}, tipoEmpleado = #{tipoEmpleado.idTipoEmpleado} WHERE idempleado = #{idEmpleado}" )
	Integer update(Empleado empleado);
	
	@Delete("DELETE FROM empleado WHERE idempleado = #{idEmpleado}")
	Integer delete(@Param("idEmpleado") Integer idEmpleado);
	
	
	@Select("SELECT * FROM empleado WHERE idempleado = #{idEmpleado}")
	@Results({
			@Result(property = "idEmpleado", column = "idempleado")
			,
			@Result(property = "tipoEmpleado", 
			javaType = TipoEmpleado.class, 
			column = "tipoempleado", 
			one = @One(select = "com.jeandev.mapper.TipoEmpleadoMapper.getTipo"))
			,
			@Result(property = "skills", 
			javaType = List.class, 
			column = "idEmpleado", 
			many = @Many(select = "com.jeandev.mapper.SkillMapper.skillsPorEmpleado"))
	})
	Empleado getById(@Param("idEmpleado") Integer idEmpleado);
	
	@Select("SELECT * FROM empleado")
	@Results( 
			@Result(property = "tipoEmpleado", 
					javaType = TipoEmpleado.class,
					column = "tipoempleado",
					one = @One(select = "com.jeandev.mapper.TipoEmpleadoMapper.getTipo")
			)
		) //como si fuera un join
	List<Empleado> getAll();
	
	//login comun
	@Select("SELECT * FROM empleado WHERE usuario = #{usuario} AND clave = #{clave}")
	@Results( 
			@Result(property = "tipoEmpleado", 
					javaType = TipoEmpleado.class,
					column = "tipoempleado",
					one = @One(select = "com.jeandev.mapper.TipoEmpleadoMapper.getTipo")
			)
		) //como si fuera un join
	Empleado login(@Param("usuario")String usuario,@Param("clave") String clave);
	
	//primero hay que hacer la busqueda por el usuarip y luego comparar el pass para ver si coincide con el cifrado
	@Select("SELECT * FROM empleado WHERE usuario = #{usuario}")
	@Results(
			@Result(
				property = "tipoEmpleado", 
				javaType = TipoEmpleado.class,
				column = "tipoempleado",
				one = @One(select = "com.jeandev.mapper.TipoEmpleadoMapper.getTipo")
			)
	)
	Empleado obtenerPorUsuario(@Param("usuario") String usuario);
	
}
