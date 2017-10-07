package main.model.order;

public class ShopCart {
    private String item;
    private int count;
    private String type;
    private double price;
    private double sum;

    public ShopCart(){

    }

    public ShopCart (String item, String type, int count, double price){
        this.item = item;
        this.type = type;
        this.count = count;
        this.price = price;
        this.sum = count * price;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
