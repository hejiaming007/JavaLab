package com.jimmy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jimmy.domain.Student;
import com.jimmy.service.StudentRepository;

@Controller
public class RestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Autowired
    private StudentRepository studentRepository;
	
	private static final Logger logger = LoggerFactory
			.getLogger(RestController.class);

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		logger.debug("restTemplate:"+restTemplate);
		logger.debug("studentRepository:"+studentRepository);
		return "greeting";
	}
	
	@RequestMapping("/errorMaker")
	public String errorMaker(
			Model model) throws Exception {
		model.addAttribute("Exception", "");
		throw new Exception("er...");	
	}
	
	@RequestMapping("/errorMaker404")
	public String errorMaker404(
			Model model) throws Exception {
		 restTemplate.getForObject("http://localhost:9200/invalidPage", String.class,
				new Object[]{});
		return "greeting";
	}

	@RequestMapping("/greeting2")
	@ResponseBody
	public String greeting2(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) throws JsonProcessingException {
		model.addAttribute("name", name);
		System.out.println("restTemplate:" + restTemplate);
		
		Student student = new Student();
		student.setName("Jimmy");
		student.setSex("Male");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(student);
		
		
		return "Return response body text directly....json:" + json;
	}

	@RequestMapping("/elasticHealth")
	@ResponseBody
	public String greeting3(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		System.out.println("in elastic Health.");
		model.addAttribute("name", name);
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("hotel", "42");
		vars.put("booking", "21");
		
//		String result = restTemplate.getForObject("http://example.com/hotels/{hotel}/bookings/{booking}", String.class,
//				vars);
		String result = restTemplate.getForObject("http://localhost:9200/_cat/health?v&pretty", String.class,
				vars);
		
		return "Rest call result: " + result;
	}
	
	
	/*
	 * 
	 * RESTful return JSON object.
	 */
	@RequestMapping(path="/findStudentByName", method=RequestMethod.GET)
	public ResponseEntity<List<Student>> findStudentByName(@RequestParam(value = "name", required = true) String name){
		List<Student> students = studentRepository.findByName(name);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@RequestMapping(path="/findAllStudents", method=RequestMethod.GET)
	public ResponseEntity<List<Student>> findStudentByName(){
		 Iterable<Student> students = studentRepository.findAll();
		 List<Student> result = new ArrayList<Student>();
		for (Student student : students) {
			result.add(student);
		}
		
		return new ResponseEntity<List<Student>>(result, HttpStatus.OK);
	}
	

}
