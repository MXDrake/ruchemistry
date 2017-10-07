package main.model.order;

import java.util.ArrayList;
import java.util.List;

public class ShopCart {
    private List<Item> shopCart = new ArrayList <>();

    public void addItem(Item item){
        this.shopCart.add(item);
    }

    public List <Item> getShopCart() {
        return shopCart;
    }

    public void setShopCart(List <Item> shopCart) {
        this.shopCart = shopCart;
    }
}
