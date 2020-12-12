/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.service;

import com.boburmurtozaev.classroster.dao.ClassRosterPersistenceException;
import com.boburmurtozaev.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author boburmurtozaev
 */
public interface ClassRosterServiceLayer {
    
    void createStudent(Student student) throws 
            ClassRosterDuplicateIDException, 
            ClassRosterDataValidationException, 
            ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws 
            ClassRosterPersistenceException;
    
    Student getStudent(String studentID) throws 
            ClassRosterPersistenceException;
    
    Student removeStudent(String studentID) throws 
            ClassRosterPersistenceException;
    
    
}
