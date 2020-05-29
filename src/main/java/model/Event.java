package model;

import java.util.Date;

public class Event {
    private Integer eventId;
    private Integer showId;
    private Integer roomId ;
    private Date date;

    public Event(Integer showId, Integer roomId, Date date) {
        this.showId = showId;
        this.roomId = roomId;
        this.date = date;
    }

    public Event(Integer eventId, Integer showId, Integer roomId, Date date) {
        this.eventId = eventId;
        this.showId = showId;
        this.roomId = roomId;
        this.date = date;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", showId=" + showId +
                ", roomId=" + roomId +
                ", date=" + date +
                '}';
    }
}
