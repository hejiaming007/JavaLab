package com.jimmy.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jimmy.domain.Student;
/*
 * You don't have to write a implementation of this interface, Spring data JPA will do it for u at runtime.
 * 
 * */

public interface StudentRepository extends CrudRepository<Student, Long> {
 
	List<Student> findByName(String name);
	
	List<Student> findBySex(String sex);

}
