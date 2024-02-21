package org.example.exercises.m24_unitTests.services;

import org.example.exercises.m24_unitTests.dao.IContractDAO;

public class DeleteContractService implements IDeleteContractService {

    private final IContractDAO contractDAO;
    public DeleteContractService(final IContractDAO dao) {
        this.contractDAO = dao;
    }

    @Override
    public boolean execute(Long id) {
        try {
            contractDAO.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
