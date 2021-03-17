package com.jeandev.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jeandev.mapper.EmpleadoMapper;
import com.jeandev.model.Empleado;
import com.jeandev.model.Skill;
import com.jeandev.repository.EmpleadoRepository;
import com.jeandev.repository.SkillRepository;
import com.jeandev.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService , Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpleadoMapper empleadoMapper;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public Integer delete(Integer idEmpleado) {
		//return empleadoMapper.delete(idEmpleado);
		 empleadoRepository.deleteById(idEmpleado);
		return 1;
	}

	@Override
	public Empleado getById(Integer idEmpleado) {
		//return empleadoMapper.getById(idEmpleado);
		Optional<Empleado> opt = empleadoRepository.findById(idEmpleado);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		return new Empleado();
		
	}

	@Override
	public List<Empleado> getAll() {
		//return empleadoMapper.getAll();
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Override
	public Empleado login(String usuario, String clave) {
		return empleadoMapper.login(usuario, clave);
		
	}

	@Override
	public Integer insert(Empleado empleado) {
		int status = empleadoRepository.save(empleado) != null ? 1 : 0;
		return status;
	}

	@Override
	public Integer update(Empleado empleado) {
		skillRepository.eliminarPorEmpleado(empleado);
		int status = empleadoRepository.save(empleado) != null ? 1 : 0;
		return status;
	}

	


	/*
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		//realizamos la busqueda por un usuario
		Empleado empleado = empleadoMapper.obtenerPorUsuario(usuario);
		if(empleado != null) { 
			//definir un rol al usuario
			Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ADMIN");
			User secUser = new User(empleado.getUsuario(), empleado.getClave(), authorities);
			return secUser;
		}
		
		return (UserDetails) new Empleado();
	}
*/
	

}
