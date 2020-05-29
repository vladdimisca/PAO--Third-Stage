package servlet;

import model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.EventService;
import service.RoomService;
import service.ShowService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONArray jsonArray = new JSONArray();

        ArrayList<Event> events = EventService.getInstance().getAllEvents();

        for(Event event : events) {
            JSONObject json = new JSONObject();

            json.put("eventId", event.getEventId());
            json.put("showName", ShowService.getInstance().getShowById(event.getShowId()).getShowName());
            json.put("roomName", RoomService.getInstance().getRoomById(event.getRoomId()).getRoomName());
            json.put("eventDate", event.getDate().toString());
            json.put("price", ShowService.getInstance().getShowById(event.getShowId()).getPrice());

            jsonArray.add(json);
        }

        resp.getWriter().write(String.valueOf(jsonArray));
    }
}
