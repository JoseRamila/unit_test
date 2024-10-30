package com.mayab.quality.loginunittest.dao;

import com.mayab.quality.loginunittest.model.User;
import com.sun.jdi.connect.spi.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserMysqlDao implements IDAOUser {
    @Override
    public User findByUserName(String name) {
        return null;
    }

    @Override
    public int save(User user) throws SQLException {
        int result = -1;

        try (Connection connection = getConnectionMySQL();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users(name, email, password, isLogged) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.isLogged());

            if (preparedStatement.executeUpdate() >= 1) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        result = rs.getInt(1);
                    }
                }
                System.out.println("\nAlumno añadido con éxito");
                System.out.println(">> Return: " + result + "\n");
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }

        return result;
    }


    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public User updateUser(User userOld) {
        return null;
    }
}
