package repository;

import exceptions.OutOfSeatsException;
import model.Event;
import model.Room;
import model.Seat;
import model.Ticket;
import service.EventService;
import service.RoomService;
import service.TicketService;


import java.sql.*;
import java.util.ArrayList;

public class EventRepository {
    private static final EventRepository eventRepositoryInstance = new EventRepository();
    String url = "jdbc:mysql://localhost/spectacole";
    String username = "root";
    String password = "";

    public EventRepository() {}

    public static EventRepository getInstance() {
        return eventRepositoryInstance;
    }

    public void addEvent(Event event) {
        String sqlInsert = "INSERT INTO EVENTS " +
                "(SHOW_ID, ROOM_ID, DATE) " +
                "VALUES (?, ?, ?)";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlInsert);

            statement.setInt(1, event.getShowId());
            statement.setInt(2, event.getRoomId());
            statement.setDate(3, new java.sql.Date(event.getDate().getTime()));

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

    public ArrayList<Event> getAllEvents() {
        String sqlSelect = "" +
                "SELECT " +
                "ID, " +
                "SHOW_ID, " +
                "ROOM_ID, " +
                "DATE " +
                "FROM EVENTS";

        ArrayList<Event> events = new ArrayList<>();

        Connection dbConnection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Integer eventId = result.getInt("ID");
                Integer showId = result.getInt("SHOW_ID");
                Integer roomId = result.getInt("ROOM_ID");
                java.util.Date date = result.getDate("DATE");

                Event event = new Event(eventId, showId, roomId, date);
                events.add(event);
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

        return events;
    }

    public Event getEventById(Integer id) {
        String sqlSelect = "" +
                "SELECT " +
                "SHOW_ID, " +
                "ROOM_ID, " +
                "DATE " +
                "FROM EVENTS " +
                "WHERE ID = ?";

        Connection dbConnection = null;
        PreparedStatement statement = null;

        Event event = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.prepareStatement(sqlSelect);

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                Integer showId = result.getInt("SHOW_ID");
                Integer roomId = result.getInt("ROOM_ID");
                java.util.Date date = result.getDate("DATE");

                event = new Event(id, showId, roomId, date);
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

        return event;
    }

    public boolean checkSeatAvailability(ArrayList<Ticket> tickets, Seat seat) {
        for(Ticket ticket : tickets) {
            if(ticket.getSeat().equals(seat))
                return false;
        }

        return true;
    }

    public Seat getFirstFreeSeatByEventId(Integer eventId) throws OutOfSeatsException {
        ArrayList<Ticket> tickets = TicketService.getInstance().getTicketsByEventId(eventId);
        Event event = EventService.getInstance().getEventById(eventId);
        Room room = RoomService.getInstance().getRoomById(event.getRoomId());

        for(int row = 1; row <= room.getHallRows(); row++)
            for(int column = 1; column <= room.getHallCols(); column++)
                if(checkSeatAvailability(tickets, new Seat(row, column)))
                    return new Seat(row, column);

        throw new OutOfSeatsException("There are no free seats");
    }
}
