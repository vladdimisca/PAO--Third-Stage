package repository;

import model.Show;

import java.sql.*;

public class ShowRepository {
    private static final ShowRepository showRepositoryInstance = new ShowRepository();
    String url = "jdbc:mysql://localhost/spectacole";
    String username = "root";
    String password = "";

    public ShowRepository() {}

    public static ShowRepository getInstance() {
        return showRepositoryInstance;
    }

    public void addShow(Show show) {
        String sqlInsert = "INSERT INTO SHOWS " +
                "(NAME, PRICE) " +
                "VALUES (?, ?)";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlInsert);

            statement.setString(1, show.getShowName());
            statement.setDouble(2, show.getPrice());

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

    public Show getShowById(Integer id) {
        String sqlSelect = "" +
                "SELECT " +
                "NAME, " +
                "PRICE " +
                "FROM SHOWS " +
                "WHERE ID = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        Show show = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                String showName = result.getString("NAME");
                Double price = result.getDouble("PRICE");

                show = new Show(id, showName, price);
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

        return show;
    }
}
