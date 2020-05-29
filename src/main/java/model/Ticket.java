package model;

public class Ticket {
    private Integer ticketId;
    private Integer eventId;
    private String email;
    private Seat seat;

    public Ticket(String email, Integer eventId, Seat seat) {
        this.eventId = eventId;
        this.email = email;
        this.seat = seat;
    }

    public Ticket(Integer ticketId, String email, Integer eventId, Seat seat) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.email = email;
        this.seat = seat;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventId=" + eventId +
                ", email='" + email + '\'' +
                ", seat=" + seat +
                '}';
    }
}
