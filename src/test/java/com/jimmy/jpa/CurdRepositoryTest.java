/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jimmy.jpa;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jimmy.application.Application;
import com.jimmy.domain.Student;
import com.jimmy.service.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CurdRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    
    @Test
    public void testGetAllStudent(){
		Iterable<Student> list = studentRepository.findAll();
		System.out.println("list:"+list);
		Iterator<Student> itr = list.iterator();
		while(itr.hasNext()){
			System.out.println("id:"+itr.next().getId());
		}
    }
    
    @Test
    public void getStudentsBySex(){
    	List<Student> result = studentRepository.findBySex("Male");
    	System.out.println("getStudentsBySex():"+result);
    }
    
    @Test
    public void getAllByPage(){
		Pageable pageable = new PageRequest(0, 2);
		Page<Student> page = studentRepository.findAll(pageable);
//		Iterator<Student> students = page.iterator();
		for (Student student : page) {
			System.out.println("student:"+student.getName());
		}
    	
    }

}
