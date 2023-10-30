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

import com.sms.studentManagement.DAO.enrollmentDAO;
import com.sms.studentManagement.repository.enrollmentRepository;
import com.sms.studentManagement.service.EnrollmentService;


@RestController
@RequestMapping("/enroll")
public class enrollmentController {

	@Autowired
	private enrollmentRepository enrollRepo;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	
	@GetMapping
	public ResponseEntity<?>getAllEnroll(){
		List<enrollmentDAO> enrolls = enrollRepo.findAll();
		
		return new ResponseEntity<>(enrolls, enrolls.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity<?>createEnroll(@RequestBody enrollmentDAO enroll){
		try {
			enrollmentService.createEnroll(enroll);
			return new ResponseEntity<enrollmentDAO>(enroll, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>getSingleEnroll(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(enrollmentService.getSingleEnroll(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateEnroll(@PathVariable("id") String id,@RequestBody enrollmentDAO enroll){
		try {
			enrollmentService.updateEnroll(id, enroll);
			return new ResponseEntity<>("Updated enrollment Record with id "+id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable("id") String id){
		Optional<enrollmentDAO> enrollWithId = enrollRepo.findById(id);
		try {
			if(!enrollWithId.isPresent()) {
				return new ResponseEntity<>("record not found",HttpStatus.NOT_FOUND); 
			}else {
				enrollmentService.deleteEnrollById(id);
				return new ResponseEntity<>("Successfully deleted the record with id "+id, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
}
