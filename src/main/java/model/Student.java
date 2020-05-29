package model;

public class Student extends Client {
    private final static Integer discount = 22;

    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    @Override
    public Double getPrice(Double price) {
        return price * (100 - discount) / 100;
    }

    @Override
    public String getDiscountType() {
        return "Student";
    }
}
