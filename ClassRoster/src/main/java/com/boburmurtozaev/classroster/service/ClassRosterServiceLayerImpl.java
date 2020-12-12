/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.service;

import com.boburmurtozaev.classroster.dao.ClassRosterAuditDao;
import com.boburmurtozaev.classroster.dao.ClassRosterPersistenceException;
import com.boburmurtozaev.classroster.dto.Student;
import java.util.List;
import com.boburmurtozaev.classroster.dao.ClassRosterDao;

/**
 *
 * @author boburmurtozaev
 */



/// Get All Students


public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    
    private ClassRosterDao dataAccess;
    private ClassRosterAuditDao audit;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao){
        this.dataAccess = dao;
        this.audit = auditDao;
    }

    @Override
    public void createStudent(Student student) throws
            ClassRosterDuplicateIDException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {
       
        if(dataAccess.getStudent(student.getStudentId())!=null){
            throw new ClassRosterDuplicateIDException("ERROR: Could not create "
                    + "student. Student ID "+student.getStudentId()+" already exists");
        }
         validateStudentData(student);
         
         dataAccess.addStudent(student.getStudentId(), student);
         
         audit.writeAuditEntry("Student "+student.getStudentId()+" created.");
    }

    @Override
    public List<Student> getAllStudents() throws 
            ClassRosterPersistenceException {
        return dataAccess.getAllStudents();
    }

    @Override
    public Student getStudent(String studentID) throws
            ClassRosterPersistenceException {
       return dataAccess.getStudent(studentID);
    }

    @Override
    public Student removeStudent(String studentID) throws 
            ClassRosterPersistenceException {
        
        Student removedStudent = dataAccess.removeStudent(studentID);
        
        audit.writeAuditEntry("Student "+studentID+" removed");
       return removedStudent;
    }
    
    private void validateStudentData(Student student) throws 
            ClassRosterDataValidationException {
        
        if(student.getFirstName()==null
                || student.getFirstName().trim().length()==0
                || student.getLastName()==null
                || student.getLastName().trim().length()==0
                || student.getCohort() == null
                || student.getCohort().trim().length()==0){
            
            throw new ClassRosterDataValidationException("ERROR: All fields [First Name, Last Name, Cohort] are required");
            
        }
    }
    
}
