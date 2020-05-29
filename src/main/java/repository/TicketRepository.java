package repository;

import model.Seat;
import model.Ticket;

import java.sql.*;
import java.util.ArrayList;

public class TicketRepository {
    private static final TicketRepository ticketRepositoryInstance = new TicketRepository();
    String url = "jdbc:mysql://localhost/spectacole";
    String username = "root";
    String password = "";

    public TicketRepository() {}

    public static TicketRepository getInstance() {
        return ticketRepositoryInstance;
    }

    public void addTicket(Ticket ticket) {
        String sqlInsert = "INSERT INTO TICKETS " +
                "(EVENT_ID, EMAIL, N_ROW, N_COLUMN) " +
                "VALUES (?, ?, ?, ?)";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlInsert);

            statement.setInt(1, ticket.getEventId());
            statement.setString(2, ticket.getEmail());
            statement.setInt(3, ticket.getSeat().getRow());
            statement.setInt(4, ticket.getSeat().getColumn());

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

    public ArrayList<Ticket> getTicketsByEmail(String email) {
        String sqlSelect = "" +
                "SELECT " +
                "ID, " +
                "EVENT_ID, " +
                "N_ROW, " +
                "N_COLUMN " +
                "FROM TICKETS " +
                "WHERE EMAIL = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Integer ticketId = result.getInt("ID");
                Integer eventId = result.getInt("EVENT_ID");
                Integer row = result.getInt("N_ROW");
                Integer column = result.getInt("N_COLUMN");

                Ticket ticket = new Ticket(ticketId, email, eventId, new Seat(row, column));
                tickets.add(ticket);
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

        return tickets;
    }

    public ArrayList<Ticket> getTicketsByEventId(Integer eventId) {
        String sqlSelect = "" +
                "SELECT " +
                "ID, " +
                "EMAIL, " +
                "N_ROW, " +
                "N_COLUMN " +
                "FROM TICKETS " +
                "WHERE EVENT_ID = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setInt(1, eventId);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Integer ticketId = result.getInt("ID");
                String email = result.getString("EMAIL");
                Integer row = result.getInt("N_ROW");
                Integer column = result.getInt("N_COLUMN");

                Ticket ticket = new Ticket(ticketId, email, eventId, new Seat(row, column));
                tickets.add(ticket);
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

        return tickets;
    }

    public void removeTicketById(Integer id) {
        String sqlDelete = "" +
                "DELETE " +
                "FROM " +
                "TICKETS " +
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

    public void removeTicketsByEmail(String email) {
        String sqlDelete = "" +
                "DELETE " +
                "FROM " +
                "TICKETS " +
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
}
