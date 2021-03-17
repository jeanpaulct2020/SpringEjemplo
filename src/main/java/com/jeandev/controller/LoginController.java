package com.jeandev.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeandev.dto.EmpleadoDTO;
import com.jeandev.model.Empleado;
import com.jeandev.service.EmpleadoService;

@Controller
public class LoginController {


	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/login")
	public String inicio(Model model) {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam String user,@RequestParam String password, Model model, Principal principal) {
		
		Empleado empleado = empleadoService.login(user, password);
		
		if (empleado instanceof Empleado) {
			
			EmpleadoDTO empleadoDTO = new EmpleadoDTO(empleado);
			
			model.addAttribute("empleado",empleadoDTO);
			
			return principal != null ? "index" : "fragments/layout";
		} else {
			model.addAttribute("message", "Usuario y/o contraseña incorrecta");
			return "login";
		}
		
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/login";
	}
}
