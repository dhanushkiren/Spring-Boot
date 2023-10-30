package com.sms.studentManagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.studentManagement.DAO.courseDAO;
import com.sms.studentManagement.repository.courseRepository;


@Service
public class CourseService {
	
	@Autowired
	private courseRepository courseRepo;
	
	
	//create 
		public void createCourse(courseDAO course) {
			course.setCreatedAt(new Date(System.currentTimeMillis()));
			courseRepo.save(course);
		}
	
	
		//Get All
		public List<courseDAO> getAllcourses(){
			List<courseDAO> courses = courseRepo.findAll();
			if(courses.size() > 0) {
				return courses;
			}else {
				return new ArrayList<courseDAO>();
			}
		}
		
		
		//single get
		public courseDAO getSingleCourse(String id) {
			Optional<courseDAO> optionalcourse = courseRepo.findById(id);
			return optionalcourse.get();
		}
		
		
		//update
		public void updateCourse(String id, courseDAO course) {
			Optional<courseDAO> courseWithId = courseRepo.findById(id);
			if(courseWithId.isPresent()) {
				courseDAO courseToUpdate = courseWithId.get();
				courseToUpdate.setCourseName(course.getCourseName());
				courseToUpdate.setDescription(course.getDescription());
				courseToUpdate.setAvailableSeats(course.getAvailableSeats());
				courseToUpdate.setUpdatedAT(new Date(System.currentTimeMillis()));
				courseRepo.save(courseToUpdate);
			}else {
				return;
			}
		}
		
		
		//Delete
		public void deleteCourseById(String id) {
			Optional<courseDAO> courseOptional = courseRepo.findById(id);
			if(!courseOptional.isPresent()) {
				return;
			}else {
				courseRepo.deleteById(id);
			}
		}

		
}
