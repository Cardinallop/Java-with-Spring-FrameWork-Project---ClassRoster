/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.dao;

/**
 *
 * @author boburmurtozaev
 */
public interface ClassRosterAuditDao {
    
    public void writeAuditEntry(String entry) throws
            ClassRosterPersistenceException;
    
}
