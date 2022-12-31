package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educandoweb.course.entities.User;

@Repository // Aqui eh opcional a notation pq esta importando do JpaRepository que ja esta registrado no spring
public interface UserRepository extends JpaRepository<User, Long>{
	
}
