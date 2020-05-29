package service;

import exceptions.EmailNotFoundException;
import exceptions.ExistingClientException;
import exceptions.WrongPasswordException;
import model.Client;
import threads.AddClientThread;


public class AuthenticationService {
    private static final AuthenticationService authenticationServiceInstance = new AuthenticationService();
    EncryptionService encryptionService = EncryptionService.getInstance();
    ClientService clientService = ClientService.getInstance();
    AuditService auditService = AuditService.getInstance();

    private AuthenticationService() {}

    public static AuthenticationService getInstance() {
        return authenticationServiceInstance;
    }

    public void register(Client client) throws ExistingClientException {
        auditService.writeAction("Register a new client",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        String password = encryptionService.encrypt(client.getPassword());
        client.setPassword(password);

        Client existingClient = clientService.getClientByEmail(client.getEmail());

        if(existingClient != null)
            throw new ExistingClientException("User already registered!");

        Thread addThread = new AddClientThread("Add Client Thread", client);

        addThread.start();

        try {
            addThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void login (String email, String password) throws EmailNotFoundException, WrongPasswordException {
        auditService.writeAction("Login a client",
                                    auditService.getTimestamp(),
                                    Thread.currentThread().getName()
                                );

        Client existingClient = clientService.getClientByEmail(email);

        if(existingClient == null)
            throw new EmailNotFoundException("This user is not registered!");

        String decryptedPassword = encryptionService.decrypt(existingClient.getPassword());
        if (!decryptedPassword.equals(password))
            throw new WrongPasswordException("The password for this user is wrong!");
    }
}
