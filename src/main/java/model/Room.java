package model;

public class Room {
    private String roomName;
    private Integer roomId;
    private Integer hallRows;
    private Integer hallCols;

    public Room(String roomName, Integer rows, Integer columns) {
        this.roomName = roomName;
        this.hallRows = rows;
        this.hallCols = columns;
    }

    public Room(Integer roomId, String roomName, Integer rows, Integer columns) {
        this.roomName = roomName;
        this.hallRows = rows;
        this.hallCols = columns;
        this.roomId = roomId;
    }

    public Room(Room room) {
        this.roomName = room.roomName;
        this.roomId = room.roomId;
        this.hallRows = room.hallRows;
        this.hallCols = room.hallCols;
    }

    public String getRoomName() {
        return roomName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getHallCols() {
        return hallCols;
    }

    public Integer getHallRows() {
        return hallRows;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setHallRows(Integer rows) {
        this.hallRows = rows;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setHallCols(Integer hallCols) {
        this.hallCols = hallCols;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", roomId=" + roomId +
                ", hallRows=" + hallRows +
                ", hallCols=" + hallCols +
                '}';
    }
}
