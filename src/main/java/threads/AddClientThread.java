package threads;

import model.Client;
import service.ClientService;


public class AddClientThread extends Thread {
    private final Client client;

    public AddClientThread(String name, Client client) {
        this.setName(name);
        this.client = client;
    }

    @Override
    public void run() {
        ClientService.getInstance().addClient(this.client);
    }
}
