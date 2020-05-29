package service;

import exceptions.IllegalEventException;
import exceptions.OutOfSeatsException;
import model.Event;
import model.Room;
import model.Seat;
import model.Show;
import repository.EventRepository;

import java.util.ArrayList;

public class EventService {
    private final EventRepository eventRepository = EventRepository.getInstance();
    private static final EventService instance = new EventService();
    AuditService auditService = AuditService.getInstance();

    private EventService() { }

    public static EventService getInstance() {
        return instance;
    }

    public boolean checkEvent(Event event) {
        auditService.writeAction("Check the validity of an event",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        Show show = ShowService.getInstance().getShowById(event.getShowId());
        Room room = RoomService.getInstance().getRoomById(event.getRoomId());

        return show != null && room != null;
    }

    public void addEvent(Event event) throws IllegalEventException {
        auditService.writeAction("Add a new event",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        if(!checkEvent(event))
            throw new IllegalEventException("This event couldn't take place!");

        eventRepository.addEvent(event);
    }

    public ArrayList<Event> getAllEvents() {
        auditService.writeAction("Get all events",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        return eventRepository.getAllEvents();
    }

    public Event getEventById(Integer id) {
        auditService.writeAction("Get an event by id",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        return eventRepository.getEventById(id);
    }

    public Seat getFirstFreeSeatByEventId(Integer eventId) throws OutOfSeatsException {
        auditService.writeAction("Get the first free seat for an event by id",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        return eventRepository.getFirstFreeSeatByEventId(eventId);
    }
}
