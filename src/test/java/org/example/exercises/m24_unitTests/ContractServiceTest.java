package org.example.exercises.m24_unitTests;

import org.example.exercises.m24_unitTests.dao.ContractDAO;
import org.example.exercises.m24_unitTests.dao.IContractDAO;
import org.example.exercises.m24_unitTests.dao.mock.ContractDAOMock;
import org.example.exercises.m24_unitTests.services.*;
import org.junit.Assert;
import org.junit.Test;

public class ContractServiceTest {

    public ContractServiceTest() {
    }

    @Test
    public void ensureCanSave() {
        IContractDAO mockDao = new ContractDAOMock();
        ISaveContractService saveService = new SaveContractService(mockDao);
        boolean result = saveService.execute();
        Assert.assertTrue(result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ensureWillPresentErrorOnSave() {
        IContractDAO dao = new ContractDAO();
        ISaveContractService saveService = new SaveContractService(dao);
        boolean result = saveService.execute();
        Assert.assertTrue(result);
    }

    @Test
    public void ensureCanReturnSingleContract() {
        IContractDAO mockDao = new ContractDAOMock();
        IRetrieveContractService retrieveService = new RetrieveContractService(mockDao);
        String result = retrieveService.execute(0L);
        Assert.assertEquals("In the future, here will return a contract", result);
    }

    @Test
    public void ensureCanDeleteSingleContract() {
        IContractDAO mockDao = new ContractDAOMock();
        IDeleteContractService deleteService = new DeleteContractService(mockDao);
        boolean result = deleteService.execute(0L);
        Assert.assertTrue(result);
    }

    @Test
    public void ensureCanUpdateSingleContract() {
        IContractDAO mockDao = new ContractDAOMock();
        IUpdateContractService updateService = new UpdateContractService(mockDao);
        boolean result = updateService.execute();
        Assert.assertTrue(result);
    }
}
