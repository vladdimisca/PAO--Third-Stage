package repository;

import model.Room;

import java.sql.*;

public class RoomRepository {
    private static final RoomRepository roomRepositoryInstance = new RoomRepository();
    String url = "jdbc:mysql://localhost/spectacole";
    String username = "root";
    String password = "";

    public RoomRepository() {}

    public static RoomRepository getInstance() {
        return roomRepositoryInstance;
    }

    public void addRoom(Room room) {
        String sqlInsert = "INSERT INTO ROOMS " +
                "(NAME, N_ROWS, N_COLUMNS) " +
                "VALUES (?, ?, ?)";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlInsert);

            statement.setString(1, room.getRoomName());
            statement.setInt(2, room.getHallRows());
            statement.setInt(3, room.getHallCols());

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

    public Room getRoomById(Integer id) {
        String sqlSelect = "" +
                "SELECT " +
                "NAME, " +
                "N_ROWS, " +
                "N_COLUMNS " +
                "FROM ROOMS " +
                "WHERE ID = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        Room room = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                String roomName = result.getString("NAME");
                Integer hallRows = result.getInt("N_ROWS");
                Integer hallCols = result.getInt("N_COLUMNS");

                room = new Room(id, roomName, hallRows, hallCols);
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

        return room;
    }

    public void removeRoomById(Integer id) {
        String sqlDelete = "" +
                "DELETE " +
                "FROM " +
                "ROOMS " +
                "WHERE ID = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlDelete);

            statement.setInt(1, id);
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
}
