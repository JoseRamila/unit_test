package com.mayab.quality.loginunittest.dao;

import com.mayab.quality.loginunittest.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserMysqlDAO implements IDAOUser {

    public Connection getConnectionMySQL() {
        Connection con = null;
        try {
            // Establish the driver connector
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Set the URI for connecting to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/calidad", "root", "123456");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    @Override
    public User findByUserName(String name) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;
        User result = null;

        try {
            // Declare statement query to run
            preparedStatement = connection.prepareStatement("SELECT * from usuarios WHERE name = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

            // Obtain the pointer to the data in generated table
            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                boolean isLogged = rs.getBoolean(5);

                result = new User(username, password, email);
                result.setId(id);
                result.setLogged(isLogged);

                // Print user information
                System.out.println("\n---Alumno---");
                System.out.println("ID: " + result.getId());
                System.out.println("Nombre: " + result.getName());
                System.out.println("Email: " + result.getEmail());
                System.out.println("Tipo: " + result.isLogged() + "\n");
            }

            // Close resources
            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int save(User user) {
        Connection connection = getConnectionMySQL();
        int result = -1;
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO usuarios(name, email, password, isLogged) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
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
            }

            System.out.println("\nAlumno añadido con éxito\n>> Return: " + result + "\n");

            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;
        User result = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * from usuarios WHERE email = ?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String emailUser = rs.getString(3);
                String password = rs.getString(4);
                boolean isLogged = rs.getBoolean(5);

                result = new User(username, password, emailUser);
                result.setId(id);
                result.setLogged(isLogged);

                System.out.println("\n---Alumno---");
                System.out.println("ID: " + result.getId());
                System.out.println("Nombre: " + result.getName());
                System.out.println("Email: " + result.getEmail());
                System.out.println("Tipo: " + result.isLogged() + "\n");
            }

            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;
        List<User> listaAlumnos = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * from usuarios");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                boolean log = rs.getBoolean(5);

                User retrieved = new User(name, email, password);
                retrieved.setId(id);
                retrieved.setLogged(log);
                listaAlumnos.add(retrieved);
            }

            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return listaAlumnos;
    }

    @Override
    public User findById(int id) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;
        User result = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * from usuarios WHERE id = ?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int idUser = rs.getInt(1);
                String username = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                boolean isLogged = rs.getBoolean(5);

                result = new User(username, password, email);
                result.setId(idUser);
                result.setLogged(isLogged);

                System.out.println("\n---Alumno---");
                System.out.println("ID: " + result.getId());
                System.out.println("Nombre: " + result.getName());
                System.out.println("Email: " + result.getEmail());
                System.out.println("Tipo: " + result.isLogged() + "\n");
            }

            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = getConnectionMySQL();
        boolean result = false;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() >= 1) {
                result = true;
            }

            System.out.println("\nAlumno eliminado con éxito\n>> Return: " + result + "\n");

            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public User updateUser(User userNew) {
        Connection connection = getConnectionMySQL();
        User result = null;

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(
                    "UPDATE usuarios SET name = ?, password= ? WHERE id = ?");
            preparedStatement.setString(1, userNew.getName());
            preparedStatement.setString(2, userNew.getPassword());
            preparedStatement.setInt(3, userNew.getId());

            if (preparedStatement.executeUpdate() >= 1) {
                result = userNew;
            }

            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
