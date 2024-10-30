package com.mayab.quality.loginunittest.dao;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMysqlDaoTest extends DBTestCase {

    public UserMysqlDaoTest(){
        System.setProperties(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS"com.mysql.cj.jdbc.Driver");

    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }

    @BeforeEach
    public void setUp() throws Exception {
        UserMysqlDao daoMysql = new UserMysqlDao();
        IDatabaseConnection connection = getConnection();
        try {
            DatabaseOperation.TRUNCATE_TABLE.execute(connection, getDataSet());
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e) {
            fail("Error in setup: " + e.getMessage());
        } finally {
            connection.close();
        }

    }
}