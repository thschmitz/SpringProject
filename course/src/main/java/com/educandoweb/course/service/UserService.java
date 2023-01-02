package com.educandoweb.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.service.exceptions.DatabaseException;
import com.educandoweb.course.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // Para poder ser injetado pelo autowired la dentro do UserResource;
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		
		/*try {
			repository.deleteById(id);	
		} catch(RuntimeException e) {
			e.printStackTrace(); // nos retorna a excecao EmptyResultDataAccessException
		}*/
		try {
			repository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage()); // todo
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); // Nao vai ao banco de dados, ele so prepara o objeto para mexer e depois efeturar uma operacao com banco de dados
			updateData(entity, obj);
			
			return repository.save(entity);	
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}
	
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
