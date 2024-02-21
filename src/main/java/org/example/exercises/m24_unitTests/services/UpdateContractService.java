package org.example.exercises.m24_unitTests.services;

import org.example.exercises.m24_unitTests.dao.IContractDAO;

public class UpdateContractService implements IUpdateContractService {

    private final IContractDAO contractDAO;
    public UpdateContractService(final IContractDAO dao) {
        this.contractDAO = dao;
    }
    @Override
    public boolean execute() {
        try {
            contractDAO.update();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
