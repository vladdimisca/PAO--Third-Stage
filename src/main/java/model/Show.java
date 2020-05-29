package model;


public class Show {
    private String showName;
    private Integer showId;
    private Double price;

    public Show(Integer showId, String showName, Double price) {
        this.price = price;
        this.showName = showName;
        this.showId = showId;
    }

    public Show(String showName, Double price) {
        this.price = price;
        this.showName = showName;
    }

    public Double getPrice() {
        return price;
    }

    public String getShowName() {
        return showName;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showName='" + showName + '\'' +
                ", showId=" + showId +
                ", price=" + price +
                '}';
    }
}
