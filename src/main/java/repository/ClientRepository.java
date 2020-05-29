package repository;

import model.Client;
import model.Student;
import model.Pensioner;
import model.Child;
import service.EncryptionService;

import java.sql.*;
import java.util.ArrayList;

public class ClientRepository {
    private static final ClientRepository clientRepositoryInstance = new ClientRepository();
    String url = "jdbc:mysql://localhost/spectacole";
    String username = "root";
    String password = "";

    public ClientRepository() {}

    public static ClientRepository getInstance() {
        return clientRepositoryInstance;
    }

    public void addClient(Client client) {
        String sqlInsert = "INSERT INTO CLIENTS " +
                           "(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, DISCOUNT_TYPE) " +
                           "VALUES (?, ?, ?, ?, ?)";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlInsert);

            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getDiscountType());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e)   {
            e.printStackTrace();
        } finally {
            try {
                assert dbConnection != null;
                dbConnection.close();

                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Client getClientByEmail(String email) {
        String sqlSelect = "" +
                "SELECT " +
                "FIRST_NAME, " +
                "LAST_NAME, " +
                "PASSWORD, " +
                "DISCOUNT_TYPE " +
                "FROM CLIENTS " +
                "WHERE EMAIL = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        Client client = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                String firstName = result.getString("FIRST_NAME");
                String lastName = result.getString("LAST_NAME");
                String password = result.getString("PASSWORD");
                String discountType = result.getString("DISCOUNT_TYPE");

                switch(discountType) {
                    case "No discount":
                        client = new Client(firstName, lastName, email, password);
                        break;
                    case "Student":
                        client = new Student(firstName, lastName, email, password);
                        break;
                    case "Child":
                        client = new Child(firstName, lastName, email, password);
                        break;
                    case "Pensioner":
                        client = new Pensioner(firstName, lastName, email, password);
                        break;
                }

            }
        } catch (SQLException | ClassNotFoundException e)   {
            e.printStackTrace();
        } finally {
            try {
                assert dbConnection != null;
                dbConnection.close();

                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

    public ArrayList<Client> getAllClients() {
        String sqlSelect = "" +
                "SELECT " +
                "FIRST_NAME, " +
                "LAST_NAME, " +
                "EMAIL, " +
                "PASSWORD, " +
                "DISCOUNT_TYPE " +
                "FROM CLIENTS";

        ArrayList<Client> clients = new ArrayList<>();

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                String firstName = result.getString("FIRST_NAME");
                String lastName = result.getString("LAST_NAME");
                String email = result.getString("EMAIL");
                String password = result.getString("PASSWORD");
                String discountType = result.getString("DISCOUNT_TYPE");

                Client client = null;

                switch (discountType) {
                    case "No discount":
                        client = new Client(firstName, lastName, email, password);
                        break;
                    case "Student":
                        client = new Student(firstName, lastName, email, password);
                        break;
                    case "Child":
                        client = new Child(firstName, lastName, email, password);
                        break;
                    case "Pensioner":
                        client = new Pensioner(firstName, lastName, email, password);
                        break;
                }

                clients.add(client);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert dbConnection != null;
                dbConnection.close();

                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clients;
    }

    public void remove(String email) {
        String sqlDelete = "" +
                "DELETE " +
                "FROM " +
                "CLIENTS " +
                "WHERE EMAIL = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlDelete);

            statement.setString(1, email);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                assert dbConnection != null;
                dbConnection.close();

                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void changePassword (String email, String newPassword) {
        String sqlUpdate = "" +
                "UPDATE " +
                "CLIENTS " +
                "SET PASSWORD = ? " +
                "WHERE EMAIL = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlUpdate);

            String newPasswordEncrypted = EncryptionService.getInstance().encrypt(newPassword);

            statement.setString(1, newPasswordEncrypted);
            statement.setString(2, email);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e)   {
            e.printStackTrace();
        } finally {
            try {
                assert dbConnection != null;
                dbConnection.close();

                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
