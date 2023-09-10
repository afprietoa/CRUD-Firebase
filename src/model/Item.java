package model;

public class Item {
    private String name;
    private Double price;
    private Long stock;

    public Item(String name, Double price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Item() {
    }
    
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

   
}