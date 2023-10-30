package com.sms.studentManagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.studentManagement.DAO.studentDAO;
import com.sms.studentManagement.repository.studentRepository;

@Service
public class StudentService {
	
	@Autowired
	private studentRepository studentRepo;
	
	
	//create 
	public void createStudent(studentDAO student) {
		student.setCreatedAt(new Date(System.currentTimeMillis()));
		studentRepo.save(student);
	}
	
	//Get All
	public List<studentDAO> getAllStudents(){
		List<studentDAO> students = studentRepo.findAll();
		if(students.size() > 0) {
			return students;
		}else {
			return new ArrayList<studentDAO>();
		}
	}
	
	
	//single get
	public studentDAO getSingleStudent(String id) {
		Optional<studentDAO> optionalStudent = studentRepo.findById(id);
		return optionalStudent.get();
	}
	
	
	//update
	public void updateStudent(String id, studentDAO student) {
		Optional<studentDAO> studentWithId = studentRepo.findById(id);
		if(studentWithId.isPresent()) {
			studentDAO studentToUpdate = studentWithId.get();
			studentToUpdate.setName(student.getName());
			studentToUpdate.setPhone(student.getPhone());
			studentToUpdate.setMail(student.getMail());
			studentToUpdate.setAge(student.getAge());
			studentToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
			studentRepo.save(studentToUpdate);
		}else {
			return;
		}
	}
	
	
	//Delete
	public void deleteStudentById(String id) {
		Optional<studentDAO> studentOptional = studentRepo.findById(id);
		if(!studentOptional.isPresent()) {
			return;
		}else {
			studentRepo.deleteById(id);
		}
	}
}
