package main.model.order;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item {
    private String item;
    private String ntd;
    private int count;
    private double pack;
    private String ei;
    private String type;
    private double price;
    private double sum;


    public Item(){

    }

    public Item(String item, String type, int count, double price){
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

    public double getPack() {
        return pack;
    }

    public void setPack(double pack) {
        this.pack = pack;
    }

    public String getEi() {
        return ei;
    }

    public void setEi(String ei) {
        this.ei = ei;
    }

    public String getNtd() {
        return ntd;
    }

    public void setNtd(String ntd) {
        this.ntd = ntd;
    }
}
