package service;

import exceptions.IllegalTicketException;
import exceptions.OutOfSeatsException;
import model.Client;
import model.Event;
import model.Seat;
import model.Ticket;
import repository.TicketRepository;

import java.util.ArrayList;

public class TicketService {
    private final TicketRepository ticketRepository = TicketRepository.getInstance();
    private static final TicketService instance = new TicketService();
    AuditService auditService = AuditService.getInstance();

    private TicketService() { }

    public static TicketService getInstance() {
        return instance;
    }

    public boolean checkTicket(Ticket ticket) {
        auditService.writeAction("Check the validity of a ticket",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        Client client = ClientService.getInstance().getClientByEmail(ticket.getEmail());
        Event event = EventService.getInstance().getEventById(ticket.getEventId());

        return event != null && client != null;
    }

    public void addTicket(String email, Integer eventId) throws OutOfSeatsException, IllegalTicketException {
        auditService.writeAction("Add a new ticket",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        Seat seat = EventService.getInstance().getFirstFreeSeatByEventId(eventId);

        Ticket ticket = new Ticket(email, eventId, seat);

        if(!checkTicket(ticket))
            throw new IllegalTicketException("This ticket can't be assigned!");

        ticketRepository.addTicket(ticket);
    }

    public ArrayList<Ticket> getTicketsByEmail(String email) {
        auditService.writeAction("Get tickets by email",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        return ticketRepository.getTicketsByEmail(email);
    }

    public ArrayList<Ticket> getTicketsByEventId(Integer eventId) {
        auditService.writeAction("Get tickets by eventId",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        return ticketRepository.getTicketsByEventId(eventId);
    }

    public void removeTicketById(Integer id) {
        auditService.writeAction("Remove a ticket by id",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        ticketRepository.removeTicketById(id);
    }

    public void removeTicketsByEmail(String email) {
        auditService.writeAction("Remove tickets by email",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        ticketRepository.removeTicketsByEmail(email);
    }
}
