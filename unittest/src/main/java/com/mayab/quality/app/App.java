package com.mayab.quality.app;
import com.mayab.quality.loginunittest.dao.UserMysqlDao;
import com.mayab.quality.loginunittest.model.User;

import java.sql.*;
public class App {

    class JDBCTest{
        public static void main(String args[]) throws SQLException {
            Connection con = null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                String dbURL = "jdbc:mysql://localhost:3307/calidad";
                System.out.println("jdbcurl=" + dbURL);
                String strUserID = "root";
                String strPassword = "123456";
                con=DriverManager.getConnection(dbURL,strUserID,strPassword);
                System.out.println("Connected to the database.");
                Statement stmt=con.createStatement();
                System.out.println("Executing query");
                ResultSet rs=stmt.executeQuery("SELECT 1 FROM DUAL");
                while(rs.next())
                    System.out.println(rs.getInt("1"));
                con.close();
            }catch(Exception e){ System.out.println(e);}
            finally {
                con.close();
            }
            UserMysqlDao dao = new UserMysqlDao();
            dao.save(new User("name", "email@.com", "123456"));
        }
    }
}