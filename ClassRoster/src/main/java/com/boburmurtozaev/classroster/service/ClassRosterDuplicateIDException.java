/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.service;

/**
 *
 * @author boburmurtozaev
 */
public class ClassRosterDuplicateIDException extends Exception {
    
    public ClassRosterDuplicateIDException(String message){
        super(message);
    }
    
    public ClassRosterDuplicateIDException(String message, Throwable cause){
        
        super(message, cause);
    }
    
}
