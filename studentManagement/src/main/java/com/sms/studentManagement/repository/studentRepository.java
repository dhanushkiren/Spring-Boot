package com.sms.studentManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sms.studentManagement.DAO.studentDAO;

public interface studentRepository extends MongoRepository<studentDAO, String> {

}
