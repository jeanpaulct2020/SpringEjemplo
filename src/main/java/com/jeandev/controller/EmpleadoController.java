package com.jeandev.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeandev.model.Empleado;
import com.jeandev.model.Skill;
import com.jeandev.service.EmpleadoService;
import com.jeandev.service.SkillService;
import com.jeandev.service.TipoEmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private TipoEmpleadoService tipoEmpleadoService;
	

	@Autowired
	private SkillService skillService;
	
	@GetMapping("")
	public String listarEmpleados(Model model) {
		model.addAttribute("listaEmpleados", empleadoService.getAll());
		return "empleados/empleados";
	}
	
	@GetMapping("/nuevo")
	public String empleadoVista(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("listaTipos", tipoEmpleadoService.getTipos());
		return "empleados/nuevo";
	}
	
	@PostMapping("/crear")
	public String empleadoRegistrar(@Validated Empleado empleado, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("listaTipos", tipoEmpleadoService.getTipos());
			return "redirect:empleados/nuevo";
		}
		
		/* en caso de que no utilice jpa tengo que usar las dos inserciones, pero con jpa no solo es en cascada
		//el tamanio del arreglo es depende de la cantidad de skill que el usuario eligio
		String[] skills = new String[empleado.getSkills().size()];
		
		//primero metemos todos los skills de empleado en un arreglo de skills
		for (int i = 0; i < empleado.getSkills().size(); i++) {
			skills[i] = empleado.getSkills().get(i).getDescripcion(); 
		}
		
		//insertamos el empleado y si es correcto, insertamos en la tabla skill las habilidades(skill) que eligio
		int status = empleadoService.insert(empleado);
		
		if(status == 1) {
			Skill skill;
			//recorremos el arreglo de skills e insertamos en un skill cada elemento
			for (String sk : skills) {
				skill = new Skill();
				skill.setDescripcion(sk);
				skill.setEmpleado(empleado);
				skillService.insert(skill);
			}
			
		} */
		
		List<Skill> skills = new ArrayList<>();
		if (empleado.getSkills() != null) {
			skills = empleado.getSkills().stream().map(s -> {
				return new Skill(s.getDescripcion(), empleado);
			}).collect(Collectors.toList());
			empleado.setSkills(skills);
		}
		empleadoService.insert(empleado);
		
		return "redirect:/empleados";
	}
	
	/*actualizar*/
	@GetMapping("/editar/{id}")
	public String empleadoEditar(@PathVariable(value = "id") Integer idEmpleado,Model model) {	
		
		//buscar al empleado, de acuerdo al id enviado por parametro
		Empleado empleado = empleadoService.getById(idEmpleado);
		model.addAttribute("empleado", empleado);
		model.addAttribute("listaTipos", tipoEmpleadoService.getTipos());
		
		return "empleados/editar";
	}
	
	@PostMapping("/actualizar/{id}")
	public String empleadoActualizar(@PathVariable(value = "id") Integer idEmpleado ,  @Validated Empleado empleado,
			BindingResult result, RedirectAttributes attr, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("listaTipos", tipoEmpleadoService.getTipos());
			return "empleados/editar";
		}
		
		List<Skill> skills = new ArrayList<>();
		
		if (empleado.getSkills() != null) {
			skills = empleado.getSkills().stream().map(s -> {
				return new Skill(s.getDescripcion(), empleado);
			}).collect(Collectors.toList());
			empleado.setSkills(skills);
		}
		
		empleadoService.update(empleado);
		
		return "redirect:/empleados";
	}
	
	/*eliminar*/
	@GetMapping("/eliminar/{id}")
	public String empleadoEliminar(@PathVariable(value = "id") Integer idEmpleado) {
		empleadoService.delete(idEmpleado);
		return "redirect:/empleados";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {

		CustomCollectionEditor skillsCollector = new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
//					Integer id = Integer.parseInt((String) element);
					String selectValue = (String) element;

					Skill skill = new Skill();
					skill.setDescripcion(selectValue);
					return skill;
				}
				throw new RuntimeException("InitBinder error in element: " + element);
			}
		};

		binder.registerCustomEditor(List.class, "skills", skillsCollector);
	}
}
