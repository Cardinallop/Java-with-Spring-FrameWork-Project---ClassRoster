/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.dao;

import com.boburmurtozaev.classroster.dto.Student;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author boburmurtozaev
 */
public class ClassRosterDaoFileImplTest {
    
    ClassRosterDao testDao;
    
    public ClassRosterDaoFileImplTest() {
    }
    
    
    
    //1st step - ARRANGE
    //putting the system in a known good state using JUnit setup() method.
    //"Known good state" - meaning starts with an empty blank file each time the test runs
    @BeforeEach
    public void setUp() throws Exception {
        
        String testFile = "testroster.txt";
        
        new FileWriter(testFile);
        
        testDao = new ClassRosterDaoFileImpl(testFile);
        
    }
    
    @Test
    public void testAddGetStudent() throws Exception {
        
        //Create our method test inputs
        
        String studentID = "007";
        Student student = new Student(studentID);
        student.setFirstName("James");
        student.setLastName("Bond");
        student.setCohort("Casino Royale");
        
        
        //Add the student to the DAO
        testDao.addStudent(studentID, student);
        
        //Get the student from the DAO
        Student retrievedStudent = testDao.getStudent(studentID);
        
        //Check the data is equal
        Assertions.assertEquals(student.getStudentId(),
                retrievedStudent.getStudentId(), "Checking student ID.");
        Assertions.assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking student first name");
        Assertions.assertEquals(student.getLastName(), retrievedStudent.getLastName(), "Checking student last name.");
        Assertions.assertEquals(student.getCohort(), retrievedStudent.getCohort(), "Checking student cohort");
        
        
    }
    
    @Test
    public void testAddgetAllStudents() throws Exception {
        //Create our first student
        String studentID = "007";
        Student firstStudent = new Student(studentID);
        firstStudent.setFirstName("James");
        firstStudent.setLastName("Bond");
        firstStudent.setCohort("Casino Royale");
        
        //Create our second student
        String secondStudentID = "008";
        Student secondStudent = new Student(studentID);
        secondStudent.setFirstName("Jason");
        secondStudent.setLastName("Born");
        secondStudent.setCohort("Status Quo");
        
        testDao.addStudent(firstStudent.getStudentId(), firstStudent);
        testDao.addStudent(secondStudent.getStudentId(),secondStudent);
        
        // Retrieve the list of all students within the DAO
    List<Student> allStudents = testDao.getAllStudents();

    // First check the general contents of the list
    assertNotNull(allStudents, "The list of students must not null");
    assertEquals(2, allStudents.size(),"List of students should have 2 students.");

    // Then the specifics
    assertTrue(testDao.getAllStudents().contains(firstStudent),
                "The list of students should include Ada.");
    assertTrue(testDao.getAllStudents().contains(secondStudent),
            "The list of students should include Charles.");

        
        
    }
    
    @Test
public void testRemoveStudent() throws Exception {
    // Create two new students
    Student firstStudent = new Student("0001");
    firstStudent.setFirstName("Ada");
    firstStudent.setLastName("Lovelace");
    firstStudent.setCohort("Java-May-1945");

    Student secondStudent = new Student("0002");
    secondStudent.setFirstName("Charles");
    secondStudent.setLastName("Babbage");
    secondStudent.setCohort(".NET-May-1945");

    // Add both to the DAO
    testDao.addStudent(firstStudent.getStudentId(), firstStudent);
    testDao.addStudent(secondStudent.getStudentId(), secondStudent);

    // remove the first student - Ada
    Student removedStudent = testDao.removeStudent(firstStudent.getStudentId());

    // Check that the correct object was removed.
    assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");

    // Get all the students
    List<Student> allStudents = testDao.getAllStudents();

    // First check the general contents of the list
    assertNotNull( allStudents, "All students list should be not null.");
    assertEquals( 1, allStudents.size(), "All students should only have 1 student.");

    // Then the specifics
    assertFalse( allStudents.contains(firstStudent), "All students should NOT include Ada.");
    assertTrue( allStudents.contains(secondStudent), "All students should NOT include Charles.");    

    // Remove the second student
    removedStudent = testDao.removeStudent(secondStudent.getStudentId());
    // Check that the correct object was removed.
    assertEquals( removedStudent, secondStudent, "The removed student should be Charles.");

    // retrieve all of the students again, and check the list.
    allStudents = testDao.getAllStudents();

    // Check the contents of the list - it should be empty
    assertTrue( allStudents.isEmpty(), "The retrieved list of students should be empty.");

    // Try to 'get' both students by their old id - they should be null!
    Student retrievedStudent = testDao.getStudent(firstStudent.getStudentId());
    assertNull(retrievedStudent, "Ada was removed, should be null.");

    retrievedStudent = testDao.getStudent(secondStudent.getStudentId());
    assertNull(retrievedStudent, "Charles was removed, should be null.");

}
    
    
    
   

   
    
}
