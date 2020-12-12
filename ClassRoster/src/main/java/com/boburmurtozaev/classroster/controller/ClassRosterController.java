/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.controller;

import com.boburmurtozaev.classroster.dao.ClassRosterPersistenceException;
import com.boburmurtozaev.classroster.dto.Student;
import com.boburmurtozaev.classroster.ui.ClassRosterView;
import java.util.List;
import com.boburmurtozaev.classroster.service.ClassRosterDataValidationException;
import com.boburmurtozaev.classroster.service.ClassRosterDuplicateIDException;
import com.boburmurtozaev.classroster.dao.ClassRosterDao;
import com.boburmurtozaev.classroster.service.ClassRosterServiceLayer;

/**
 *
 * @author boburmurtozaev
 */
public class ClassRosterController {
    
    private ClassRosterView view;
    private ClassRosterServiceLayer serv;
    
    public ClassRosterController(ClassRosterServiceLayer serviceLayer, ClassRosterView view) {
    this.serv = serviceLayer;
    this.view = view;
}

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        while (keepGoing) {
           

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
        }catch (ClassRosterPersistenceException e) {
        view.displayErrorMessage(e.getMessage());
    }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterPersistenceException {
    view.displayCreateStudentBanner();
    boolean hasErrors = false;
    do{
        Student currentStudent = view.getNewStudentInfo();
        try{
            serv.createStudent(currentStudent);
            view.displayCreateSuccessBanner();
            hasErrors = false;
        }catch(ClassRosterDuplicateIDException | ClassRosterDataValidationException e){
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        
    }while(hasErrors);
    
    
}
    
    private void listStudents() throws ClassRosterPersistenceException {
    view.displayDisplayAllBanner();
    List<Student> studentList = serv.getAllStudents();
    view.displayStudentList(studentList);
}
    
    private void viewStudent() throws ClassRosterPersistenceException {
    view.displayDisplayStudentBanner();
    String studentId = view.getStudentIdChoice();
    Student student = serv.getStudent(studentId);
    view.displayStudent(student);
}
    private void removeStudent() throws ClassRosterPersistenceException {
    view.displayRemoveStudentBanner();
    String studentId = view.getStudentIdChoice();
    serv.removeStudent(studentId);
    view.displayRemoveSuccessBanner();
}
    
    private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}

    

    
    
}
