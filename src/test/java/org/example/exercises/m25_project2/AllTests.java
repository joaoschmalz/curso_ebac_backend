package org.example.exercises.m25_project2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({  CustomerDAOTest.class, CustomerServiceTest.class, ProductDAOTest.class,
    ProductServiceTest.class, SaleDAOTest.class})
public class AllTests {
}
