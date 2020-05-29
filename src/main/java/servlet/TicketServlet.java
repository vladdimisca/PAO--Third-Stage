package servlet;

import exceptions.IllegalTicketException;
import exceptions.OutOfSeatsException;
import model.Client;
import model.Event;
import model.Ticket;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.*;
import threads.AddTicketThread;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        Integer eventId = Integer.parseInt(req.getParameter("eventId"));

        AddTicketThread addThread = new AddTicketThread("Add Ticket Thread", email, eventId);

        addThread.start();
        JSONObject json = null;

        try {
            addThread.join();

            json = addThread.getJson();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        resp.getWriter().write(String.valueOf(json));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");

        JSONArray jsonArray = new JSONArray();

        ArrayList<Ticket> tickets = TicketService.getInstance().getTicketsByEmail(email);

        for(Ticket ticket : tickets) {
            JSONObject json = new JSONObject();

            Event event = EventService.getInstance().getEventById(ticket.getEventId());
            Client client = ClientService.getInstance().getClientByEmail(email);

            json.put("ticketId", ticket.getTicketId());
            json.put("showName", ShowService.getInstance().getShowById(event.getShowId()).getShowName());
            json.put("roomName", RoomService.getInstance().getRoomById(event.getRoomId()).getRoomName());
            json.put("eventDate", event.getDate().toString());
            json.put("price", client.getPrice(ShowService.getInstance().getShowById(event.getShowId()).getPrice()));

            if(client.getDiscountType().equals("No discount")) {
                json.put("discount", client.getDiscountType());
            } else {
                json.put("discount", client.getDiscountType() + " discount");
            }

            json.put("row", ticket.getSeat().getRow());
            json.put("column", ticket.getSeat().getColumn());

            jsonArray.add(json);
        }

        resp.getWriter().write(String.valueOf(jsonArray));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer ticketId = Integer.parseInt(req.getParameter("ticketId"));

        TicketService.getInstance().removeTicketById(ticketId);

        JSONObject json = new JSONObject();

        json.put("success", "success");

        resp.getWriter().write(String.valueOf(json));
    }
}
