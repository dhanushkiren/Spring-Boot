package com.sms.studentManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.studentManagement.DAO.studentDAO;
import com.sms.studentManagement.repository.studentRepository;
import com.sms.studentManagement.service.StudentService;



@RestController
@RequestMapping("/students")
public class studentController {

	@Autowired
	private studentRepository studentRepo;
	
	@Autowired
	private StudentService studentService;
	
	
	@GetMapping	
	public ResponseEntity<?>getAllStudents(){
		List<studentDAO> students = studentRepo.findAll();
		
		return new ResponseEntity<>(students, students.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<?>createStudent(@RequestBody studentDAO student){
		try {
			studentService.createStudent(student);
			return new ResponseEntity<studentDAO>(student,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getSingleStudent(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(studentService.getSingleStudent(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?>updateStudent(@PathVariable("id") String id,@RequestBody studentDAO student){
		try {
			studentService.updateStudent(id, student);
			return new ResponseEntity<>("Updated Student Record with id "+id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable("id") String id){
		Optional<studentDAO> studentWithId = studentRepo.findById(id);
		try {
			if(!studentWithId.isPresent()) {
				return new ResponseEntity<>("record not found",HttpStatus.NOT_FOUND); 
			}else {
				studentService.deleteStudentById(id);
				return new ResponseEntity<>("Successfully deleted the record with id "+id, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
