package com.sms.studentManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sms.studentManagement.DAO.enrollmentDAO;

public interface enrollmentRepository extends MongoRepository<enrollmentDAO, String> {

}
