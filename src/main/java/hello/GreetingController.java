package hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jimmy.entities.Student;

@Controller

public class GreetingController {


	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		System.out.println("Hello");
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
		String result = restTemplate.getForObject("http://localhost:9200/invalidPage", String.class,
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
	
	

}
