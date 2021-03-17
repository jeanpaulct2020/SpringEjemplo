package com.jeandev.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeandev.mapper.TipoEmpleadoMapper;
import com.jeandev.model.TipoEmpleado;
import com.jeandev.repository.EmpleadoRepository;
import com.jeandev.repository.TipoEmpleadoRepository;
import com.jeandev.service.TipoEmpleadoService;

@Service
public class TipoEmpleadoServiceImpl implements TipoEmpleadoService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoEmpleadoMapper tipoEmpleadoMapper;
	
	@Autowired
	private TipoEmpleadoRepository tipoEmpleadoRepository;
	
	@Override
	public TipoEmpleado getTipo(Integer idTipoEmpleado) {
		//return tipoEmpleadoMapper.getTipo(idTipoEmpleado);
		Optional<TipoEmpleado> opt = tipoEmpleadoRepository.findById(idTipoEmpleado);
		return opt.get();
	}

	@Override
	public List<TipoEmpleado> getTipos() {
		//return tipoEmpleadoMapper.getTipos();
		return (List<TipoEmpleado>) tipoEmpleadoRepository.findAll();
	}

	
}
