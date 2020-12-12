/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boburmurtozaev.classroster.service;

import com.boburmurtozaev.classroster.dao.ClassRosterAuditDao;
import com.boburmurtozaev.classroster.dao.ClassRosterPersistenceException;

/**
 *
 * @author boburmurtozaev
 */
public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing . . .
    }
    
}
