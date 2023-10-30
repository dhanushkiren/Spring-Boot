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

import com.sms.studentManagement.DAO.courseDAO;
import com.sms.studentManagement.repository.courseRepository;
import com.sms.studentManagement.service.CourseService;

@RestController
@RequestMapping("/courses")
public class courseController {

	@Autowired
	private courseRepository courseRepo;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping	
	public ResponseEntity<?>getAllCourses(){
		List<courseDAO> courses = courseRepo.findAll();
		
		return new ResponseEntity<>(courses, courses.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?>createCourse(@RequestBody courseDAO course){
		try {
//			student.setCreatedAt(new Date(System.currentTimeMillis()));
//			studentRepo.save(student);
			courseService.createCourse(course);
			return new ResponseEntity<courseDAO>(course,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getSingleCourse(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(courseService.getSingleCourse(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateCourse(@PathVariable("id") String id,@RequestBody courseDAO course){
		try {
			courseService.updateCourse(id, course);
			return new ResponseEntity<>("Updated Student Record with id "+id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable("id") String id){
		Optional<courseDAO> courseWithId = courseRepo.findById(id);
		try {
			if(!courseWithId.isPresent()) {
				return new ResponseEntity<>("record not found",HttpStatus.NOT_FOUND); 
			}else {
				courseService.deleteCourseById(id);
				return new ResponseEntity<>("Successfully deleted the record with id "+id, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}

