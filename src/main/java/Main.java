import exceptions.IllegalEventException;
import model.Event;
import model.Room;
import model.Show;
import service.EventService;
import service.RoomService;
import service.ShowService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        ShowService.getInstance().addShow(new Show("Ted Talk", 100.0));
        ShowService.getInstance().addShow(new Show("Stand up night", 15.0));
        ShowService.getInstance().addShow(new Show("Theatre night", 18.0));
        ShowService.getInstance().addShow(new Show("Talent show", 20.0));

        RoomService.getInstance().addRoom(new Room("National theatre", 50, 75));
        RoomService.getInstance().addRoom(new Room("Club 99", 20, 30));
        RoomService.getInstance().addRoom(new Room("True club", 20, 25));


        try {
            EventService.getInstance().addEvent(new Event(1, 3, new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-21")));
            EventService.getInstance().addEvent(new Event(2, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-05")));
            EventService.getInstance().addEvent(new Event(3, 1, new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-10")));
            EventService.getInstance().addEvent(new Event(4, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-18")));
            EventService.getInstance().addEvent(new Event(2, 3, new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-22")));
            EventService.getInstance().addEvent(new Event(1, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-29")));
            EventService.getInstance().addEvent(new Event(4, 3, new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-14")));
        } catch (IllegalEventException | ParseException e) {
            e.printStackTrace();
        }
    }
}
