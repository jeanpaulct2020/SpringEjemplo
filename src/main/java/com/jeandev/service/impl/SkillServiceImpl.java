package com.jeandev.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeandev.mapper.SkillMapper;
import com.jeandev.model.Skill;
import com.jeandev.repository.SkillRepository;
import com.jeandev.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SkillMapper skillMapper;
	
	@Autowired
	private SkillRepository SkillRepository;
	
	@Override
	public Integer insert(Skill skill) {
		int status = SkillRepository.save(skill) != null ? 1 : 0;
		return status;
	}

}
