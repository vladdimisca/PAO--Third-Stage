package threads;

import exceptions.IllegalTicketException;
import exceptions.OutOfSeatsException;
import org.json.simple.JSONObject;
import service.TicketService;

public class AddTicketThread extends Thread {
    private final String email;
    private final Integer eventId;
    private JSONObject json = null;

    public AddTicketThread(String name, String email, Integer eventId) {
        this.setName(name);
        this.email = email;
        this.eventId = eventId;
    }

    @Override
    public void run() {
        this.json = new JSONObject();

        try {
            TicketService.getInstance().addTicket(email, eventId);
            json.put("success", "Added to your tickets");
        } catch (OutOfSeatsException e) {
            json.put("failure", "Sold out");
        } catch (IllegalTicketException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJson() {
        return json;
    }

}
