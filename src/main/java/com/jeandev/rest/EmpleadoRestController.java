package com.jeandev.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeandev.model.Empleado;
import com.jeandev.model.Skill;
import com.jeandev.service.EmpleadoService;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoRestController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping
	List<Empleado> listarEmpleados() {
		return empleadoService.getAll();
	}
	
	//metodo para registrar
	@PostMapping
	ResponseEntity<Integer> crearEmpleado(@RequestBody Empleado empleado) { //con el requesBody espcificamos el objeto, el cual va a hacer el cuerpo de mi peticion
		
		List<Skill> skills = new ArrayList<>();
		if (empleado.getSkills() != null) {
			skills = empleado.getSkills().stream().map(s -> {
				return new Skill(s.getDescripcion(), empleado);
			}).collect(Collectors.toList());
			empleado.setSkills(skills);
		}
		ResponseEntity<Integer> resp = ResponseEntity.ok(empleadoService.insert(empleado));
		return resp;
	}
	
	@PutMapping("{id}")
	ResponseEntity<Integer> actualizarEmpleado(@PathVariable(value = "id") Integer idEmpleado, @RequestBody Empleado empleado) { //con el requesBody espcificamos el objeto, el cual va a hacer el cuerpo de mi peticion
		
		empleado.setIdEmpleado(idEmpleado); // en caso de que no quiera llenar el campo:valor al consumir el recurso
		
		List<Skill> skills = new ArrayList<>();
		
		if (empleado.getSkills() != null) {
			skills = empleado.getSkills().stream().map(s -> {
				return new Skill(s.getDescripcion(), empleado);
			}).collect(Collectors.toList());
			empleado.setSkills(skills);
		}
		
		ResponseEntity<Integer> resp = ResponseEntity.ok(empleadoService.update(empleado));
		return resp;
	}
	
	@GetMapping("{id}")
	Empleado listarEmpleadosPorId(@PathVariable(value = "id") Integer idEmpleado) {
		return empleadoService.getById(idEmpleado);
		
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<String> eliminarEmpleado(@PathVariable(value = "id") Integer idEmpleado) {
		int status = empleadoService.delete(idEmpleado);
		if (status == 1) { 
			return ResponseEntity.ok("Se elimino correctamente !!!");
		}
		return ResponseEntity.badRequest().body("No se encontro el empleado a eliminar");
	}
	
	
	
}
