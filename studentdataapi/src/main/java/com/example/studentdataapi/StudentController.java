package com.example.studentdataapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
	@RequestMapping("/student")
	public class StudentController {
	
	@GetMapping("{studentId}")
	public ResponseEntity<ResponseData> getStudentData(@PathVariable("studentId") Long student){
		
		ResponseData response=new ResponseData();
		
		Student s1=new Student();
		s1.setStudentId(1l);
		s1.setStudentName("Farhaz Khan");
		s1.setAddress("Bhopal");
		s1.setCollegeId(1l);
		
		response.setStudent(s1);
		
		Long collegeId=s1.getCollegeId();
		RestTemplate restTempate=new RestTemplate();
		String endPoint="http://localhost:8080/college/{collegeId}";
		
		ResponseEntity<College> data=restTempate.getForEntity(endPoint, College.class, collegeId);
		if(data.getStatusCodeValue()==200) {
			College c1=data.getBody();
			response.setCollege(c1);
			
		}
		
		
		
		return new ResponseEntity<ResponseData>(response,HttpStatus.OK);
	}

}
