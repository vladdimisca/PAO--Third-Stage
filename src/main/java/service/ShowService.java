package service;

import model.Show;
import repository.ShowRepository;

public class ShowService {
    private final ShowRepository showRepository = ShowRepository.getInstance();
    private static final ShowService instance = new ShowService();
    AuditService auditService = AuditService.getInstance();

    private ShowService() { }

    public static ShowService getInstance() {
        return instance;
    }

    public void addShow(Show show) {
        auditService.writeAction("Add a new show",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        showRepository.addShow(show);
    }

    public Show getShowById(Integer id) {
        auditService.writeAction("Get a show by id",
                auditService.getTimestamp(),
                Thread.currentThread().getName()
        );

        return showRepository.getShowById(id);
    }
}
