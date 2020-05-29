package service;

import exceptions.WrongPasswordException;
import model.*;
import repository.ClientRepository;

import java.util.ArrayList;


public class ClientService {
    private final ClientRepository clientRepository = ClientRepository.getInstance();
    private static final ClientService instance = new ClientService();
    AuditService auditService = AuditService.getInstance();

    private ClientService() { }

    public static ClientService getInstance() {
        return instance;
    }

    public void addClient(Client client) {
        auditService.writeAction("Add a new client",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        clientRepository.addClient(client);
    }

    public ArrayList<Client> getAllClients() {
        auditService.writeAction("Get all clients",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        return clientRepository.getAllClients();
    }

    public Client getClientByEmail(String email) {
        auditService.writeAction("Get a client by email",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        return clientRepository.getClientByEmail(email);
    }

    public void removeClientByEmail(String email, String password) throws WrongPasswordException {
        auditService.writeAction("Remove a client by email",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        String decryptedPassword = EncryptionService.getInstance().decrypt(getClientByEmail(email).getPassword());

        if(!decryptedPassword.equals(password))
            throw new WrongPasswordException("The password for this client is wrong!");

        TicketService.getInstance().removeTicketsByEmail(email);

        clientRepository.remove(email);
    }

    public void changePassword (String email, String oldPassword, String newPassword) throws WrongPasswordException {
        auditService.writeAction("Change client's password",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        EncryptionService encryptionService = EncryptionService.getInstance();

        String decryptedPassword = encryptionService.decrypt(getClientByEmail(email).getPassword());

        if (!decryptedPassword.equals(oldPassword))
            throw new WrongPasswordException("The password for this client is wrong!");

        clientRepository.changePassword(email, newPassword);
    }
}
