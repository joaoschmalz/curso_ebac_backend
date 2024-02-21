package org.example.exercises.m24_unitTests.dao.mock;

import org.example.exercises.m24_unitTests.dao.IContractDAO;

public class ContractDAOMock implements IContractDAO {
    @Override
    public void save() {

    }

    @Override
    public String retrieve() {
        return "In the future, here will return a contract";
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }
}
