package model;

public class Child  extends Client {
    private final static Integer discount = 40;

    public Child(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    @Override
    public Double getPrice(Double price) {
        return price * (100 - discount) / 100;
    }

    @Override
    public String getDiscountType() {
        return "Child";
    }
}
