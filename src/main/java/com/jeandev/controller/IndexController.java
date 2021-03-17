package com.jeandev.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	
	@GetMapping("/")
	public String inicio(Model model, Principal principal) {
		//model.addAttribute("name", "Jean Paul Cochachin Torres");
		
		return principal != null ? "index" : "fragments/layout";
	}
	
}
