package com.sms.studentManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sms.studentManagement.DAO.courseDAO;

public interface courseRepository extends MongoRepository<courseDAO, String> {

}
