package com.sms.studentManagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.studentManagement.DAO.enrollmentDAO;
import com.sms.studentManagement.repository.enrollmentRepository;

@Service
public class EnrollmentService {

	
	@Autowired
	private enrollmentRepository enrollRepo;
	
	//create 
		public void createEnroll(enrollmentDAO enroll) {
			enroll.setCreatedAt(new Date(System.currentTimeMillis()));
			enrollRepo.save(enroll);
		}
		
		
		//Get All
		public List<enrollmentDAO> getAllEnrolls(){
			List<enrollmentDAO> enrolls = enrollRepo.findAll();
			if(enrolls.size() > 0) {
				return enrolls;
			}else {
				return new ArrayList<enrollmentDAO>();
			}
		}
		
	
		
		//single get
		public enrollmentDAO getSingleEnroll(String id) {
			Optional<enrollmentDAO> optionalEnroll = enrollRepo.findById(id);
			return optionalEnroll.get();
		}
	
		
		
		//update
		public void updateEnroll(String id, enrollmentDAO enroll) {
			Optional<enrollmentDAO> enrollWithId = enrollRepo.findById(id);
			if(enrollWithId.isPresent()) {
				enrollmentDAO enrollToUpdate = enrollWithId.get();
				enrollToUpdate.setStudentid(enroll.getStudentid());
				enrollToUpdate.setCourseid(enroll.getCourseid());
				enrollToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
				enrollRepo.save(enrollToUpdate);
			}else {
				return;
			}
		}
		
		
		
		//Delete
		public void deleteEnrollById(String id) {
			Optional<enrollmentDAO> enrollOptional = enrollRepo.findById(id);
			if(!enrollOptional.isPresent()) {
				return;
			}else {
				enrollRepo.deleteById(id);
			}
		}
		
		
}
