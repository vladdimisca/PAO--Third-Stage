package servlet;

import exceptions.EmailNotFoundException;
import exceptions.ExistingClientException;
import exceptions.WrongPasswordException;
import model.Child;
import model.Client;
import model.Pensioner;
import model.Student;
import org.json.simple.JSONObject;
import service.AuthenticationService;
import service.ClientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        Client client = ClientService.getInstance().getClientByEmail(email);

        JSONObject json = new JSONObject();

        json.put("firstName", client.getFirstName());
        json.put("lastName", client.getLastName());
        json.put("email", client.getEmail());
        json.put("discountType", client.getDiscountType());

        resp.getWriter().write(String.valueOf(json));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String discountType = req.getParameter("discountType");

        Client client = null;

        switch (discountType) {
            case "Adult":
                client = new Client(firstName, lastName, email, password);
                break;
            case "Child":
                client = new Child(firstName, lastName, email, password);
                break;
            case "Pensioner":
                client = new Pensioner(firstName, lastName, email, password);
                break;
            case "Student":
                client = new Student(firstName, lastName, email, password);
                break;
            default:
                System.out.println("This type of discount doesn't exist!");
        }

        final String message = "You have been successfully registered!";
        JSONObject json = new JSONObject();

        try {
            assert client != null;
            AuthenticationService.getInstance().register(client);
            json.put("success", message);
        } catch (ExistingClientException e) {
            json.put("failure", e.getMessage());
        }

        resp.getWriter().write(String.valueOf(json));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        JSONObject json = new JSONObject();

        try {
            AuthenticationService.getInstance().login(email, password);
            json.put("success", "success");
        } catch (EmailNotFoundException | WrongPasswordException e) {
            json.put("failure", e.getMessage());
        }

        resp.getWriter().write(String.valueOf(json));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        JSONObject json = new JSONObject();

        try {
            ClientService.getInstance().removeClientByEmail(email, password);

            json.put("success", "success");
        } catch (WrongPasswordException e) {
            json.put("failure", e.getMessage());
        }

        resp.getWriter().write(String.valueOf(json));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        JSONObject json = new JSONObject();

        try {
            ClientService.getInstance().changePassword(email, oldPassword, newPassword);
            json.put("success", "Your password was updated!");
        } catch (WrongPasswordException e) {
            json.put("failure", e.getMessage());
        }

        resp.getWriter().write(String.valueOf(json));
    }
}
