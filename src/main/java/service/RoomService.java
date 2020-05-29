package service;

import model.Room;
import repository.RoomRepository;

public class RoomService {
    private final RoomRepository roomRepository = RoomRepository.getInstance();
    private static final RoomService instance = new RoomService();
    AuditService auditService = AuditService.getInstance();

    private RoomService() { }

    public static RoomService getInstance() {
        return instance;
    }

    public void addRoom(Room room) {
        auditService.writeAction("Add a new room",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        roomRepository.addRoom(room);
    }

    public Room getRoomById(Integer id) {
        auditService.writeAction("Get a room by id",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        return roomRepository.getRoomById(id);
    }
}
