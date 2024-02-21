package org.example.exercises.m24_unitTests.services;

import org.example.exercises.m24_unitTests.dao.IContractDAO;

public class SaveContractService implements ISaveContractService {

    private final IContractDAO contractDAO;
    public SaveContractService(final IContractDAO dao) {
        this.contractDAO = dao;
    }

    @Override
    public boolean execute() {
        try{
            contractDAO.save();
            return true;
        } catch (Exception e) {
            throw e;
        }

    }
}
