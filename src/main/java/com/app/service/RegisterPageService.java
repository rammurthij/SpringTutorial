package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.RegisterPage;
import com.app.repo.RegisterRepository;

@Service
@Transactional
public class RegisterPageService {

	
	@Autowired
    RegisterRepository lr;
	
	public List<RegisterPage> registerspagelist()
	{
		return lr.findAll();
	}
	
	public Optional<RegisterPage> findById(Long registerpageId){

		return lr.findById(registerpageId);
	}
	
	public RegisterPage save(RegisterPage registerpage){
		return lr.save(registerpage);
	}
	
	public void delete(RegisterPage registerpage) {
		lr.delete(registerpage);
	}
}
